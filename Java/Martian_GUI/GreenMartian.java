
package martian_gui;

/**
 * This class models a GreenMartian which is a subclass of Martian
 * @author dgibson
 *
 */
public class GreenMartian extends Martian implements Teleporter {

	/**
	 *  Every Green Martian has an volume which is simply an integer.
	 */
	private int volume;

	/**
	 * Creates a GreenMartian with the specified <code>id</code>.
	 * @param id
	 */
	public GreenMartian(int id) {
		this(id, 1);
	}

	public GreenMartian(int id, int volume) {
		super(id);
		this.volume = volume;
	}

	/**
	 * Returns the <code>volume</code> for the Martian.
	 * @return The volume for the Martian.
	 */
	public int getVolume() {
		return volume;
	}

	/**
	 * Set the <code>volume</code> for the Martian
	 * @param volume
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}


	/**
	 * The GreenMartian implements the teleport method by returning a string like
	 * this: “id=xxx teleporting to <code>dest</code>”
	 * @param dest This is the destination for the teleportation and is used in the
	 * string that this method returns.
	 */
	@Override
	public String teleport(String dest) {
		return "id=" + getId() + " Teleporting to " + dest;
	}

	/**
	 * Returns a string like: “id=xxx, Grobldy Grock”, where xxx is the <code>id</code>.
	 */
	@Override
	public String speak() {
		return "Grubldy Grock" + ", id=" + getId() + ", vol=" + getVolume();
	}

	/**
	 * Returns a string like: “Green martian - id=xxx, vol=yyy”, where xxx is the <code>id</code> and yyy is the <code>volume</code>.
	 */
	@Override
	public String toString() {
		return "Green " + super.toString() + ", vol=" + volume;
	}
}