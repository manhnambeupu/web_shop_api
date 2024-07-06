package web_shop.api_web_shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Getters, Setters, Equals, HashCode, ToString khong can phai tu viet cac methode nay nua
@NoArgsConstructor // Tao ra 1 constructor khong co tham so
@AllArgsConstructor // Tao ra 1 constructor co tham so voi tat ca cac truong
public class MauSac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ten;
}
