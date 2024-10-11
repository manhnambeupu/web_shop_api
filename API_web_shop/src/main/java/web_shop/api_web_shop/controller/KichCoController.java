package web_shop.api_web_shop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web_shop.api_web_shop.dto.KichCoDTO;
import web_shop.api_web_shop.dto.ResponseDTO;
import web_shop.api_web_shop.service.KichCoService;

import java.util.List;

@RestController
@RequestMapping("/api/kichco")
@RequiredArgsConstructor // tao ra 1 constructor co tham so inject dependency vao cac doi tuong duoc dat la final
public class KichCoController {

        private final KichCoService kichCoService;

        // Rest API get(che giau duong link tang tinh bao mat), post, put, delete
        @GetMapping // lay du lieu
        public ResponseDTO < List<KichCoDTO> > getAll(){
            kichCoService.getAll();
            return ResponseDTO. < List<KichCoDTO> >builder() //  dung bbuilder de tao mot doi tuong ResponseDTO co kieu
                                                            //  la mot List<KichCoDTO>
                    .data(kichCoService.getAll())
                    .status(200)
                    .message("Success")
                    .build();

            /*cach2, khong dung builder
            ResponseDTO< List<KichCoDTO> > responseDTO = new ResponseDTO<>();
            responseDTO.setData(kichCoService.getAll());
            responseDTO.setStatus(200);
            responseDTO.setMessage("Success");
             */
        }

    @GetMapping("/{id}") // lay ban ghi kich co theo id
    public ResponseDTO<KichCoDTO> getById(Long id){
        return ResponseDTO. <KichCoDTO>builder()
                .data(kichCoService.getById(id))
                .status(200)
                .message("Success")
                .build();
    }

        @PostMapping // tao ra 1 ban ghi kich co
        // RequestBody: lay du lieu cua nguoi dung gui
        // @Valid: kiem tra xem du lieu co dung dinh dang hay khong
        public ResponseDTO<Void> create(@RequestBody  @Valid KichCoDTO kichCoDTO){
            kichCoService.create(kichCoDTO);
            return ResponseDTO. <Void>builder()
                    .status(201)
                    .message("Success create size product")
                    .build();
        }

        @PutMapping("/{id}") // cap nhat ban ghi kich co
        //PathVariable: lay du lieu id tu duong link de update tren DB
        public ResponseDTO <Void> update(@PathVariable Long id, @RequestBody KichCoDTO dto){
            // DB: update kich co = "" where id = "" (phai dinh kem them id)
            kichCoService.update(dto, id);
            return ResponseDTO. <Void>builder()
                    .status(200)
                    .message("Success update size product")
                    .build();
        }


        @DeleteMapping("/{id}") // xoa ban ghi kich co
        public ResponseDTO <Void> delete(@PathVariable Long id){
            kichCoService.delete(id);
            return  ResponseDTO. <Void>builder()
                    .status(204)
                    .message("Success delete size product")
                    .build();
        }
}
