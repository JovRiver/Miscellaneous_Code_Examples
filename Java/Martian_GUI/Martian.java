package martian_gui;

/**
 * This abstract class models a Martian.
 * @author dgibson
 *
 */
public abstract class Martian implements Cloneable, Comparable<Martian> {

	/**
	 * Every Martian has an id which is simply an integer.
	 */
	private int id;

	/**
	 * Creates a Martian with the specified <code>id</code> and sets the <code>volume</code> to 1
	 * @param id
	 */
	public Martian(int id) {
		this.id = id;
	}

	/**
	 * Two Martians are equal if their <code>id</code>’s are equal, regardless of whether they are <code>GreenMartian</code>s or <code>RedMartians</code>s.
	 */
	@Override
	public boolean equals(Object o) {
		Martian m = (Martian)o;
		if(id == m.getId())
			return true;
		else
			return false;
	}

	/**
	 * Returns the <code>id</code> for the Martian.
	 * @return The id for the Martian.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Martian subclasses must implement this method to speak in the way that makes sense for them.
	 * @return The string that the Martian speaks.
	 */
	public abstract String speak();

	/**
	 * Returns a string like: “id=xxx, vol=yyy”, where xxx is the <code>id</code> and yyy is the <code>volume</code>.
	 */
	public void String toString() {
		String msg = String.format("Martian id=%d", id);
		return msg;
	}

	/**
	 * Implelements Java’s <code>compareTo</code> so that Martians (either red or green) are compared based on
	 * their <code>id</code>’s which results in the usual ascending sorted order.
	 *
	 */
	@Override
	public int compareTo(Martian m) {
		return this.getId() - m.getId() ;
	}
}