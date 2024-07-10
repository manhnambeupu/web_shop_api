package web_shop.api_web_shop.service;

import web_shop.api_web_shop.dto.KichCoDTO;

import java.util.List;

public interface KichCoService {
    /**
     * getAll
     * gibt alle Groesse Data Transfer Object zurueck
     * @return List<KichCoDTO>
     */
    List<KichCoDTO> getAll();

    /**
     * create
     * erstellt ein Groesse Data Transfer Object
     * @param kichCoDTO
     */
    void create(KichCoDTO kichCoDTO);

    /**
     * update
     * aktualisiert ein Groesse Data Transfer Object
     * @param kichCoDTO
     * @param id
     */
    void update(KichCoDTO kichCoDTO, Long id);

    /**
     * delete
     * loescht ein Groesse Data Transfer Object
     * @param id
     */
    void delete(Long id);

    /**
     * getById
     * gibt ein Groesse Data Transfer Object zurueck
     * @param id
     * @return KichCoDTO
     */
    KichCoDTO getById(Long id);
}
