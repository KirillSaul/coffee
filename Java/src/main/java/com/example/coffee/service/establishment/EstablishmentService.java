package com.example.coffee.service.establishment;

import com.example.coffee.protocol.establishment.EstablishmentEditDto;
import com.example.coffee.protocol.establishment.EstablishmentForm;
import com.example.coffee.protocol.establishment.EstablishmentTableDto;
import org.springframework.data.domain.Page;

import java.util.Set;


public interface EstablishmentService {
    Page<EstablishmentTableDto> getAllActive(Integer page, Integer pageSize);
    Page<EstablishmentTableDto> getAllDeleted(Integer page, Integer pageSize);
    Page<EstablishmentTableDto> getAllActiveByCityId(Set<Long> idCities, Integer page, Integer pageSize);
    Page<EstablishmentTableDto> getAllDeletedByCityId(Set<Long> idCities, Integer page, Integer pageSize);
    void deleteEstablishmentById(Long idEstablishment);
    void recoverEstablishmentById(Long idEstablishment);
    void createEstablishment(EstablishmentForm establishmentForm);
    EstablishmentEditDto findByIdForEdit(Long idEstablishment);
    void updateEstablishment(Long idEstablishment, EstablishmentForm establishmentForm);
}
