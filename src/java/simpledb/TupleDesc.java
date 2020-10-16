package simpledb;

import java.io.Serializable;
import java.util.*;

/**
 * TupleDesc describes the schema of a tuple.
 */
public class TupleDesc implements Serializable {

//1- create a list of TDItems
	/**
	 * A help class to facilitate organizing the information of each field
	 */
	public static class TDItem implements Serializable {

		private static final long serialVersionUID = 1L;

		/**
		 * The type of the field
		 */
		public final Type fieldType;

		/**
		 * The name of the field
		 */
		public final String fieldName;

		public TDItem(Type t, String n) {
			this.fieldName = n;
			this.fieldType = t;
		}

		public String toString() {
			return fieldName + "(" + fieldType + ")";
		}
	}

	/**
	 * @return An iterator which iterates over all the field TDItems that are
	 *         included in this TupleDesc
	 */

	private ArrayList<TDItem> tdItems;

	public Iterator<TDItem> iterator() {
		return tdItems.iterator();

	}

	private static final long serialVersionUID = 1L;

	/**
	 * Create a new TupleDesc with typeAr.length fields with fields of the specified
	 * types, with associated named fields.
	 * 
	 * @param typeAr  array specifying the number of and types of fields in this
	 *                TupleDesc. It must contain at least one entry.
	 * @param fieldAr array specifying the names of the fields. Note that names may
	 *                be null.
	 */
	public TupleDesc(Type[] typeAr, String[] fieldAr) {
	
			tdItems = new ArrayList<TDItem>(typeAr.length);
			for (int i = 0; i < tdItems.size(); i++) {
				TDItem item = new TDItem(typeAr[i], fieldAr[i]);
				tdItems.add(item);

			}
		
		

	}

	/**
	 * Constructor. Create a new tuple desc with typeAr.length fields with fields of
	 * the specified types, with anonymous (unnamed) fields.
	 * 
	 * @param typeAr array specifying the number of and types of fields in this
	 *               TupleDesc. It must contain at least one entry.
	 */
	public TupleDesc(Type[] typeAr) {
		if (typeAr.length >= 1) {
			tdItems = new ArrayList<TDItem>(typeAr.length);
			for (int i = 0; i < tdItems.size(); i++) {
				TDItem item = new TDItem(typeAr[i], "unnamed");
				tdItems.add(item);

			}

		} else {
			System.out.print("the typeAr is empty");
		}

	}

	/**
	 * @return the number of fields in this TupleDesc
	 */
	public int numFields() {
		return tdItems.size();
	}

	/**
	 * Gets the (possibly null) field name of the ith field of this TupleDesc.
	 * 
	 * @param i index of the field name to return. It must be a valid index.
	 * @return the name of the ith field
	 * @throws NoSuchElementException if i is not a valid field reference.
	 */
	public String getFieldName(int i) throws NoSuchElementException {

		if (i < 0 || i >= tdItems.size())
			throw new NoSuchElementException();

		return tdItems.get(i).fieldName;
	}

	/**
	 * Gets the type of the ith field of this TupleDesc.
	 * 
	 * @param i The index of the field to get the type of. It must be a valid index.
	 * @return the type of the ith field
	 * @throws NoSuchElementException if i is not a valid field reference.
	 */
	public Type getFieldType(int i) throws NoSuchElementException {

		if (i < 0 || i >= tdItems.size())
			throw new NoSuchElementException();

		return tdItems.get(i).fieldType;
	}

	/**
	 * Find the index of the field with a given name.
	 * 
	 * @param name name of the field.
	 * @return the index of the field that is first to have the given name.
	 * @throws NoSuchElementException if no field with a matching name is found.
	 */
	public int fieldNameToIndex(String name) throws NoSuchElementException {
		// some code goes here
		// 6- return the index of item with name
		// throw NoSuchElementException if not exist

		for (int i = 0; i < tdItems.size(); i++) {
			if (tdItems.get(i).fieldName.equalsIgnoreCase(name)) {
				return i;
			}
		}
		throw new NoSuchElementException();

	}

	/**
	 * @return The size (in bytes) of tuples corresponding to this TupleDesc. Note
	 *         that tuples from a given TupleDesc are of a fixed size.
	 */
	public int getSize() {

		int size = 0;
		for (int i = 0; i < tdItems.size(); i++) {
			size += tdItems.get(i).fieldType.getLen();

		}
		return size;
	}

	/**
	 * Merge two TupleDescs into one, with td1.numFields + td2.numFields fields,
	 * with the first td1.numFields coming from td1 and the remaining from td2.
	 * 
	 * @param td1 The TupleDesc with the first fields of the new TupleDesc
	 * @param td2 The TupleDesc with the last fields of the TupleDesc
	 * @return the new TupleDesc
	 */
	public static TupleDesc merge(TupleDesc td1, TupleDesc td2) {
		Type typear[] = new Type[td1.getSize() + td2.getSize()];
		String typef[] = new String[td1.getSize() + td2.getSize()];

		for (int i = 0; i < td1.getSize(); i++) {
			typear[i] = td1.tdItems.get(i).fieldType;
			typef[i] = td1.tdItems.get(i).fieldName;

		}

		for (int i = 0; i < td2.getSize(); i++) {
			typear[i] = td2.tdItems.get(i).fieldType;
			typef[i] = td2.tdItems.get(i).fieldName;

		}

		return new TupleDesc(typear, typef);
		// some code goes here
		// 8- Create new TupleDesc that merge both td1 and td2
	}

	/**
	 * Compares the specified object with this TupleDesc for equality. Two
	 * TupleDescs are considered equal if they have the same number of items and if
	 * the i-th type in this TupleDesc is equal to the i-th type in o for every i.
	 * 
	 * @param o the Object to be compared for equality with this TupleDesc.
	 * @return true if the object is equal to this TupleDesc.
	 */

	public boolean equals(Object o) {
		if (o instanceof TupleDesc) {
			TupleDesc temp = (TupleDesc) o;
			if (temp.getSize() == getSize()) {
				for (int i = 0; i < getSize(); i++) {
					if (temp.tdItems.get(i).fieldType != tdItems.get(i).fieldType)
						return false;

				}
				return true;
			}

		}
		return false;

	}

	public int hashCode() {
		// not required for lab1
		// If you want to use TupleDesc as keys for HashMap, implement this so
		// that equal objects have equals hashCode() results
		throw new UnsupportedOperationException("unimplemented");
	}

	/**
	 * Returns a String describing this descriptor. It should be of the form
	 * "fieldType[0](fieldName[0]), ..., fieldType[M](fieldName[M])", although the
	 * exact format does not matter.
	 * 
	 * @return String describing this descriptor.
	 */
	public String toString() {
		String dis = "";
		for (int i = 0; i < tdItems.size(); i++) {
			dis += "Filed Type: " + tdItems.get(i).fieldType + "\t" + "Filed Name: " + tdItems.get(i).fieldName + "\n";
		}
		return dis;
	}
}
