package GiaThuan10;

import java.io.Serializable;
import java.util.ArrayList;

public class QuanLy implements Serializable{
private ArrayList<NhanVien> ds;

public QuanLy() {
	ds = new ArrayList(10);
}
public  ArrayList<NhanVien> getDs() {
	return ds;
}
public boolean them(NhanVien s) {
	if(ds.contains(s))
		return false;
	return ds.add(s);
}


}
