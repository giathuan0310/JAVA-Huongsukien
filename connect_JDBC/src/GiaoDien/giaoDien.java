package GiaoDien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

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
import javax.swing.table.DefaultTableModel;

import Dao.LopHoc_DAO;
import Lop_Sv.LopHoc;
import connectDB.ConnectDB;



public class giaoDien extends JFrame implements ActionListener{
	JLabel lblMalop,lbltenlop,lblGVCN;
	JTextField txtMalop,txtTenlop,txtGVCN;
	JComboBox cmbstring;
	JButton bnThem,bnLuu,bnSua,bnXoa,bnXemtt;
	private LopHoc_DAO lophoc;
	DefaultTableModel DataModel;
	JTable table;
	
	
	public  giaoDien() {
		
		this.setTitle("Lop Hoc");
		this.setSize(800,500);
		try {
			ConnectDB.getInstance().Connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JPanel pnNorth;
		add(pnNorth=new JPanel(),BorderLayout.NORTH);
		JLabel title =new JLabel("ThÃƒÂ´ng Tin Lop Hoc");
		
		Font font=new Font("Arial", ABORT, 30);
		title.setFont(font);
		pnNorth.add(title);
		
		
		
		Box b=Box.createVerticalBox();
		Box b1,b2,b3,b4,b5,b6;
		
		b.add(b5=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		String[] headers= "Malop;Tenlop;giaovienCN".split(";");
		DataModel=new DefaultTableModel(headers,0);
		JScrollPane scroll=new JScrollPane();
		
		scroll.setViewportView(table=new JTable(DataModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		b.add(scroll);
		
		
		
		JButton bntrai,bnphai;
		b.add(b6=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b6.add(bntrai=new JButton("<<"));
		b6.add(bntrai=new JButton("<"));
		b6.add(bnphai=new JButton(">"));
		b6.add(bnphai=new JButton(">>"));
	
		
		
		b.add(b1=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(lblMalop=new JLabel("Mã Lớp:"));
		b1.add(txtMalop=new JTextField());
		
		
		b.add(b2=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(lbltenlop=new JLabel("Tên Lớp:"));
		b2.add(txtTenlop=new JTextField());
		
		
		b.add(b3=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(lblGVCN=new JLabel("Giáo Viên Chủ Nhiệm:"));
		b3.add(txtGVCN=new JTextField());
		
		b.add(b4=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		
		
		b4.add(bnThem=new JButton("Them"));
		b4.add(bnLuu=new JButton("Luu"));
		b4.add(bnXoa=new JButton("Xoa"));
		b4.add(bnSua=new JButton("Sua"));
		b4.add(bnXemtt=new JButton("Xem thong tin sinh vien"));
		
		bnThem.addActionListener(this);
		bnLuu.addActionListener(this);
		bnXoa.addActionListener(this);
		bnSua.addActionListener(this);
		bnXemtt.addActionListener(this);
		
		
		
		
		
		
		add(b,BorderLayout.CENTER);
		
		//LopHoc
		lophoc =new LopHoc_DAO();
		table.setRowHeight(25);
		for(LopHoc lh:lophoc.getAllLopHoc()) {
			Object[] rowData= {lh.getMalop(),lh.getTenlop(),lh.getGiaovienCN()};
			DataModel.addRow(rowData);
		}
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	public static void main(String[] args) {
		LopHoc_DAO lophoc=new LopHoc_DAO();
		new giaoDien();
	}
	
	
	private void moKhoaControls(boolean b) {
		
		bnThem.setEnabled(b);
		bnLuu.setEnabled(b);
		bnSua.setEnabled(b);
		bnXoa.setEnabled(b);
		
	}

	private void moKhoaTextfields(boolean b) {
		txtMalop.setEditable(b);
		txtTenlop.setEditable(b);
		txtGVCN.setEditable(b);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		if(o.equals(bnThem)) {
			if(bnThem.getText().equalsIgnoreCase("Thêm")) {
				moKhoaTextfields(true);
				moKhoaControls(false);
				bnLuu.setEnabled(true);
				bnThem.setEnabled(true);
				
				bnThem.setText("Hủy");
				
			}
		}
		else if(o.equals(bnLuu)) {
			LopHoc lop=new LopHoc(txtMalop.getText(), txtTenlop.getText(),txtGVCN.getText());
			lophoc.addLopHoc(lop);
			JOptionPane.showMessageDialog(this, "Thêm Thành Công!");
			String row []= {txtMalop.getText(), txtTenlop.getText(),txtGVCN.getText()};
			DataModel.addRow(row);
		}
		
		
		
		
		
		else if(o.equals(bnXoa)) {
			xoarong();
		}
		
	}
	
	
	
	public void xoarong() {
		txtMalop.setText("");
		txtTenlop.setText("");
		txtGVCN.setText("");
	}
}


