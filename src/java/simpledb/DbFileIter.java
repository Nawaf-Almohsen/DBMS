package simpledb;

import java.util.Iterator;
import java.util.List;

public class DbFileIter extends AbstractDbFileIterator {
	List<Tuple> list;
	Iterator<Tuple> it;
	boolean closed;
	public DbFileIter(List<Tuple> l) {
		list=l;
		closed=false;
	}
	@Override
	public void rewind() throws DbException, TransactionAbortedException {
		it = list.iterator();

	}

	@Override
	public void open() throws DbException, TransactionAbortedException {
		it = list.iterator();

	}

	@Override
	protected Tuple readNext() throws DbException, TransactionAbortedException {
		if (closed)
			return null;
		if (it == null || !it.hasNext())
			return null;
		return it.next();
	}
	@Override
	public void close() {
		super.close();
		list.clear();
		closed=true;
	}


}
