package br.com.fiap.smartgarbage.repository;

import br.com.fiap.smartgarbage.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserDetails findByEmail(String email);
}
