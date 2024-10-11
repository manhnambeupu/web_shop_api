package web_shop.api_web_shop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web_shop.api_web_shop.dto.MauSacDTO;
import web_shop.api_web_shop.dto.ResponseDTO;
import web_shop.api_web_shop.service.MauSacService;

import java.util.List;


// Client -> Controller -> Service -> Repository -> Database

@RestController
@RequestMapping("/api/mausac")
@RequiredArgsConstructor // tao ra 1 constructor co tham so inject dependency vao cac doi tuong duoc dat la final
public class MauSacController {

    private final MauSacService mauSacService;

    // Rest API get(che giau duong link tang tinh bao mat), post, put, delete
    @GetMapping // lay du lieu
    public ResponseDTO< List<MauSacDTO> > getAll(){

        return ResponseDTO. < List<MauSacDTO> >builder() //  dung bbuilder de tao mot doi tuong ResponseDTO co kieu
                                                        //  la mot List<MauSacDTO>
                .data(mauSacService.getAll())
                .status(200)
                .message("Success")
                .build();

        /*cach2, khong dung builder
        ResponseDTO< List<MauSacDTO> > responseDTO = new ResponseDTO<>();
        responseDTO.setData(mauSacService.getAll());
        responseDTO.setStatus(200);
        responseDTO.setMessage("Success");
        return responseDTO;
         */
    }

    @PostMapping // tao ra 1 ban ghi mau sac
    // RequestBody: lay du lieu cua nguoi dung gui
    // @Valid: kiem tra xem du lieu co dung dinh dang hay khong
    public ResponseDTO <Void> create(@RequestBody @Valid MauSacDTO mauSacDTO){

        mauSacService.create(mauSacDTO);
        return ResponseDTO. <Void>builder()
                .status(201)
                .message("Success create mau sac")
                .build();
    }

    @PutMapping("/{id}") // cap nhat ban ghi mau sac
    //PathVariable: lay du lieu id tu duong link de update tren DB
    public ResponseDTO <Void> update(@PathVariable Long id, @RequestBody @Valid MauSacDTO dto){
        // DB: update mau sac = "" where id = "" (phai dinh kem them id)
        mauSacService.update(dto, id);
        return ResponseDTO. <Void>builder()
                .status(200)
                .message("Success update mau sac")
                .build();
    }
    @GetMapping("/{id}") // lay ban ghi mau sac theo id
    public ResponseDTO <MauSacDTO> getById(@PathVariable Long id){
        return ResponseDTO. <MauSacDTO>builder()
                .data(mauSacService.getById(id))
                .status(200)
                .message("Success")
                .build();
        /*cach2, khong dung builder
        ResponseDTO<MauSacDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(mauSacService.getById(id));
        responseDTO.setStatus(200);
        responseDTO.setMessage("Success");
        return responseDTO;
         */
    }

    @DeleteMapping("/{id}") // xoa ban ghi mau sac
    public ResponseDTO <Void> delete(@PathVariable Long id){
        mauSacService.delete(id);
        return ResponseDTO. <Void>builder()
                .status(204)
                .message("Success delete mau sac")
                .build();
    }
}
