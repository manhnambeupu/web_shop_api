package web_shop.api_web_shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String maHoaDon;
    private  Long tongSanPham;
    private  Double tongSoTien;
    private  String soDienThoai;
    private  String diaChi;
    private  String hoVaTen;
    private  String maNhanVien; // luu lai thong tin khi ban hang tai quay
    private LocalDate date;
    private LocalDate ngayHoanThanh;
    private String lyDoHuy;
    private Integer trangThai; // 0 da huy, 1 dang cho, 2 cho lay hang, 3 Dang giao hang, 4 da giao hang
    private Integer loaiHoaDon; // 1 online ,2 tai quay

    @ManyToOne
    @JoinColumn(name = "tai_khoan_id")
    private TaiKhoan taiKhoan;

}
