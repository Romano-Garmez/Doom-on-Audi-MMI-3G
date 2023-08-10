/*
 * @(#)src/demo/jni/FileStatDemo.java, dadev, dxdev, 20061221 1.6
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




public class FileStatDemo
{
	public static void main(String args[])
	{
		try
		{
			System.out.println(new FileStat(args[0]));
		}catch(ArrayIndexOutOfBoundsException ae)
		{
			System.out.println("Usage: java FileStatDemo <file> ");
			System.exit(1);
		}catch(FileStatException fe)
		{
			System.out.println(fe);
		}
	}
}
	