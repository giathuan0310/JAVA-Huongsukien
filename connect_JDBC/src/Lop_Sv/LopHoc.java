package Lop_Sv;

public class LopHoc {
	private String Malop,Tenlop,giaovienCN;

	public LopHoc() {
		super();
	}

	public LopHoc(String malop, String tenlop, String giaovienCN) {
		super();
		Malop = malop;
		Tenlop = tenlop;
		this.giaovienCN = giaovienCN;
	}

	public String getMalop() {
		return Malop;
	}

	public void setMalop(String malop) {
		Malop = malop;
	}

	public String getTenlop() {
		return Tenlop;
	}

	public void setTenlop(String tenlop) {
		Tenlop = tenlop;
	}

	public String getGiaovienCN() {
		return giaovienCN;
	}

	public void setGiaovienCN(String giaovienCN) {
		this.giaovienCN = giaovienCN;
	}

	@Override
	public String toString() {
		return "LopHoc [Malop=" + Malop + ", Tenlop=" + Tenlop + ", giaovienCN=" + giaovienCN + "]";
	}
	

}
