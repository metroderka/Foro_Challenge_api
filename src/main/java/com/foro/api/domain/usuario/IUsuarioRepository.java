package com.foro.api.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUsuarioRepository extends JpaRepository <Usuario,Long>{
    UserDetails findByLogin(String username);

}
