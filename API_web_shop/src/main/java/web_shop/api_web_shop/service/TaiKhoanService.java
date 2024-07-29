package web_shop.api_web_shop.service;

import web_shop.api_web_shop.dto.TaiKhoanDTO;

import java.util.List;

public interface TaiKhoanService {

    List<TaiKhoanDTO> getAll();

    TaiKhoanDTO getById(Long id);

    void create(TaiKhoanDTO taiKhoanDTO);

    void update(TaiKhoanDTO taiKhoanDTO, Long id);

    //void delete(Long id);
}
