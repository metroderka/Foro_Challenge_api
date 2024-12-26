package com.foro.api.infra.security;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.foro.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;


@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;
    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("foro")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(generarFechaDeExpiracion())
                    .withClaim("id",usuario.getId())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }
    public String getSubject (String token){
        if (token == null){
            throw new RuntimeException() ;
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // Validar firma
            verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("foro")
                    // reusable verifier instance
                    .build()
                    .verify(token);
            verifier.getSubject();

        } catch (JWTVerificationException exception) {
            // Invalid signature/claims
            System.out.println(exception.toString());
        }
        if(verifier.getSubject() == null ){
            throw new RuntimeException("Verifier inválido");
        }
        return verifier.getSubject();
    }
/*
        DecodedJWT decoded = JWT.decode(token);
        DecodedJWT verified = JWT.require(Algorithm.HMAC256(apiSecret))
                .withIssuer("foro")
                .build()
                .verify(token);
        System.out.println(decoded);
        System.out.println(verified.getSubject());

        return verified.getSubject();
    }

*/
    private Instant generarFechaDeExpiracion(){
        return LocalDateTime.now().plusHours(5).toInstant(ZoneOffset.UTC);
    }
}
