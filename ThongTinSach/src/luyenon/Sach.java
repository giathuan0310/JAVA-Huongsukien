package luyenon;

public class Sach {
	private String ma,Ten,Theloai,Nhaxb;
	private int sotrang;
	public Sach() {
		super();
	}
	public Sach(String ma, String ten, String theloai, String nhaxb, int sotrang) {
		super();
		this.ma = ma;
		Ten = ten;
		Theloai = theloai;
		Nhaxb = nhaxb;
		this.sotrang = sotrang;
	}
	public String getMa() {
		return ma;
	}
	public void setMa(String ma) {
		this.ma = ma;
	}
	public String getTen() {
		return Ten;
	}
	public void setTen(String ten) {
		Ten = ten;
	}
	public String getTheloai() {
		return Theloai;
	}
	public void setTheloai(String theloai) {
		Theloai = theloai;
	}
	public String getNhaxb() {
		return Nhaxb;
	}
	public void setNhaxb(String nhaxb) {
		Nhaxb = nhaxb;
	}
	public int getSotrang() {
		return sotrang;
	}
	public void setSotrang(int sotrang) {
		this.sotrang = sotrang;
	}
	@Override
	public String toString() {
		return "Sach [ma=" + ma + ", Ten=" + Ten + ", Theloai=" + Theloai + ", Nhaxb=" + Nhaxb + ", sotrang=" + sotrang
				+ "]";
	}
	
	
	

}
