package simpledb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Tuple maintains information about the contents of a tuple. Tuples have a
 * specified schema specified by a TupleDesc object and contain Field objects
 * with the data for each field.
 */
public class Tuple implements Serializable {
	// 1- a tuple consists of an array of fields, TupleDesc and RecordId

	private static final long serialVersionUID = 1L;

	ArrayList<Field> fields;
	TupleDesc tupleDesc;
	RecordId recordId;

	/**
	 * Create a new tuple with the specified schema (type).
	 *
	 * @param td the schema of this tuple. It must be a valid TupleDesc instance
	 *           with at least one field.
	 */
	public Tuple(TupleDesc td) {
			fields = new ArrayList<Field>();
			tupleDesc = td;
		
		// some code goes here
		// 2- assign td and initialize the array of fields

	}

	/**
	 * @return The TupleDesc representing the schema of this tuple.
	 */
	public TupleDesc getTupleDesc() {
		return tupleDesc;
	}

	/**
	 * @return The RecordId representing the location of this tuple on disk. May be
	 *         null.
	 */
	public RecordId getRecordId() {

		return recordId;
	}

	/**
	 * Set the RecordId information for this tuple.
	 *
	 * @param rid the new RecordId for this tuple.
	 */
	public void setRecordId(RecordId rid) {
		recordId = rid;

	}

	/**
	 * Change the value of the ith field of this tuple.
	 *
	 * @param i index of the field to change. It must be a valid index.
	 * @param f new value for the field.
	 */
	public void setField(int i, Field f) {

		if (i < 0 || i >= fields.size())
			return;
		fields.add(i, f);

	}

	/**
	 * @return the value of the ith field, or null if it has not been set.
	 *
	 * @param i field index to return. Must be a valid index.
	 */
	public Field getField(int i) {
		if (i < 0 || i >= fields.size())
			return null;

		return fields.get(i);
	}

	/**
	 * Returns the contents of this Tuple as a string. Note that to pass the system
	 * tests, the format needs to be as follows:
	 *
	 * column1\tcolumn2\tcolumn3\t...\tcolumnN
	 *
	 * where \t is any whitespace (except a newline)
	 */
	public String toString() {
		String dis ="";
		for(int i=0;i<fields.size();i++) {
			if(i==fields.size()-1) {
				dis+=fields.toString();
				}
			else {
			dis+=fields.toString()+" ";
			}
		}	
		return dis;
	}

	/**
	 * @return An iterator which iterates over all the fields of this tuple
	 */
	public Iterator<Field> fields() {
		return fields.iterator();

	}

	/**
	 * reset the TupleDesc of this tuple (only affecting the TupleDesc)
	 */
	public void resetTupleDesc(TupleDesc td) {
		this.tupleDesc = td;
	}
}
