package br.com.fiap.smartgarbage.controller;

import br.com.fiap.smartgarbage.dto.CollectExhibitionDTO;
import br.com.fiap.smartgarbage.dto.CollectScheduleDTO;
import br.com.fiap.smartgarbage.service.CollectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CollectController {

    @Autowired
    private CollectService collectService;

    /*
        Métodos nativos da JPA
        CREATE, READ, UPDATE, DELETE
    */

    @PostMapping("/collect")
    @ResponseStatus(HttpStatus.CREATED)
    public CollectExhibitionDTO save(@RequestBody @Valid CollectScheduleDTO collect) {
        return collectService.save(collect);
    }

    @GetMapping("/collect")
    @ResponseStatus(HttpStatus.OK)
    public Page<CollectExhibitionDTO> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable){
        return collectService.findAll(pageable);
    }

    @GetMapping("/collect/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CollectExhibitionDTO findById(@PathVariable Long id) {
        return collectService.findById(id);
    }

    @PutMapping("/collect")
    @ResponseStatus(HttpStatus.OK)
    public CollectExhibitionDTO update(@RequestBody CollectScheduleDTO collectScheduleDTO) {
        return collectService.update(collectScheduleDTO);
    }

    @DeleteMapping("/collect")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        collectService.delete(id);
    }

    /*
        Métodos customizados no repository
        Buscar pelo endereço, buscar pela data da coleta, buscar pelo tipo de coleta, buscar pelo status da coleta
    */
}
