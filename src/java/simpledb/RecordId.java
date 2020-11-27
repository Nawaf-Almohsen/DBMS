package simpledb;

import java.io.Serializable;

/**
 * A RecordId is a reference to a specific tuple on a specific page of a
 * specific table.
 */
public class RecordId implements Serializable {

	// look at the constructor to identify class attributes

	private static final long serialVersionUID = 1L;

	PageId pid;
	int tupleno;

	/**
	 * Creates a new RecordId referßring to the specified PageId and tuple number.
	 * 
	 * @param pid     the pageid of the page on which the tuple resides
	 * @param tupleno the tuple number within the page.
	 */
	public RecordId(PageId pid, int tupleno) {
		// some code goes here
		this.pid = pid;
		this.tupleno = tupleno;

	}

	/**
	 * @return the tuple number this RecordId references.
	 */
	public int getTupleNumber() {
		// some code goes here
		return tupleno;
	}

	/**
	 * @return the page id this RecordId references.
	 */
	public PageId getPageId() {
		// some code goes here
		return pid;
	}

	/**
	 * Two RecordId objects are considered equal if they represent the same tuple.
	 * 
	 * @return True if this and o represent the same tuple
	 */
	@Override
	public boolean equals(Object o) {
		// some code goes here
		// check o type first. Return true if pageid and tupleNo are equal
	
		if(o==null)
			return false;
		
		if (o instanceof RecordId) {
			int temp = ((RecordId) o).getTupleNumber();
			
			if (temp ==this.tupleno)
				if(((RecordId) o).pid.equals(pid))
				return true;
		}
		
		
	
		return false;

	}

	/**
	 * You should implement the hashCode() so that two equal RecordId instances
	 * (with respect to equals()) have the same hashCode().
	 * 
	 * @return An int that is the same for equal RecordId objects.
	 */
	@Override
	public int hashCode() {
		// some code goes here

		String stringN = Integer.toString(pid.hashCode()) + Integer.toString(tupleno);

		return Integer.parseInt(stringN);

	}

}
