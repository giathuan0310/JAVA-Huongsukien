

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Gui extends JFrame implements ActionListener {
    JPanel pnCenter;
    DefaultTableModel model;
    JTable table;
    Box b2;
    JTextField txtManv,txtHo,txtTenNV,txtTuoi,txtTienLuong,txtTimKiem;
    JLabel lbManv,lbHo,lbTen,lbTuoi,lbPhai,lbTienLuong;
    JButton btnThem,btnXoaTrang,btnXoa,btnLuu,btnTim,btnSua;
    JRadioButton radNam,radNu;
    private DanhSachNV ds;
    
    private Databasee databasee;
    
    public Gui(){
        super("Thông tin nhân viên");
        
        databasee = new Databasee();
        
        ds=new DanhSachNV();
        gui();
        try{
            loadData();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void gui(){
       JPanel pnBorder = new JPanel();
       pnBorder.setLayout(new BorderLayout());
       //TiÃªu Ä‘á»�
        JPanel pnNorth = new JPanel();
        JLabel lbTitle = new JLabel("THÔNG TIN NHÂN VIÊN");
        lbTitle.setForeground(Color.BLUE);
        Font ftTitle = new Font("Arial",Font.BOLD,25);
        lbTitle.setFont(ftTitle);
        pnNorth.add(lbTitle);
        pnBorder.add(pnNorth,BorderLayout.NORTH);

        //Center
        pnCenter = new JPanel();
        pnCenter.setLayout(new BoxLayout(pnCenter,BoxLayout.Y_AXIS));
        Box b = Box.createVerticalBox();
        Box b1 = Box.createHorizontalBox();
        Box b2 = Box.createHorizontalBox();
        Box b3 = Box.createHorizontalBox();
        Box b4 = Box.createHorizontalBox();
        b.add(b1);
        b.add(Box.createVerticalStrut(5));
        b.add(b2);
        b.add(Box.createVerticalStrut(5));
        b.add(b3);
        b.add(Box.createVerticalStrut(5));
        b.add(b4);
        b.add(Box.createVerticalStrut(5));
        pnCenter.add(b);
        pnCenter.add(Box.createVerticalStrut(10));
        lbManv = new JLabel("Mã nhân viên: ");
        lbHo = new JLabel("Họ: ");
        lbTen = new JLabel("Tên nhân viên: ");
        lbTuoi = new JLabel("Tuổi: ");
        lbPhai = new JLabel("Phái: ");
        lbTienLuong = new JLabel("Tiền Lương: ");

        txtManv = new JTextField();
        b1.add(lbManv);
        b1.add(txtManv);

        txtHo = new JTextField();
        txtTenNV = new JTextField();
        b2.add(lbHo);
        b2.add(txtHo);
        b2.add(lbTen);
        b2.add(txtTenNV);
        lbHo.setPreferredSize(lbManv.getPreferredSize());

        txtTuoi = new JTextField();
        txtTienLuong = new JTextField();
        radNam = new JRadioButton("Nam",true);
        radNam.setEnabled(true);
        radNu = new JRadioButton("Nữ");
        ButtonGroup group = new ButtonGroup();
        group.add(radNam); group.add(radNu);
        b3.add(lbTuoi);
        b3.add(txtTuoi);
        b3.add(lbPhai);
        b3.add(radNam);
        b3.add(radNu);
        b4.add(lbTienLuong);
        b4.add(txtTienLuong);
        lbTuoi.setPreferredSize(lbManv.getPreferredSize());
        lbPhai.setPreferredSize(lbManv.getPreferredSize());
        lbTienLuong.setPreferredSize(lbManv.getPreferredSize());

        taoBang();
        pnBorder.add(pnCenter,BorderLayout.CENTER);
        //Chá»©c nÄƒng
        JSplitPane split;
        pnBorder.add(split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT), BorderLayout.SOUTH);
        split.setResizeWeight(0.5);

        JPanel pnTimKiem = new JPanel();
        JLabel lbTimKiem = new JLabel("Nhập thông tin nhân viên cần tìm");
        txtTimKiem = new JTextField(10);
        btnTim = new JButton("Tìm");
        pnTimKiem.add(lbTimKiem);
        pnTimKiem.add(txtTimKiem);
        pnTimKiem.add(btnTim);
        split.add(pnTimKiem);

        JPanel pnChucNang = new JPanel();
        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Xóa");
        btnSua = new JButton("Sửa");
        btnXoaTrang = new JButton("Xóa trắng");
        btnLuu = new JButton("Lưu");
        pnChucNang.add(btnThem);
        pnChucNang.add(btnXoa);
        pnChucNang.add(btnSua);
        pnChucNang.add(btnXoaTrang);
        pnChucNang.add(btnLuu);
        split.add(pnChucNang);

        Border cnBorder = BorderFactory.createLineBorder(Color.GRAY);
        pnChucNang.setBorder(cnBorder);
        pnTimKiem.setBorder(cnBorder);

       
        btnThem.addActionListener(this);
        btnTim.addActionListener(this);
        btnLuu.addActionListener(this);
        btnXoa.addActionListener(this);
        btnXoaTrang.addActionListener(this);
        this.add(pnBorder);
    }
    public void taoBang() {
        JPanel pnTable = new JPanel();
        model = new DefaultTableModel();
        table = new JTable(model);
        model.addColumn("Mã NV");
        model.addColumn("Họ");
        model.addColumn("Tên");
        model.addColumn("Phái");
        model.addColumn("Tuổi");
        model.addColumn("Tiền lương");
        TableColumn phaicolumn = table.getColumnModel().getColumn(3);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Nam");
        comboBox.addItem("Nữ");
        phaicolumn.setCellEditor(new DefaultCellEditor(comboBox));
        TableColumn column = new TableColumn();
        column.setPreferredWidth(100);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        DefaultTableCellRenderer xenter = new DefaultTableCellRenderer();
        xenter.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(xenter);
        table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

        JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setPreferredSize(new Dimension(650, 250));
        pnCenter.add(sp);

       /* table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnXoa.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int r = table.getSelectedRow();
                        ds.xoaViTri(r);
                        model.removeRow(r);
                    }
                });
                int row = table.getSelectedRow();
                txtManv.setText(model.getValueAt(row,0).toString());
                txtHo.setText(model.getValueAt(row,1).toString());
                txtTenNV.setText(model.getValueAt(row,2).toString());
                if(model.getValueAt(row,3).toString().equalsIgnoreCase("Nam"))
                {
                    radNam.setSelected(true);
                    radNu.setSelected(false);
                }
                else
                {
                    radNam.setSelected(false);
                    radNu.setSelected(true);
                }
                txtTuoi.setText(model.getValueAt(row,4).toString());
                txtTienLuong.setText(model.getValueAt(row,5).toString());
            }
        });

        */
       table.addMouseListener(new MouseListener() {
           @Override
           public void mouseClicked(MouseEvent e) {
               int row = table.getSelectedRow();
               txtManv.setText(model.getValueAt(row,0).toString());
               txtHo.setText(model.getValueAt(row,1).toString());
               txtTenNV.setText(model.getValueAt(row,2).toString());
               if(model.getValueAt(row,3).toString().equalsIgnoreCase("Nam"))
               {
                   radNam.setSelected(true);
                   radNu.setSelected(false);
               }
               else
               {
                   radNam.setSelected(false);
                   radNu.setSelected(true);
               }
               txtTuoi.setText(model.getValueAt(row,4).toString());
               txtTienLuong.setText(model.getValueAt(row,5).toString());
           }

           @Override
           public void mousePressed(MouseEvent e) {

           }

           @Override
           public void mouseReleased(MouseEvent e) {

           }

           @Override
           public void mouseEntered(MouseEvent e) {

           }

           @Override
           public void mouseExited(MouseEvent e) {

           }
       });
    }
    public void loadData() throws Exception{
    	//ds=null;
        //cach 1
    	//ds = databasee.read_NV("Nhanvien.txt");
        // Cach 2
    	ds = (DanhSachNV)databasee.readFile("Nhanvien.dat");
    	if(ds==null)
    	{
    		ds =new DanhSachNV();
    	}
    	else
    		
    	{for(NhanVien nv : ds.getLs()){
            String[] row = {nv.getMaNV(), nv.getHoNV(),nv.getTenNV(),nv.getPhai(),nv.getTuoi()+"",nv.getTienLuong()+""};
            model.addRow(row);
    	}
    }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnThem))
        {
            if(btnThem.getText().equals("Thêm"))
            {
                xoatrang();
                btnXoa.setEnabled(false);
                btnThem.setText("Hủy");
            }
            else
            {
                btnXoa.setEnabled(true);
                btnThem.setText("ThÃªm");
            }

    }
        else if(e.getSource().equals(btnLuu))
        {
            if(txtManv.getText().equals("") || txtHo.getText().equals("") || txtTenNV.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Bạn chưa nhập thông tin! ");
            }
            else
            {
                try {
                    luu();
                    //databasee.writeNV("Nhanvien.txt", ds);
                    databasee.saveFile("Nhanvien.dat", ds);
                    
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            
        }
        else if(e.getSource().equals(btnXoa)) {
            try{
                xoaDong();
            }catch (Exception exc){
                exc.printStackTrace();
            }
            /*
            int rows = model.getRowCount();
            for(int i = rows - 1; i >=0; i--)
            {
                model.removeRow(i);
            }
            for (NhanVien nv : ds.getLs()) {
                String[] row = {nv.getMaNV(), nv.getHoNV(), nv.getTenNV(), nv.getPhai(), nv.getTuoi() + "", nv.getTienLuong() + ""};
                model.addRow(row);
            }
            table.setModel(model);
            */
        }
        else if(e.getSource().equals(btnXoaTrang))
        {
            xoatrang();
        }
        else if(e.getSource().equals(btnTim))
        {
            int pos=ds.timNhanvien(txtTimKiem.getText());
            if(pos!=-1)
            {
            	JOptionPane.showMessageDialog(null,"Có nhân viên này!");
                        table.setRowSelectionInterval(pos, pos);
             }
            else
              JOptionPane.showMessageDialog(null,"Khong tim  thay nhan vien co ID này!");
        }

    }
    public void luu() throws Exception{
        String gt = "";
        String ma = txtManv.getText();
        String ho = txtHo.getText();
        String ten = txtTenNV.getText();
        if(radNam.isSelected())
            gt = radNam.getText();
        if(radNu.isSelected())
            gt = radNu.getText();
        String tuoi = txtTuoi.getText();
        String luong = txtTienLuong.getText();

        NhanVien nv = new NhanVien(ma,ho,ten,gt,Integer.parseInt(tuoi),Double.parseDouble(luong));
        if(ds.themNV(nv))
        	{
        		JOptionPane.showMessageDialog(this, "Them thanh cong");
        		String [] row = {ma,ho,ten,gt,tuoi,luong};
        		model.addRow(row);
        		
        		//databasee.writeNV("Nhanvien.txt",ds);
        		databasee.saveFile("Nhanvien.dat", ds);
        	}
        else
       {
        	JOptionPane.showMessageDialog(this, "Id nay ton tai");
        	txtManv.setText("");
        	
        }
        // cach 1
       // databasee.writeNV("Nhanvien.txt",ds);
       //cach 2
        databasee.saveFile("Nhanvien.dat", ds);
        
       
    }
    public void xoaDong() throws Exception{
        int r = table.getSelectedRow();
        if(r != -1)
        {
            int tb = JOptionPane.showConfirmDialog(null,"Báº¡n cÃ³ cháº¯c cháº¯n muá»‘n xÃ³a dÃ²ng nÃ y khÃ´ng?","Delete",JOptionPane.YES_NO_OPTION);
            if(tb == JOptionPane.YES_OPTION){
                ds.xoaViTri(r);
                model.removeRow(r);
                xoatrang();
               //databasee.writeNV("Nhanvien.txt", ds);
                databasee.saveFile("Nhanvien.dat", ds);
                
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Báº¡n chÆ°a chá»�n dÃ²ng cáº§n xÃ³a!");
        }
    }
    public void save() throws Exception{
        //databasee.writeNV("Nhanvien.txt",ds);
    	
    }
    public void xoatrang(){
        txtManv.setText("");
        txtHo.setText("");
        txtTenNV.setText("");
        radNam.setSelected(false);
        radNu.setSelected(false);
        txtTuoi.setText("");
        txtTienLuong.setText("");
        txtManv.requestFocus();
    }
}
