package web_shop.api_web_shop.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import web_shop.api_web_shop.dto.ResponseDTO;

/**
 * Handle exception
 * neu xay ra loi thi chay vao day lay message cua loi do tra ve cho client
 * Cu thu chay di , xong xem Exception cua loi ten la gi
 * thi @ExceptionHandler(value = "tenException" .class)
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler( value = RuntimeException.class)
    ResponseDTO<String> handleException(RuntimeException e){
        //cach truyen thong dung ResponseEntity: return ResponseEntity.badRequest().body(e.getMessage());
        return ResponseDTO. <String> builder()
                .message(e.getMessage())
                .status(400)
                .build();
    }


    //xu ly loi dingh dang du lieu @Size, @valid
    @ExceptionHandler (value = MethodArgumentNotValidException.class)
    ResponseDTO<String> handleMethodArgumentNotValidException (MethodArgumentNotValidException e){
       //cach truyen thong , dung ResponseEnity return ResponseEntity.badRequest().body( e.getFieldError().getDefaultMessage() );
        return ResponseDTO. <String> builder()
                .message(e.getBindingResult().getFieldError().getDefaultMessage())
                .status(400)
                .build();
    }
}
