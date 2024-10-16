package br.com.fiap.smartgarbage.service;

import br.com.fiap.smartgarbage.model.UserModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${minha.chave}")
    private String secretWord;

    public String generateToken(UserModel userModel){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretWord);
            String token = JWT.create()
                    .withIssuer("smart-garbage")
                    .withSubject(userModel.getEmail())
                    .withExpiresAt(generateExpirateDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e){
            throw new RuntimeException("Não foi possível gerar o token!");
        }
    }

    private Instant generateExpirateDate(){
        return LocalDateTime
                .now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretWord);

            return JWT.require(algorithm)
                    .withIssuer("smart-garbage")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException e){
            return "";
        }
    }
}