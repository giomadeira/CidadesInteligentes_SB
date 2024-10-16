package br.com.fiap.smartgarbage.service;

import br.com.fiap.smartgarbage.dto.UserExhibitionDTO;
import br.com.fiap.smartgarbage.dto.UserInsertDTO;
import br.com.fiap.smartgarbage.exception.NotFoundException;
import br.com.fiap.smartgarbage.model.UserModel;
import br.com.fiap.smartgarbage.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserExhibitionDTO save(UserInsertDTO userInsertDTO) {

        String cryptedPassword = new BCryptPasswordEncoder().encode(userInsertDTO.password());

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userInsertDTO, userModel);

        userModel.setPassword(cryptedPassword);

        UserModel userSaved = userRepository.save(userModel);
        return new UserExhibitionDTO(userSaved);
    }

    public UserExhibitionDTO findById(Long id) {
        Optional<UserModel> userModelOptional = userRepository.findById(id);
        if(userModelOptional.isPresent()) {
            return new UserExhibitionDTO(userModelOptional.get());
        } else {
            throw new NotFoundException("Usuário não encontrado");
        }
    }

    public Page<UserExhibitionDTO> findAll(Pageable pageable) {
        return userRepository
            .findAll(pageable)
            .map(UserExhibitionDTO::new);
    }

    public UserExhibitionDTO update(UserInsertDTO userInsertDTO) {
        Optional<UserModel> userModelOptional = userRepository.findById(userInsertDTO.id());
        if(userModelOptional.isPresent()) {
            UserModel userModel = new UserModel();
            BeanUtils.copyProperties(userInsertDTO, userModel);
            return new UserExhibitionDTO(userRepository.save(userModel));
        } else{
            throw new NotFoundException("Usuário não encontrado");
        }
    }

    public void delete(Long id) {
        Optional<UserModel> userModelOptional = userRepository.findById(id);
        if(userModelOptional.isPresent()) {
            userRepository.delete(userModelOptional.get());
        } else {
            throw new NotFoundException("Usuário não encontrado");
        }
    }
}
