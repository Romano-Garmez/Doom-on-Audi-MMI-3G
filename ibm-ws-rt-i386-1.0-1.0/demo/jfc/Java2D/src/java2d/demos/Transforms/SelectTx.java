/*
 * @(#)src/demo/jfc/Java2D/src/java2d/demos/Transforms/SelectTx.java, dsdev, dsdev 1.10
 * ===========================================================================
 * Licensed Materials - Property of IBM
 * "Restricted Materials of IBM"
 *
 * IBM SDK, Java(tm) 2 Technology Edition, v5.0
 * (C) Copyright IBM Corp. 1998, 2005. All Rights Reserved
 *
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 * ===========================================================================
 */

/*
 * ===========================================================================
 (C) Copyright Sun Microsystems Inc, 1992, 2004. All rights reserved.
 * ===========================================================================
 */





/*
 * @(#)SelectTx.java	1.27 02/06/13
 */

package java2d.demos.Transforms;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import javax.swing.*;
import java2d.AnimatingControlsSurface;
import java2d.CustomControls;


/**
 * Scaling or Shearing or Rotating an image & rectangle.
 */
public class SelectTx extends AnimatingControlsSurface {

    protected static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int XMIDDLE = 2;
    private static final int DOWN = 3;
    private static final int UP = 4;
    private static final int YMIDDLE = 5;
    private static final int XupYup = 6;
    private static final int XdownYdown = 7;
    private static final String[] title = { "Scale" , "Shear", "Rotate" };
    private Image img, original;
    private int iw, ih;
    protected static final int SCALE = 0;
    protected static final int SHEAR = 1;
    protected static final int ROTATE = 2;
    protected int transformType = SHEAR;
    protected double sx, sy;
    protected double angdeg;
    protected int direction = RIGHT;
    protected int transformToggle;


    public SelectTx() {
        setBackground(Color.white);
        original = getImage("painting.gif");
        iw = original.getWidth(this);
        ih = original.getHeight(this);
        setControls(new Component[] { new DemoControls(this) });
    }


    public void reset(int w, int h) {
        iw = w > 3 ? w/3 : 1;
        ih = h > 3 ? h/3 : 1;
        img = createImage(iw, ih);
	if (img == null) {
	    return;
	}
        Graphics big = img.getGraphics();
        big.drawImage(original, 0, 0, iw, ih, Color.orange, null);
        if (transformType == SCALE) {
            direction = RIGHT;
            sx = sy = 1.0;
        } else if (transformType == SHEAR) {
            direction = RIGHT;
            sx = sy = 0;
        } else {
            angdeg = 0;
        }
    }


    public void step(int w, int h) {
        int rw = iw + 10;
        int rh = ih + 10;

        if (transformType == SCALE && direction == RIGHT) {
            sx += .05;
            if (w * .5 - iw * .5 + rw * sx + 10 > w) {
                direction = DOWN;
            }
        } else if (transformType == SCALE && direction == DOWN) {
           sy += .05;
           if (h * .5 - ih * .5 + rh * sy + 20 > h) {
               direction = LEFT;
            }
        } else if (transformType == SCALE && direction == LEFT) {
            sx -= .05;
            if (rw * sx - 10 <= -(w * .5 - iw * .5)) {
                direction = UP;
            }
        } else if (transformType == SCALE && direction == UP) {
            sy -= .05;
            if (rh * sy - 20 <= -(h * .5 - ih * .5)) {
                direction = RIGHT;
                transformToggle = SHEAR;
            }
        }

        if (transformType == SHEAR && direction == RIGHT) {
            sx += .05;
            if (rw + 2 * rh * sx + 20 > w) {
                direction = LEFT;
                sx -= .1;
            }
        } else if (transformType == SHEAR && direction == LEFT) {
            sx -= .05;
            if (rw - 2 * rh * sx + 20 > w) {
                direction = XMIDDLE;
            }
        } else if (transformType == SHEAR && direction == XMIDDLE) {
            sx += .05;
            if (sx > 0) {
                direction = DOWN;
                sx = 0;
            }
        } else if (transformType == SHEAR && direction == DOWN) {
            sy -= .05;
            if (rh - 2 * rw * sy + 20 > h) {
                direction = UP;
                sy += .1;
            }
        } else if (transformType == SHEAR && direction == UP) {
            sy += .05;
            if (rh + 2 * rw * sy + 20 > h) {
                direction = YMIDDLE;
            }
        } else if (transformType == SHEAR && direction == YMIDDLE) {
            sy -= .05;
            if (sy < 0) {
                direction = XupYup;
                sy = 0;
            }
        } else if (transformType == SHEAR && direction == XupYup) {
            sx += .05; sy += .05;
            if (rw + 2 * rh * sx + 30 > w || rh + 2 * rw * sy + 30 > h) {
                direction = XdownYdown;
            }
        } else if (transformType == SHEAR && direction == XdownYdown) {
            sy -= .05; sx -= .05;
            if (sy < 0) {
                direction = RIGHT;
                sx = sy = 0.0;
                transformToggle = ROTATE;
            }
        }

        if (transformType == ROTATE) {
            angdeg += 5;
            if (angdeg == 360) { 
                angdeg = 0;
                transformToggle = SCALE;
            }
        }
    }


