package Lop_Sv;

public class Sinhvien {
	private String Masv,hoTen,email,diachi;
	private LopHoc lophoc;
	public Sinhvien() {
		super();
	}
	public Sinhvien(String masv, String hoTen, String email, String diachi, LopHoc lophoc) {
		super();
		Masv = masv;
		this.hoTen = hoTen;
		this.email = email;
		this.diachi = diachi;
		this.lophoc = lophoc;
	}
	public String getMasv() {
		return Masv;
	}
	public void setMasv(String masv) {
		Masv = masv;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public LopHoc getLophoc() {
		return lophoc;
	}
	public void setLophoc(LopHoc lophoc) {
		this.lophoc = lophoc;
	}
	@Override
	public String toString() {
		return "Sinhvien [Masv=" + Masv + ", hoTen=" + hoTen + ", email=" + email + ", diachi=" + diachi + ", lophoc="
				+ lophoc + "]";
	}
	
	

}
