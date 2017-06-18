package reader;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

public class list {

	ArrayList<node> al=new ArrayList();
	public boolean contains(node root) {
		return(al.contains(root));
	}

	public void add(node root) {
		al.add(root);
	}

	public node get(int i) {
		Iterator<node> itr=al.iterator();
		if(al.size()>i)
		{for(int n=0;n<i;n++)
			itr.next();
		
		return itr.next();}
		return null;
	}
	
	public int getIndex(node root) {
		Iterator<node> itr=al.iterator();
		int n;
		if(al.contains(root))
			{for(n=0;root!=itr.next();n++);
				
				return n;
			}
		else
			return -1;
	}

	public boolean IsNull() {
		return al.isEmpty();
	}

	
}
