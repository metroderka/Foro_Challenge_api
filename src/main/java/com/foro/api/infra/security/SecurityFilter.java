package com.foro.api.infra.security;

import com.foro.api.domain.topico.ITopicoRepository;
import com.foro.api.domain.usuario.IUsuarioRepository;
import com.foro.api.domain.usuario.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.rmi.RemoteException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    ITopicoRepository topicoRepository;
    @Autowired
    IUsuarioRepository usuarioRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("El filtro está siendo llamado");

        var authHeader = request.getHeader("Authorization");
//        if (token == null || token =="") {
//            throw new RuntimeException("El token enviado no es válido.");
//        }
        if (authHeader != null ) {
            System.out.println("El Token no es null.");
            var token = authHeader.replace("Bearer ", "");
            System.out.println(token);
            var subject = tokenService.getSubject(token);
            System.out.println(subject);
            if (subject != null){
                var usuario = usuarioRepository.findByLogin(subject);
                var authentication = new UsernamePasswordAuthenticationToken(
                        usuario,null,usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);
       // System.out.println(tokenService.getSubject(token));

    }

}

