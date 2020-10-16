package simpledb;

import java.io.*;
import java.util.*;

/**
 * HeapFile is an implementation of a DbFile that stores a collection of tuples
 * in no particular order. Tuples are stored on pages, each of which is a fixed
 * size, and the file is simply a collection of those pages. HeapFile works
 * closely with HeapPage. The format of HeapPages is described in the HeapPage
 * constructor.
 * 
 * @see simpledb.HeapPage#HeapPage
 * @author Sam Madden
 */
public class HeapFile implements DbFile {
   // create class attributes to maintain info of HeapFile

	/**
	 * Constructs a heap file backed by the specified file.
	 * 
	 * @param f the file that stores the on-disk backing store for this heap file.
	 */
	public HeapFile(File f, TupleDesc td) {
		// some code goes here
		/*
		 * initialize HeapFile info
		 * create PageIds based on the possible number of pages.
		 * The number of pages must be computed based on file size and page size in bytes
		 */
		
	}

	/**
	 * Returns the File backing this HeapFile on disk.
	 * 
	 * @return the File backing this HeapFile on disk.
	 */
	public File getFile() {
		// some code goes here
		return null;
	}

	/**
	 * Returns an ID uniquely identifying this HeapFile. Implementation note: you
	 * will need to generate this tableid somewhere to ensure that each HeapFile has
	 * a "unique id," and that you always return the same value for a particular
	 * HeapFile. We suggest hashing the absolute file name of the file underlying
	 * the heapfile, i.e. f.getAbsoluteFile().hashCode().
	 * 
	 * @return an ID uniquely identifying this HeapFile.
	 */
	public int getId() {
		// some code goes here
		//suggest hashing the absolute file name of the file underlying
		// the heapfile, i.e. f.getAbsoluteFile().hashCode()
		
		 throw new UnsupportedOperationException("implement this");
	}

	/**
	 * Returns the TupleDesc of the table stored in this DbFile.
	 * 
	 * @return TupleDesc of this DbFile.
	 */
	public TupleDesc getTupleDesc() {
		// some code goes here
		
		 throw new UnsupportedOperationException("implement this");
	}

	// see DbFile.java for javadocs
	public Page readPage(PageId pid) {
		// some code goes here
		/*
		 * Check that the requested pid = the current file id
		 * create empty buffer with HeapPage.createEmptyPageData();
		 * Read the file with random access and seek to the specified location of pid
		 * Fill the buffer and return a new page containing it.
		 */
		return null;
		
	}

	// see DbFile.java for javadocs
	public void writePage(Page page) throws IOException {
		// some code goes here
		// not necessary for lab1
		//required fro lab2
		/*
		 * check if page is dirty
		 * Open file with RandomAccessFile
		 * skipBytes to the appropriate offset
		 * Write pageData 
		 */
	}

	/**
	 * Returns the number of pages in this HeapFile.
	 */
	public int numPages() {
		// some code goes here
		// return the computed number of pages for this file.
		return 0;

	}

	// see DbFile.java for javadocs
	public ArrayList<Page> insertTuple(TransactionId tid, Tuple t)
			throws DbException, IOException, TransactionAbortedException {
		// some code goes here
		return null;
		// not necessary for lab1
		/*
		 * loop pageIds
		 * Get page using Database.getBufferPool().getPage
		 * Check for page with empty slots
		 * insert tuple in the page
		 * if no page with empty slots, create a new page
		 * Add pageId of new page and increase the number of pages
		 * insert the tuple in the page and write the page to disk
		 * Return a list containing the page of inserted tuple
		 */
	}

	// see DbFile.java for javadocs
	public ArrayList<Page> deleteTuple(TransactionId tid, Tuple t) throws DbException, TransactionAbortedException {
		// some code goes here
		return null;
		// not necessary for lab1
		/*
		 * Get page of deleted tuple using Database.getBufferPool().getPage
		 * delete tuple from page
		 * return a list containing the page of deleted tuple
		 */
	}

	// see DbFile.java for javadocs
	public DbFileIterator iterator(TransactionId tid) {
		// some code goes here
		/*
		 * 
		 * read all pages using Database.getBufferPool().getPage
		 * Use the iterator implemented in the pages to get all tuples
		 * return an iterator that contain all tuples using DbFileIter as wrapper class
		 */
		
	
		return null;
	}

}
