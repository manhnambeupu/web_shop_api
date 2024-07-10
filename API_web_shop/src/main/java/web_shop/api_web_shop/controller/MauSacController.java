package web_shop.api_web_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web_shop.api_web_shop.dto.MauSacDTO;
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
    public List<MauSacDTO> getAll(){
        return mauSacService.getAll();
    }

    @PostMapping // tao ra 1 ban ghi mau sac
    // RequestBody: lay du lieu cua nguoi dung gui
    public void create(@RequestBody MauSacDTO mauSacDTO){
        mauSacService.create(mauSacDTO);
    }

    @PutMapping("/id") // cap nhat ban ghi mau sac
    //PathVariable: lay du lieu id tu duong link de update tren DB
    public void update(@PathVariable Long id, @RequestBody MauSacDTO dto){
        // DB: update mau sac = "" where id = "" (phai dinh kem them id)
        mauSacService.update(dto, id);
    }
    @GetMapping("/id") // lay ban ghi mau sac theo id
    public MauSacDTO getById(Long id){
        return mauSacService.getById(id);
    }

    @DeleteMapping // xoa ban ghi mau sac
    public void delete(Long id){
        mauSacService.delete(id);
    }
}
