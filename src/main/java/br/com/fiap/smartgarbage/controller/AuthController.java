package br.com.fiap.smartgarbage.controller;

import br.com.fiap.smartgarbage.dto.LoginDTO;
import br.com.fiap.smartgarbage.dto.TokenDTO;
import br.com.fiap.smartgarbage.dto.UserExhibitionDTO;
import br.com.fiap.smartgarbage.dto.UserInsertDTO;
import br.com.fiap.smartgarbage.model.UserModel;
import br.com.fiap.smartgarbage.service.TokenService;
import br.com.fiap.smartgarbage.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(
            @RequestBody
            @Valid
            LoginDTO loginDTO
    ){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.email(), loginDTO.password()
        );
        Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        String token = tokenService.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserExhibitionDTO register(
            @RequestBody
            @Valid
            UserInsertDTO userInsertDTO
    ){
        UserExhibitionDTO savedUser = null;
        savedUser = userService.save(userInsertDTO);
        return savedUser;
    }
}
