package web_shop.api_web_shop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonDTO {

    private  Long id;
    private  String maHoaDon;
    private  Long tongSanPham;
    private  Double tongSoTien;
    private  String soDienThoai;
    private  String diaChi;
    private  String hoVaTen;
    private  String maNhanVien; // luu lai thong tin khi ban hang tai quay

    @NotNull(message = "Ngay tao hoa don khong duoc de trong")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @NotNull(message = "Ngay tao hoa don khong duoc de trong")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate ngayHoanThanh;

    private String lyDoHuy;
    private Integer trangThai; // 0 da huy, 1 dang cho, 2 cho lay hang, 3 Dang giao hang, 4 da giao hang
    private Integer loaiHoaDon; // 1 online ,2 tai quay

    //private TaiKhoanDTO taiKhoan;
    private Long taiKhoanId;
}
