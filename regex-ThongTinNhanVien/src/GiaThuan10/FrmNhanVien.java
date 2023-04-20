package GiaThuan10;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class FrmNhanVien extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtDiaChi;
	private JTextField txtTuoi;
	private JTextField txtEmail;
	
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnLuu;
	
	private JTable table;
	private JTextField txtMess;
	private JButton btnXoaRong;

	private QuanLy nhanvien;
	private DefaultTableModel tableModel;

	private JButton btnLoc;

	public FrmNhanVien() {
		setTitle("Quản lý nhan vien");
		setSize(900, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		buildUI();
	}

	private void buildUI() {

		
		JPanel pnlNorth;
		add(pnlNorth = new JPanel(), BorderLayout.NORTH);
		pnlNorth.setPreferredSize(new Dimension(0, 180));
		pnlNorth.setBorder(BorderFactory.createTitledBorder("Records Editor"));
		pnlNorth.setLayout(null); // Absolute layout

		JLabel lblMaNV, lblHoTen, lblDiaChi, lblTuoi, lblEmail;
		pnlNorth.add(lblMaNV = new JLabel("Mã Nhân Viên: "));
		pnlNorth.add(lblHoTen = new JLabel("Họ Tên: "));
		pnlNorth.add(lblDiaChi = new JLabel("Địa chỉ: "));
		pnlNorth.add(lblTuoi = new JLabel("Tuổi: "));
		pnlNorth.add(lblEmail = new JLabel("Email: "));
		
		pnlNorth.add(txtMa = new JTextField());
		pnlNorth.add(txtTen = new JTextField());
		pnlNorth.add(txtDiaChi = new JTextField());
		pnlNorth.add(txtTuoi = new JTextField());
		pnlNorth.add(txtEmail = new JTextField());
		
		pnlNorth.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));

		int w1 = 100, w2 = 300, h = 20;
		lblMaNV.setBounds(20, 20, w1, h);
		txtMa.setBounds(120, 20, 200, h);

		lblHoTen.setBounds(20, 45, w1, h);
		txtTen.setBounds(120, 45, w2, h);
		lblDiaChi.setBounds(450, 45, w1, h);
		txtDiaChi.setBounds(570, 45, w2, h);

		lblTuoi.setBounds(20, 70, w1, h);
		txtTuoi.setBounds(120, 70, w2, h);
		lblEmail.setBounds(450, 70, w1, h);
		txtEmail.setBounds(570, 70, w2, h);

	
		txtMess.setBounds(20, 145, 550, 20);

		
		JPanel pnlCenter;
		add(pnlCenter = new JPanel(), BorderLayout.CENTER);
		pnlCenter.add(btnThem = new JButton("Thêm - kiểm tra dữ liệu ..."));
		pnlCenter.add(btnXoaRong = new JButton("Xóa rỗng"));

		
		JScrollPane scroll;
		String[] headers = "MaNhanVien;HoTenNV;Tuoi;DiaChi; Email".split(";");

		tableModel = new DefaultTableModel(headers, 0);
		add(scroll = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.SOUTH);
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách"));
		table.setRowHeight(20);
		scroll.setPreferredSize(new Dimension(0, 350));

		

		nhanvien = new QuanLy();
	
		updateTableData(); 
		
		btnThem.addActionListener(this);		
		btnXoaRong.addActionListener(this);
		
	}

	private void fillForm(int row) {
		if (row != -1) {
			String ma = (String) table.getValueAt(row, 0);
			NhanVien s = new NhanVien(ma);
			ArrayList<NhanVien> dsSach = nhanvien.getDs();
			if (nhanvien.getDs().contains(s)) {
				s = dsSach.get(dsSach.indexOf(s));
				txtMa.setText(s.getMaNV());
				txtTen.setText(s.getHoTen());
				txtDiaChi.setText(s.getDiaChi());
				txtEmail.setText(s.getEmail());
				txtTuoi.setText(s.getTuoi() + "");				
				txtMa.setEditable(false);
			}
		}
	}

	private void updateTableData() {
		XoaHetDuLieuTrenTableModel();
		for (NhanVien s: nhanvien.getDs()) {
			tableModel.addRow(new Object[] { s.getMaNV(), s.getHoTen(),
					 s.getTuoi(),s.getDiaChi(), s.getEmail(),
					});
		}

		table.setModel(tableModel);

	}
	
	

	private void XoaHetDuLieuTrenTableModel() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.getDataVector().removeAllElements();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (validData()) {
				NhanVien s = revertNhanVienFromTextfields();
				if (nhanvien.them(s)) {
					txtMess.setText("Thêm thành công..........");
					updateTableData(); 
				} else {
					showMessage("Error: Trùng mã số", txtMa);
				}
			}
		} else if (o.equals(btnXoaRong)) {
			clearTextfields();
		}

	}

	private void showMessage(String message, JTextField txt) {
		txt.requestFocus();
		txtMess.setText(message);
	}
	
	private boolean validData() {
		String maSach = txtMa.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String hoTen = txtTen.getText().trim();
		String Age = txtTuoi.getText().trim();
		String mail = txtEmail.getText().trim();
		
		
		if(! (maSach.length()>0 && maSach.matches("^(NV)\\d{8}"))) {
			showMessage("Error: mã sách phải theo: ^(NV)\\\\d{8}", txtMa);
			JOptionPane.showMessageDialog(this, "Error: mã nhan vien phải theo: ^(NV)\\\\d{8}");
			return false;
		}
		
		if(!(diaChi.length()>0 && diaChi.matches("^[0-9' ]+[A-Za-z' ]+"))) {
			showMessage("Error: địa chỉ phải theo: ^[0-9][A-Za-z]+", txtDiaChi);
			JOptionPane.showMessageDialog(this, "Error: địa chỉ phải theo: ^[0-9][A-Za-z]+");
			return false;
		}
	
		if(!(hoTen.length()>0 && hoTen.matches("[A-Za-z' ]+"))) {
			showMessage("Error: họ tên phải theo: [A-Za-z' ]+", txtTen);
			JOptionPane.showMessageDialog(this, "Error: họ tên phải theo: [A-Za-z' ]+");
			return false;
		}
		
		if(!(Age.length()>0 && Age.matches("^[1-9][0-9]?$|^100$"))) {
			showMessage("Error: tuổi phải theo: ^[1-9][0-9]?$|^100$", txtTuoi);
			JOptionPane.showMessageDialog(this, "Error: tuổi phải theo: ^[1-9][0-9]?$|^100$");
			return false;
		}
		
		if(!(mail.length()>0 && mail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))) {
			showMessage("Error: email phải theo: ^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", txtEmail);
			JOptionPane.showMessageDialog(this, "Error: email phải theo: ^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
			return false;
		}
		return true;
		
	}

	private NhanVien revertNhanVienFromTextfields() {
		String maNV = txtMa.getText().trim();
		String hoTen = txtTen.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		int tuoi = Integer.parseInt(txtTuoi.getText());
		String email = txtEmail.getText().trim();
		return new NhanVien(maNV, hoTen, diaChi, tuoi, email);
	}

	private void clearTextfields() {
		txtMa.setText("");
		txtTen.setText("");
		txtDiaChi.setText("");
		txtTuoi.setText("");
		txtEmail.setText("");
	
		txtMa.setEditable(true);
		txtMa.requestFocus();
	}
}

