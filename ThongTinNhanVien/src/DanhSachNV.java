

import java.io.Serializable;
import java.util.ArrayList;

public class DanhSachNV implements Serializable
{
    private ArrayList<NhanVien> ls;

    public DanhSachNV(){
        ls = new ArrayList<>();
    }
    //Them Nhan Vien
    public boolean themNV(NhanVien nv){
    	//boolean edit =true;
    	///if(ls.contains(nv))
    	for(int i=0;i<ls.size();i++)
        if(ls.get(i).getMaNV().equalsIgnoreCase(nv.getMaNV()))
        {
        	return false;
        }
    	
    	ls.add(nv);
    	return true;
    }
    //Lay danh sach Nhan Vien
    public ArrayList<NhanVien> getLs(){
        return ls;
    }
    //Lay so Nhan Vien
    public int soNV(){
        return ls.size();
    }
    //Xoa vi tri nhan vien
    public boolean xoaViTri(int index){
        if(index >= 0 && index <= ls.size()-1){
            ls.remove(index);
            return true;
        }
        else
            return false;
    }
    public boolean suaNV(String manv, String honv, String tennv,String phai, int tuoi, Double luong){
        NhanVien nv = new NhanVien(manv,honv,tennv,phai,tuoi,luong);
        if(ls.contains(nv)){
            nv.setMaNV(manv);
            nv.setHoNV(honv);
            nv.setTenNV(tennv);
            nv.setPhai(phai);
            nv.setTuoi(tuoi);
            nv.setTienLuong(luong);
            return true;
        }
        else
            return false;
    }
    public boolean kiemTra(String manv){
        NhanVien nv = new NhanVien(manv);
        if(ls.contains(nv)){
            return true;
        }
        else
            return false;
    }
    public int timNhanvien(String manv){
        
    	for(int i=0;i<ls.size();i++)
    		if(ls.get(i).getMaNV().equalsIgnoreCase(manv))
            return i;
  
		return -1;
    }
}

