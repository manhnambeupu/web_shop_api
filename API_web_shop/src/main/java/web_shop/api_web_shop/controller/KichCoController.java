package web_shop.api_web_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web_shop.api_web_shop.dto.KichCoDTO;
import web_shop.api_web_shop.service.KichCoService;

import java.util.List;

@RestController
@RequestMapping("/api/kichco")
@RequiredArgsConstructor // tao ra 1 constructor co tham so inject dependency vao cac doi tuong duoc dat la final
public class KichCoController {

        private final KichCoService kichCoService;

        // Rest API get(che giau duong link tang tinh bao mat), post, put, delete
        @GetMapping // lay du lieu
        public List<KichCoDTO> getAll(){
            return kichCoService.getAll();
        }

        @PostMapping // tao ra 1 ban ghi kich co
        // RequestBody: lay du lieu cua nguoi dung gui
        public void create(@RequestBody KichCoDTO kichCoDTO){
            kichCoService.create(kichCoDTO);
        }

        @PutMapping("/id") // cap nhat ban ghi kich co
        //PathVariable: lay du lieu id tu duong link de update tren DB
        public void update(@PathVariable Long id, @RequestBody KichCoDTO dto){
            // DB: update kich co = "" where id = "" (phai dinh kem them id)
            kichCoService.update(dto, id);
        }
        @GetMapping("/id") // lay ban ghi kich co theo id
        public KichCoDTO getById(Long id){
            return kichCoService.getById(id);
        }

        @DeleteMapping // xoa ban ghi kich co
        public void delete(Long id){
            kichCoService.delete(id);
        }
}
