package web_shop.api_web_shop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * ResponseDTO la mot class dung de tra ve ket qua cua API
 * vi du nhu:  dang ky thanh cong, dang ky that bai, dang nhap thanh cong, dang nhap that bai
 * Vi di: update thanh cong, update that bai ...vv
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // Tao ra 1 builder de tao doi tuong ResponseDTO
@JsonInclude(JsonInclude.Include.NON_NULL) // Chi tra ve cac truong khac null
public class ResponseDTO <T> {
    private T data;
    private String message;
    private Integer status;
}
