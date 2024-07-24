package web_shop.api_web_shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data // Getters, Setters, Equals, HashCode, ToString khong can phai tu viet cac methode nay nua
@NoArgsConstructor // Tao ra 1 constructor khong co tham so
@AllArgsConstructor // Tao ra 1 constructor co tham so voi tat ca cac truong
public class SanPhamDTO {
    private Long id;
    private String ma;
    private String ten;
    private Double gia;
    private Long soLuongTonKho;
    private Long soLuongDaBan;
    private String moTa;
    private Integer trangThai;

    private LoaiSanPhamDTO loaiSanPham; // khong can mapping trong DTO

    private List<String> images; // luu URL anh

    @JsonIgnore // khong tra ve images Json, cau hoi la vay thi lam sao de upload anh
    private List<MultipartFile> files; // nguoi dung upload anh -> luu file vao server -> luu URL vao database
}
