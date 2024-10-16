package br.com.fiap.smartgarbage.controller;

import br.com.fiap.smartgarbage.dto.UserExhibitionDTO;
import br.com.fiap.smartgarbage.dto.UserInsertDTO;
import br.com.fiap.smartgarbage.service.UserService;
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
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserExhibitionDTO save(@RequestBody @Valid UserInsertDTO userInsertDTO){
        return userService.save(userInsertDTO);
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserExhibitionDTO> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable){
        return userService.findAll(pageable);
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserExhibitionDTO findById(@PathVariable @Valid Long id){
        return userService.findById(id);
    }

    @PutMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public UserExhibitionDTO update(@RequestBody @Valid UserInsertDTO userInsertDTO){
        return userService.update(userInsertDTO);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable @Valid Long id){
        userService.delete(id);
    }
}
