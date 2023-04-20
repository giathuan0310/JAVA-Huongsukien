package luyenon;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

public class Database {

	public Database() {
		super();
		// TODO Auto-generated constructor stub
	}
	
//	public void	saveFile(String fileName, Object o){
//		FileOutputStream fos = null;
//		ObjectOutputStream oos = null;
//		try{
//			fos = new FileOutputStream(fileName);
//			oos = new ObjectOutputStream(fos);
//			oos.writeObject(o);
//			oos.close();
//		}catch(Exception ex){
//			JOptionPane.showMessageDialog(null, "IO Error!");
//			return;
//		}
//	}
//	// Doc file
//	public Object readFile(String fileName) {
//		Object o = null;
//		FileInputStream fis = null;
//		ObjectInputStream ois = null;
//		try{
//			fis = new FileInputStream(fileName); 
//			ois = new ObjectInputStream(fis);
//			o = ois.readObject();
//			ois.close();
//		}catch(Exception ex){
//			JOptionPane.showMessageDialog(null, "IO Error!");
//		}
//		return o;
//	}
	

//}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
public void saveFile(String filename,Object o)	{
	FileOutputStream fos=null;
	ObjectOutputStream oos=null;
	try {
		fos=new FileOutputStream(filename);
		oos=new ObjectOutputStream(fos);
		oos.writeObject(o);
		oos.close();
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "Lỗi");
		return;
	}
}

	public Object readFile(String filename)	{
		Object o=null;
	
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		try {
			fis=new FileInputStream(filename);
			ois=new ObjectInputStream(fis);
			o=ois.readObject();
			ois.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi");
			
		}
		return o;

	}
}











