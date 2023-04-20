package luyenon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;

public class giaoDien extends JFrame implements ActionListener{
	JTextField txtMa,txtTen,txtSotrang,txtnhaxb,txtTim;
	JLabel lblMa,lblTen,lblSotrang,lblnhaxb,lblTheloai,lblTim;
	JComboBox cmbString;
	JButton bnThem,bnXoatrang,bnxoa,bnLuu,bnTim;
	
	private DefaultTableModel tableModel;
	JTable table;
	private danhsach book;
	
	private ArrayList<Sach> list;
	private Database database;

	public giaoDien()  {
		
		this.setTitle("Kiem tra giua ki");
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		
		database=new Database();
		book=new danhsach();
		try {
			loadData();
			//save();
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		JPanel pnNorth;
		
		add(pnNorth=new JPanel(),BorderLayout.NORTH);
		
		JLabel title=new JLabel("Thông tin sách");
		title.setForeground(Color.BLUE);
		Font font =new Font("Arial", ABORT, 30);
		title.setFont(font);
		pnNorth.add(title);
		
		
		Box b=Box.createVerticalBox();
		Box b1,b2,b3,b4,b5,b6,b7;
		
		
		b.add(b1=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(lblMa=new JLabel("Mã Sách"));
		b1.add(txtMa=new JTextField());
		

		b.add(b2=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(lblTen=new JLabel("Tên Sách"));
		b2.add(txtTen=new JTextField());
		b2.add(lblSotrang=new JLabel("Số trang"));
		b2.add(txtSotrang=new JTextField());
		
		b.add(b3=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		String[] cmbStrings= {"Toan","Lý"};
		b3.add(lblTheloai=new JLabel("Thể loại"));
		b3.add(cmbString=new JComboBox(cmbStrings));
		
		b3.add(lblnhaxb=new JLabel("Nhà xuất bản"));
		b3.add(txtnhaxb=new JTextField());
		
		
		

		b.add(b5=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		String[] headers= "Mã Sách;Tên;Số trang;Thể loại;Nhà XB".split(";");
	
		tableModel=new DefaultTableModel(headers,0);
		JScrollPane scroll=new JScrollPane();
		scroll.setViewportView(table=new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		b5.add(scroll);
		
		b.add(b4=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b4.add(lblTim=new JLabel("Nhập mã sách cần tìm"));
		b4.add(txtTim=new JTextField());
		b4.add(bnTim=new JButton("Tìm"));
		b4.add(bnThem=new JButton("Thêm"));
		b4.add(bnXoatrang=new JButton("Xóa Trắng"));
		b4.add(bnxoa=new JButton("Xóa"));
		b4.add(bnLuu=new JButton("Lưu"));
		
		bnThem.addActionListener(this);
		bnXoatrang.addActionListener(this);
		bnxoa.addActionListener(this);
		bnLuu.addActionListener(this);
		bnTim.addActionListener(this);
		
		
		lblTen.setPreferredSize(lblMa.getPreferredSize());
		lblnhaxb.setPreferredSize(lblMa.getPreferredSize());

		lblSotrang.setPreferredSize(lblMa.getPreferredSize());
		lblTheloai.setPreferredSize(lblMa.getPreferredSize());
		lblTim.setPreferredSize(lblMa.getPreferredSize());
		
		
		
		add(b,BorderLayout.CENTER);
		
		
		
		
		
		
		
		
		
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	public static void main(String[] args) {
		danhsach book=new danhsach();
		new giaoDien();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		if(o.equals(bnXoatrang)) {
			Xoatrang();
		}else if(o.equals(bnxoa)) {
			xoa();
		}else if(o.equals(bnThem)) {
			them();
		}else if(o.equals(bnLuu)) {
			database.saveFile("Sach.dat", book);
		}else if(o.equals(bnTim))
			
        {
			
            int pos=book.timsach(txtTim.getText());
            if(pos!=-1)
            {
            	JOptionPane.showMessageDialog(null,"Có sách này!");
                        table.setRowSelectionInterval(pos, pos);
             }
            else
              JOptionPane.showMessageDialog(null,"Khong tim  sách co ID này!");
        }
		
	}
	
	public void Xoatrang() {
		txtMa.setText("");
		txtTen.setText("");
		txtSotrang.setText("");
		txtnhaxb.setText("");
		
		
		
	}
	public void xoa() {
		int a=table.getSelectedRow();
		if(a!=-1)
			tableModel.removeRow(a);
		database.saveFile("Sach.dat", book);
	}
	
	public void them() {
		try {
			String ma=txtMa.getText();
			String Ten=txtTen.getText();
			String Nhaxb=txtnhaxb.getText();
			String TheLoai=(String)cmbString.getSelectedItem();
			int sotrang=Integer.parseInt(txtSotrang.getText());
			Sach s=new Sach(ma, Ten, TheLoai, Nhaxb, sotrang);
		
			danhsach ds=new danhsach();
			if(ds.Them(s)) {
				String[] row= {ma, Ten, TheLoai, Nhaxb,Integer.toString(sotrang) };
				tableModel.addRow(row);
				JOptionPane.showMessageDialog(null,"Them Thanh Cong !");
				//database.saveFile("Sach.dat", book);
			}else
			{
				JOptionPane.showMessageDialog(null, "Lỗi Thêm");
			}
			
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Lỗi!");
		}
		
		//database.saveFile("Sach.dat", book);
	}
	
	
	public void loadData() throws Exception{
    	//ds=null;
        //cach 1
    	//ds = databasee.read_NV("Nhanvien.txt");
        // Cach 2
    	book = (danhsach)database.readFile("Sach.dat");
    	if(book==null)
    	{
    		book =new danhsach();
    	}
    	else
    		
    	{for(Sach s : book.getList()){
            String[] row = {s.getMa()+"",s.getTen(),s.getNhaxb(),s.getSotrang()+"",s.getTheloai()};
            tableModel.addRow(row);
    	}
    }
    }
//	public void save() throws Exception{
//        database.saveFile("Sach.dat",book);
//    }
	
	

}
