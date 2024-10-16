package br.com.fiap.smartgarbage.service;

import br.com.fiap.smartgarbage.dto.CollectExhibitionDTO;
import br.com.fiap.smartgarbage.dto.CollectScheduleDTO;
import br.com.fiap.smartgarbage.exception.NotFoundException;
import br.com.fiap.smartgarbage.model.CollectModel;
import br.com.fiap.smartgarbage.repository.CollectRepository;
import br.com.fiap.smartgarbage.utils.CollectStatusEnum;
import br.com.fiap.smartgarbage.utils.CollectTypeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CollectService {

    // Injeção de dependência
    @Autowired
    private CollectRepository collectRepository;

    /*
        Métodos nativos da JPA
        CREATE, READ, UPDATE, DELETE
    */
    public CollectExhibitionDTO save(CollectScheduleDTO collectDTO) {
        CollectModel collectModel = new CollectModel();
        BeanUtils.copyProperties(collectDTO, collectModel);
        if(collectModel.getCollectStatus() == null){
            collectModel.setCollectStatus(CollectStatusEnum.SCHEDULED);
        }
        if (collectModel.getCollectType() == null) {
            collectModel.setCollectType(CollectTypeEnum.ORGANIC);
        }
        CollectModel collectSaved = collectRepository.save(collectModel);
        return new CollectExhibitionDTO(collectSaved);
    }

    public CollectExhibitionDTO findById(Long id) {
        Optional<CollectModel> collectOption = collectRepository.findById(id);
        if(collectOption.isPresent()) {
            return new CollectExhibitionDTO(collectOption.get());
        } else {
            throw new NotFoundException("Coleta não encontrada!");
        }
    }

    public Page<CollectExhibitionDTO> findAll(Pageable pageable) {
        return collectRepository.findAll(pageable).map(CollectExhibitionDTO::new);
    }

    public void delete(Long id) {
        Optional<CollectModel> collectOption = collectRepository.findById(id);
        if(collectOption.isPresent()) {
            collectRepository.delete(collectOption.get());
        } else {
            throw new NotFoundException("Coleta não encontrada!");
        }
    }

    public CollectExhibitionDTO update(CollectScheduleDTO collectScheduleDTO){
        Optional<CollectModel> collectOption = collectRepository.findById(collectScheduleDTO.id());
        if(collectOption.isPresent()) {
            CollectModel colectModel = new CollectModel();
            BeanUtils.copyProperties(collectScheduleDTO, colectModel);
            return new CollectExhibitionDTO(collectRepository.save(colectModel));
        } else{
            throw new NotFoundException("Coleta não encontrada!");
        }
    }

    /*
        Métodos customizados no repository
        Buscar pelo endereço, buscar pela data da coleta, buscar pelo tipo de coleta, buscar pelo status da coleta
    */

    public CollectExhibitionDTO findByAddress(String address) {
        Optional<CollectModel> collectOption = collectRepository.findByAddress(address);
        if(collectOption.isPresent()) {
            return new CollectExhibitionDTO(collectOption.get());
        } else {
            throw new NotFoundException("Coleta não encontrada!");
        }
    }

    public List<CollectExhibitionDTO> findByScheduledDateTimeBetween(LocalDateTime start, LocalDateTime end){
        Optional<List<CollectModel>> collectOption = collectRepository.findByScheduledDateTimeBetween(start, end);
        if(collectOption.isPresent()) {
            return collectOption.get().stream().map(CollectExhibitionDTO::new).toList();
        } else {
            throw new NotFoundException("Coleta não encontrada!");
        }
    }

    public List<CollectExhibitionDTO> findByCollectType(String collectType){
        Optional<List<CollectModel>> collectOption = collectRepository.findByCollectType(collectType);
        if(collectOption.isPresent()) {
            return collectOption.get().stream().map(CollectExhibitionDTO::new).toList();
        } else {
            throw new NotFoundException("Coleta não encontrada!");
        }
    }

    public List<CollectExhibitionDTO> findByCollectStatus(String collectStatus){
        Optional<List<CollectModel>> collectOption = collectRepository.findByCollectStatus(collectStatus);
        if(collectOption.isPresent()) {
            return collectOption.get().stream().map(CollectExhibitionDTO::new).toList();
        } else {
            throw new NotFoundException("Coleta não encontrada!");
        }
    }
}
