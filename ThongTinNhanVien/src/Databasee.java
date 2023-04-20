



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Databasee {
    public Databasee(){
        super();
    }
    
//    // Cach 01 luu thanh file text
//    // Doc file
//    public DanhSachNV read_NV(String part) throws Exception{
//        DanhSachNV ds = new DanhSachNV();
//        File f = new File(part);
//        if(f.exists()){
//            Scanner sc = new Scanner(f);
//            while (sc.hasNextLine()){// doc tung dong
//                String line = sc.nextLine();
//                String []data = line.split(","); // tach thuoc tinh 
//                NhanVien nv = new NhanVien(data[0],data[1],data[2],data[3],Integer.parseInt(data[4]),Double.parseDouble(data[5]));
//                ds.themNV(nv);
//            }
//            sc.close();
//        }
//        else{
//            f.createNewFile();
//        }
//        return ds;
//    }
//    // Ghi file
//    public void writeNV(String part, DanhSachNV ds) throws Exception{
//        try(
//        		PrintWriter out = new PrintWriter(new FileOutputStream(part),true)){
//            for(NhanVien nv : ds.getLs())
//            {
//                String data = nv.getMaNV()+","+nv.getHoNV()+","+nv.getTenNV()+","+nv.getPhai()+","+nv.getTuoi()+","+nv.getTienLuong();
//                out.println(data);// tung dong
//            }
//        }
//    }
    
    // Cach 2
    // Ghi file
    
    public void	saveFile(String fileName, Object o){
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try{
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(o);
			oos.close();
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "IO Error!");
			return;
		}
	}
	// Doc file
	public Object readFile(String fileName) {
		Object o = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try{
			fis = new FileInputStream(fileName); 
			ois = new ObjectInputStream(fis);
			o = ois.readObject();
			ois.close();
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "IO Error!");
		}
		return o;
	}
    
    
    
}
