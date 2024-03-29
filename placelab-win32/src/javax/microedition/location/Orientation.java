

/*
 * NOT YET IMPLEMENTED & PROBABLY NEVER WILL BE - JUST A PLACE HOLDER
 */

package javax.microedition.location;

/**
 * The Orientation class represents the physical orientation of the terminal.
 * Orientation is described by azimuth to north (the horizontal pointing
 * direction), pitch (the vertical elevation angle) and roll (the rotation of
 * the terminal around its own longitudinal axis).
 * 
 * It is not expected that all terminals will support all of these parameters.
 * If a terminal supports getting the Orientation, it MUST provide the compass
 * azimuth information. Providing the pitch and roll is optional. Most commonly,
 * this class will be used to obtain the current compass direction.
 * 
 * It is up to the terminal to define its own axes, but it is generally
 * recommended that the longitudinal axis is aligned with the bottom-to-top
 * direction of the screen. This means that the pitch is positive when the top
 * of the screen is up and the bottom of the screen down (when roll is zero).
 * The roll is positive when the device is tilted clockwise looking from the
 * direction of the bottom of the screen, i.e. when the left side of the screen
 * is up and the right side of the screen is down (when pitch is zero).
 * 
 * No accuracy data is given for Orientation.
 * 
 * This class is only a container for the information. The constructor does not
 * validate the parameters passed in but just retains the values. The get*
 * methods return the values passed in the constructor. When the platform
 * implementation returns Orientation objects, it MUST ensure that it only
 * returns objects where the parameters have values set as described for their
 * semantics in this class.
 */
public class Orientation {
	
	private float azimuth;
	private boolean isMagnetic;
	private float pitch;
	private float roll;
	
	/**
	 * Constructs a new Orientation object with the compass azimuth, pitch and
	 * roll parameters specified.
	 * 
	 * The values are expressed in degress using floating point values.
	 * 
	 * If the pitch or roll is undefined, the parameter shall be given as
	 * Float.NaN.
	 * 
	 * @param azimuth
	 *            the compass azimuth relative to true or magnetic north. Valid
	 *            range: [0.0, 360.0). For example, value 0.0 indicates north,
	 *            90.0 east, 180.0 south and 270.0 west.
	 * @param isMagnetic
	 *            boolean stating whether the compass azimuth is given as
	 *            relative to the magnetic field of the Earth (=true) or to true
	 *            north and gravity (=false)
	 * @param pitch
	 *            the pitch of the terminal in degrees. Valid range: [-90.0,
	 *            90.0]
	 * @param roll
	 *            the roll of the terminal in degrees. Valid range: [-180.0,
	 *            180.0)
	 */
	public Orientation(float azimuth, boolean isMagnetic, float pitch,
			float roll) {
		this.azimuth = azimuth;
		this.isMagnetic = isMagnetic;
		this.pitch = pitch;
		this.roll = roll;
	}
	
	/**
	 * Returns the terminal's horizontal compass azimuth in degrees relative to
	 * either magnetic or true north. The value is always in the range [0.0,
	 * 360.0) degrees. The isOrientationMagnetic() method indicates whether the
	 * returned azimuth is relative to true north or magnetic north.
	 * 
	 * @return the terminal's compass azimuth in degrees relative to true or
	 *         magnetic north
	 * @see #isOrientationMagnetic()
	 */
	public float getCompassAzimuth() {
		return azimuth;
	}

	/**
	 * Returns a boolean value that indicates whether this Orientation is
	 * relative to the magnetic field of the Earth or relative to true north and
	 * gravity. If this method returns true, the compass azimuth and pitch are
	 * relative to the magnetic field of the Earth. If this method returns
	 * false, the compass azimuth is relative to true north and pitch is
	 * relative to gravity.
	 * 
	 * @return true if this Orientation is relative to the magnetic field of the
	 *         Earth; false if this Orientation is relative to true north and
	 *         gravity
	 * @see #getCompassAzimuth()
	 */
	public boolean isOrientationMagnetic() {
		return isMagnetic;
	}

	/**
	 * Returns the terminal's tilt in degrees defined as an angle in the
	 * vertical plane orthogonal to the ground, and through the longitudinal
	 * axis of the terminal. The value is always in the range [-90.0, 90.0]
	 * degrees. A negative value means that the top of the terminal screen is
	 * pointing towards the ground.
	 * 
	 * @return the terminal's pitch in degrees or Float.NaN if not available
	 */
	public float getPitch() {
		return pitch;
	}

	/**
	 * Returns the terminal's rotation in degrees around its own longitudinal
	 * axis. The value is always in the range [-180.0, 180.0) degrees. A
	 * negative value means that the terminal is orientated anti-clockwise from
	 * its default orientation, looking from direction of the bottom of the
	 * screen.
	 * 
	 * @return the terminal's roll in degrees or Float.NaN if not available
	 */
	public float getRoll() {
		return roll;
	}

	/**
	 * Returns the terminal's current orientation.
	 * 
	 * @return returns an Orientation object containing the terminal's current
	 *         orientation or null if the orientation can't be currently
	 *         determined
	 * @throws LocationException
	 *             if the implementation does not support orientation
	 *             determination
	 * @throws java.lang.SecurityException
	 *             if the calling application does not have a permission to
	 *             query the orientation
	 * @see Orientation
	 */
	public static Orientation getOrientation() throws LocationException {
		throw new LocationException ("Orientation not supported.");
	}
}
