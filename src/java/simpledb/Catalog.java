package simpledb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.UUID;
/**
 * The Catalog keeps track of all available tables in the database and their
 * associated schemas. For now, this is a stub catalog that must be populated
 * with tables by a user program before it can be used -- eventually, this
 * should be converted to a catalog that reads a catalog table from disk.
 * 
 * @Threadsafe
 */
public class Catalog {
	// 1- create data structures to maintain mapping of id and name to table info.
	// Table info include: DbFile, name and PKey

	/**
	 * Constructor. Creates a new, empty catalog.
	 */

	private static class info {
		DbFile file;
		String name;
		String pkey;
		int fid;

		info(DbFile file, String name, String pkey, int id) {
			this.file = file;
			this.name = name;
			this.pkey = pkey;
			this.fid = id;
		}
	}

	HashMap<Integer, info> idToinfo;
	HashMap<String, info> nameToinfo;
	int counter=0;
	public Catalog() {
		idToinfo = new HashMap<Integer, info>();
		nameToinfo = new HashMap<String, info>();


		// some code goes here
	}

	/**
	 * Add a new table to the catalog. This table's contents are stored in the
	 * specified DbFile.
	 * 
	 * @param file      the contents of the table to add; file.getId() is the
	 *                  identfier of this file/tupledesc param for the calls
	 *                  getTupleDesc and getFile
	 * @param name      the name of the table -- may be an empty string. May not be
	 *                  null. If a name conflict exip;sts, use the last table to be
	 *                  added as the table for a given name.
	 * @param pkeyField the name of the primary key field
	 */
	public void addTable(DbFile file, String name, String pkeyField) {
		// some code goes here
		// 1- add a table to the catalog;
		info inf = new info(file, name, pkeyField,file.getId());
		
		if(counter==0) {
		idToinfo = new HashMap<Integer, info>();
		nameToinfo = new HashMap<String, info>();
		counter++;
		}

		idToinfo.put(file.getId(),inf);



		nameToinfo.put(name, inf);


	}

	public void addTable(DbFile file, String name) {
		addTable(file, name, "");
	}

	/**
	 * Add a new table to the catalog. This table has tuples formatted using the
	 * specified TupleDesc and its contents are stored in the specified DbFile.
	 * 
	 * @param file the contents of the table to add; file.getId() is the identfier
	 *             of this file/tupledesc param for the calls getTupleDesc and
	 *             getFile
	 */
	public void addTable(DbFile file) {
		addTable(file, (UUID.randomUUID()).toString());
	}

	/**
	 * Return the id of the table with a specified name,
	 * 
	 * @throws NoSuchElementException if the table doesn't exist
	 */
	public int getTableId(String name) throws NoSuchElementException {
		// some code goes here
		// get table file id from its name
		// if id not exist in catalog, throw NoSuchElementException
		if (nameToinfo.get(name) == null)
			throw new NoSuchElementException();

		return nameToinfo.get(name).fid;
	}

	/**
	 * Returns the tuple descriptor (schema) of the specified table
	 * 
	 * @param tableid The id of the table, as specified by the DbFile.getId()
	 *                function passed to addTable
	 * @throws NoSuchElementException if the table doesn't exist
	 */
	public TupleDesc getTupleDesc(int tableid) throws NoSuchElementException {
		// some code goes here
		// return TupleDesc given tableid, throw exception if not exist
		if (idToinfo.get(tableid) == null)
			throw new NoSuchElementException();

		return idToinfo.get(tableid).file.getTupleDesc();

	}

	/**
	 * Returns the DbFile that can be used to read the contents of the specified
	 * table.
	 * 
	 * @param tableid The id of the table, as specified by the DbFile.getId()
	 *                function passed to addTable
	 */
	public DbFile getDatabaseFile(int tableid) throws NoSuchElementException {
		// some code goes here
		// return file given tableid, throw exception if not exist
		if(idToinfo.get(tableid) == null)
			throw new NoSuchElementException();
		return idToinfo.get(tableid).file;
	}

	public String getPrimaryKey(int tableid) {
		// some code goes here
		// return Pkey given tableid, throw exception if not exist
		if(idToinfo.get(tableid) == null)
			throw new NoSuchElementException();
		return idToinfo.get(tableid).pkey;

	}

	public Iterator<Integer> tableIdIterator() {
		// some code goes here
		// return an iterator over all ids in catalog
		return idToinfo.keySet().iterator();
	}

	public String getTableName(int id) {
		if(idToinfo.get(id) == null)
			throw new NoSuchElementException();
		return idToinfo.get(id).name;
	}

	/** Delete all tables from the catalog */
	public void clear() {
		// some code goes here
		// clear the catalog
		idToinfo=null;
		nameToinfo=null;
		
	}

	/**
	 * Reads the schema from a file and creates the appropriate tables in the
	 * database.
	 * 
	 * @param catalogFile
	 */
	public void loadSchema(String catalogFile) {
		String line = "";
		String baseFolder = new File(new File(catalogFile).getAbsolutePath()).getParent();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(catalogFile)));

			while ((line = br.readLine()) != null) {
				// assume line is of the format name (field type, field type, ...)
				String name = line.substring(0, line.indexOf("(")).trim();
				// System.out.println("TABLE NAME: " + name);
				String fields = line.substring(line.indexOf("(") + 1, line.indexOf(")")).trim();
				String[] els = fields.split(",");
				ArrayList<String> names = new ArrayList<String>();
				ArrayList<Type> types = new ArrayList<Type>();
				String primaryKey = "";
				for (String e : els) {
					String[] els2 = e.trim().split(" ");
					names.add(els2[0].trim());
					if (els2[1].trim().toLowerCase().equals("int"))
						types.add(Type.INT_TYPE);
					else if (els2[1].trim().toLowerCase().equals("string"))
						types.add(Type.STRING_TYPE);
					else {
						System.out.println("Unknown type " + els2[1]);
						System.exit(0);
					}
					if (els2.length == 3) {
						if (els2[2].trim().equals("pk"))
							primaryKey = els2[0].trim();
						else {
							System.out.println("Unknown annotation " + els2[2]);
							System.exit(0);
						}
					}
				}
				Type[] typeAr = types.toArray(new Type[0]);
				String[] namesAr = names.toArray(new String[0]);
				TupleDesc t = new TupleDesc(typeAr, namesAr);
				HeapFile tabHf = new HeapFile(new File(baseFolder + "/" + name + ".dat"), t);
				addTable(tabHf, name, primaryKey);
				System.out.println("Added table : " + name + " with schema " + t);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Invalid catalog entry : " + line);
			System.exit(0);
		}
	}
}
