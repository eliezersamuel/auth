package com.org.auth.service;

import com.org.auth.dto.UserDTO;
import com.org.auth.dto.UserLoginDTO;
import com.org.auth.entity.UserEntity;
import com.org.auth.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public LoginService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public ResponseEntity<?> signup (UserDTO userDTO) {
        try{
            var password = userDTO.password();

            userRepository.save(userDTO.toUserEntity(this.passwordEncoder.encode(password)));
            log.info("usuário criado com sucesso!");
            return new ResponseEntity<>("usuário criado", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("e: ", new RuntimeException(e));
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<?> login (UserLoginDTO userLoginDTO) {
        try{
            Optional<UserEntity> user = userRepository.findByEmail(userLoginDTO.email());
            if(user.isEmpty()){
                log.error("usuário not found");
                return ResponseEntity.notFound().build();
            }

            boolean matches = this.passwordEncoder.matches(userLoginDTO.password(), user.get().getPassword());

            if(!matches){
                log.error("Senha inválida");
                return new ResponseEntity<>("Senha inválida", HttpStatus.UNAUTHORIZED);
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("e: ", new RuntimeException(e));
            return ResponseEntity.internalServerError().build();
        }
    }
}
