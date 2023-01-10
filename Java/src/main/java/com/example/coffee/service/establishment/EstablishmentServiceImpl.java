package com.example.coffee.service.establishment;

import com.example.coffee.entity.Establishment;
import com.example.coffee.protocol.establishment.*;
import com.example.coffee.protocol.establishment.mapper.EstablishmentEditDtoMapper;
import com.example.coffee.protocol.establishment.mapper.EstablishmentFormMapper;
import com.example.coffee.protocol.establishment.mapper.EstablishmentTableDtoMapper;
import com.example.coffee.repository.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.NoSuchObjectException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstablishmentServiceImpl implements EstablishmentService {
    private final EstablishmentRepository establishmentRepository;
    private final EstablishmentEditDtoMapper establishmentEditDtoMapper;
    private final EstablishmentFormMapper establishmentFormMapper;
    private final EstablishmentTableDtoMapper establishmentTableDtoMapper;

    @Override
    public Page<EstablishmentTableDto> getAllActive(Integer page, Integer pageSize) {
        Page<Establishment> establishments = establishmentRepository.findAllByActiveTrue(PageRequest.of(page, pageSize, Sort.Direction.ASC, "city.name"));
        return new PageImpl<>(establishments
                .stream()
                .map(establishmentTableDtoMapper::toDto)
                .collect(Collectors.toList()), PageRequest.of(page, pageSize), establishments.getTotalElements());
    }

    @Override
    public Page<EstablishmentTableDto> getAllDeleted(Integer page, Integer pageSize) {
        Page<Establishment> establishments = establishmentRepository.findAllByActiveFalse(PageRequest.of(page, pageSize, Sort.Direction.ASC, "city.name"));
        return new PageImpl<>(establishments
                .stream()
                .map(establishmentTableDtoMapper::toDto)
                .collect(Collectors.toList()), PageRequest.of(page, pageSize), establishments.getTotalElements());
    }

    @Override
    public Page<EstablishmentTableDto> getAllActiveByCityId(Set<Long> idCities, Integer page, Integer pageSize) {
        Page<Establishment> establishments = establishmentRepository.findAllByCityIdCityInAndActiveTrue(idCities, PageRequest.of(page, pageSize, Sort.Direction.ASC, "city.name"));
        return new PageImpl<>(establishments
                .stream()
                .map(establishmentTableDtoMapper::toDto)
                .collect(Collectors.toList()), PageRequest.of(page, pageSize), establishments.getTotalElements());
    }

    @Override
    public Page<EstablishmentTableDto> getAllDeletedByCityId(Set<Long> idCities, Integer page, Integer pageSize) {
        Page<Establishment> establishments = establishmentRepository.findAllByCityIdCityInAndActiveFalse(idCities, PageRequest.of(page, pageSize, Sort.Direction.ASC, "city.name"));
        return new PageImpl<>(establishments
                .stream()
                .map(establishmentTableDtoMapper::toDto)
                .collect(Collectors.toList()), PageRequest.of(page, pageSize), establishments.getTotalElements());
    }

    @Override
    @Transactional
    public void deleteEstablishmentById(Long idEstablishment) {
        establishmentRepository.findById(idEstablishment).ifPresent(establishment -> establishment.setActive(false));
    }

    @Override
    @Transactional
    public void recoverEstablishmentById(Long idEstablishment) {
        establishmentRepository.findById(idEstablishment).ifPresent(establishment -> establishment.setActive(true));
    }

    @Override
    public void createEstablishment(EstablishmentForm establishmentForm) {
        establishmentRepository.save(establishmentFormMapper.toEntity(establishmentForm));
    }

    @Override
    @SneakyThrows
    public EstablishmentEditDto findByIdForEdit(Long idEstablishment){
        Establishment establishment = establishmentRepository.findByIdEstablishment(idEstablishment).orElseThrow(() -> new NoSuchObjectException("can`t find establishment with id = " + idEstablishment));
        return establishmentEditDtoMapper.toDto(establishment);
    }

    @Override
    @Transactional
    @SneakyThrows
    public void updateEstablishment(Long idEstablishment, EstablishmentForm establishmentForm){
        Establishment establishmentFromForm = establishmentFormMapper.toEntity(establishmentForm,idEstablishment);
        Establishment establishmentFromRepo = establishmentRepository.findByIdEstablishment(idEstablishment).orElseThrow(() -> new NoSuchObjectException("can`t find establishment with id = " + idEstablishment));
        establishmentFromRepo.setCity(establishmentFromForm.getCity());
        establishmentFromRepo.setStreet(establishmentFromForm.getStreet());
        establishmentFromRepo.setStreetNumber(establishmentFromForm.getStreetNumber());
        establishmentFromRepo.setFirstPhoneNumber(establishmentFromForm.getFirstPhoneNumber());
        establishmentFromRepo.setSecondPhoneNumber(establishmentFromForm.getSecondPhoneNumber());
        establishmentFromRepo.setSchedules(establishmentFromForm.getSchedules());
    }
}
