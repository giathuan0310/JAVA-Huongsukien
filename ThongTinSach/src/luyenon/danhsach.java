package luyenon;

import java.awt.Container;
import java.io.Serializable;
import java.util.ArrayList;

public class danhsach implements Serializable{
	private ArrayList<Sach> list;

	public danhsach() {
		list=new ArrayList<Sach>();
	}
	
	public Boolean Them(Sach s) {
		if(list.contains(s))
			return false;
			list.add(s);
			return true;
	}

	public ArrayList<Sach> getList() {
		return list;
	}
	 public int timsach(String ma){
	        
	    	for(int i=0;i<list.size();i++)
	    		if(list.get(i).getMa().equalsIgnoreCase(ma))
	            return i;
	  
			return -1;
	    }
	
	
}
