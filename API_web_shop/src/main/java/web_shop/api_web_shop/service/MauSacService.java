package web_shop.api_web_shop.service;

import web_shop.api_web_shop.dto.MauSacDTO;

import java.util.List;

/**
 * MauSacService
 */
public interface MauSacService {
    /**
     * getAll
     * gibt alle Farbe Data Transfer Object zurueck
     * @return List<MauSacDTO>
     */
    List<MauSacDTO> getAll();

    /**
     * create
     * erstellt ein Farbe Data Transfer Object
     * @param mauSacDTO
     */
    void create(MauSacDTO mauSacDTO);

    /**
     * update
     * aktualisiert ein Farbe Data Transfer Object
     * @param mauSacDTO
     * @param id
     */
    void update(MauSacDTO mauSacDTO, Long id);

    /**
     * delete
     * loescht ein Farbe Data Transfer Object
     * @param id
     */
    void delete(Long id);

    /**
     * getById
     * gibt ein Farbe Data Transfer Object zurueck
     * @param id
     * @return MauSacDTO
     */
    MauSacDTO getById(Long id);
}