    public void render(int w, int h, Graphics2D g2) {

        Font font = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        TextLayout tl = new TextLayout(title[transformType], font, frc);
        g2.setColor(Color.black);
        tl.draw(g2, (float) (w/2-tl.getBounds().getWidth()/2), 
            (float) (tl.getAscent()+tl.getDescent()));

        if (transformType == ROTATE) {
            String s = Double.toString(angdeg);
            g2.drawString("angdeg=" + s, 2, h-4);
        } else {
            String s = Double.toString(sx);
            s = (s.length() < 5) ? s : s.substring(0,5);
            TextLayout tlsx = new TextLayout("sx=" + s, font, frc);
            tlsx.draw(g2, 2, h-4);

            s = Double.toString(sy);
            s = (s.length() < 5) ? s : s.substring(0,5);
            g2.drawString("sy=" + s,(int)(tlsx.getBounds().getWidth()+4), h-4);
        }

        if (transformType == SCALE) {
            g2.translate(w/2-iw/2, h/2-ih/2);
            g2.scale(sx, sy);
        } else if (transformType == SHEAR) {
            g2.translate(w/2-iw/2,h/2-ih/2);
            g2.shear(sx, sy);
        } else {
            g2.rotate(Math.toRadians(angdeg),w/2,h/2);
            g2.translate(w/2-iw/2,h/2-ih/2);
        }
        
        g2.setColor(Color.orange);
        g2.fillRect(0, 0, iw+10, ih+10);
        g2.drawImage(img, 5, 5, this);
    }


    public static void main(String argv[]) {
        createDemoFrame(new SelectTx());
    }


    static class DemoControls extends CustomControls implements ActionListener {

        SelectTx demo;
        JToolBar toolbar;

        public DemoControls(SelectTx demo) {
            super(demo.name);
            this.demo = demo;
            setBackground(Color.gray);
            add(toolbar = new JToolBar());
            toolbar.setFloatable(false);
            addTool("Scale", false);
            addTool("Shear", true);
            addTool("Rotate", false);
        }

        public void addTool(String str, boolean state) {
            JToggleButton b = (JToggleButton) toolbar.add(new JToggleButton(str));
            b.setFocusPainted(false);
            b.setSelected(state);
            b.addActionListener(this);
            int width = b.getPreferredSize().width;
            Dimension prefSize = new Dimension(width, 21);
            b.setPreferredSize(prefSize);
            b.setMaximumSize(prefSize);
            b.setMinimumSize(prefSize);
        }


        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < toolbar.getComponentCount(); i++) {
                JToggleButton b = (JToggleButton) toolbar.getComponentAtIndex(i);
                b.setSelected(false);
            }
            JToggleButton b = (JToggleButton) e.getSource();
            b.setSelected(true);
            if (b.getText().equals("Scale")) {
                demo.transformType = demo.SCALE;
                demo.direction = demo.RIGHT;
                demo.sx = demo.sy = 1;
            } else if (b.getText().equals("Shear")) {
                demo.transformType = demo.SHEAR;
                demo.direction = demo.RIGHT;
                demo.sx = demo.sy = 0;
            } else if (b.getText().equals("Rotate")) {
                demo.transformType = demo.ROTATE;
                demo.angdeg = 0;
            }
        }

        public Dimension getPreferredSize() {
            return new Dimension(200,39);
        }


        public void run() {
            Thread me = Thread.currentThread();
            demo.transformToggle = demo.transformType;
            while (thread == me) {
                try {
                    thread.sleep(222);
                } catch (InterruptedException e) { return; }
                if (demo.transformToggle != demo.transformType) {
                    java2d.Java2Demo.doClickOnDispatchThread((AbstractButton) toolbar.getComponentAtIndex(demo.transformToggle));
                }
            }
            thread = null;
        }
    } // End DemoControls class
} // End SelectTx class
