import java.io.Serializable;

public class NhanVien implements Serializable
{
    private String maNV;
    private String hoNV;
    private String tenNV;
    private String phai;
    private int tuoi;
    private double tienLuong;

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoNV() {
        return hoNV;
    }

    public void setHoNV(String hoNV) {
        this.hoNV = hoNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getPhai() {
        return phai;
    }

    public void setPhai(String phai) {
        this.phai = phai;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public Double getTienLuong() {
        return tienLuong;
    }

    public void setTienLuong(Double tienLuong) {
        this.tienLuong = tienLuong;
    }
    public NhanVien(String maNV){
        this.maNV = maNV;
    }
    public NhanVien(String maNV,String hoNV, String tenNV,String phai,int tuoi, double tienLuong){
        super();
        this.maNV = maNV;
        this.hoNV = hoNV;
        this.tenNV = tenNV;
        this.phai = phai;
        this.tuoi = tuoi;
        this.tienLuong =tienLuong;
    }

    @Override
    public String toString() {
        return "Nhân Viên [MaNV = " +maNV +",HoNV = " +hoNV +",TenNV = " +tenNV +",phai = "+phai +",tuoi = " +tuoi +",Luong = " +tienLuong +"]";
    }
}
