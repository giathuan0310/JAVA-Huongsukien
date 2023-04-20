package Dao;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Lop_Sv.LopHoc;
import connectDB.ConnectDB;

public class LopHoc_DAO {
	public List<LopHoc> getAllLopHoc(){
		
		List<LopHoc> dslophoc=new ArrayList<LopHoc>();
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		try {
			String sql="select * from LopHoc";
			Statement statement =con.createStatement();
			ResultSet rs=statement.executeQuery(sql);
			while(rs.next()) {
				dslophoc.add(new LopHoc(rs.getString("Malop"),
						rs.getString("Tenlop"),
						rs.getString("giaovienCN")
						));
						
						
						
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dslophoc;
	}
	
	public void addLopHoc(LopHoc lop) {
		Connection con=ConnectDB.getInstance().getConnection();
		PreparedStatement stmt=null;
		try {
			stmt =con.prepareStatement("insert into LopHoc values(?,?,?)");
			stmt.setString(1, lop.getMalop());
			stmt.setString(2, lop.getTenlop());
			stmt.setString(3, lop.getGiaovienCN());
			stmt.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			Close(stmt);
		}
	}
	
	
	public void update(LopHoc lop) {
		Connection con=ConnectDB.getInstance().getConnection();
		PreparedStatement stmt=null;
		String sql="update LopHoc"
				+"set Tenlop=?,"
				+"GiaovienCN=?,"
				+"where Malop =?";
		try {
			stmt =con.prepareStatement("insert into LopHoc values(?,?,?)");
			stmt.setString(1, lop.getTenlop());
			stmt.setString(2, lop.getGiaovienCN());
			stmt.setString(3, lop.getMalop());
			stmt.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			Close(stmt);
		}
	}
	
	public void deleteLopHoc(String malop) {
		Connection con=ConnectDB.getInstance().getConnection();
		PreparedStatement stmt=null;
		String sql="delete from LopHoc where Malop=?";
		try {
			stmt=con.prepareStatement(sql);
			
			stmt.setString(1, malop);
			
			stmt.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			Close(stmt);
		}
	}

	private void Close(PreparedStatement stmt) {
		if(stmt !=null)
			try {
				stmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		
	}
}
