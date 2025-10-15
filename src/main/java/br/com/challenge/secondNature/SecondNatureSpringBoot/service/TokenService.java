package br.com.challenge.secondNature.SecondNatureSpringBoot.service;

import br.com.challenge.secondNature.SecondNatureSpringBoot.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private static final String ISSUER = "second-nature-api";

    public String gerarToken(Usuario usuario) {
        // O algoritmo de assinatura agora é criado diretamente.
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(usuario.getEmail())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                // Adicionando claims (informações) customizadas ao token
                .withClaim("id", usuario.getId_usuario())
                .withClaim("nome", usuario.getNome())
                .withClaim("email", usuario.getEmail())
                .sign(algorithm);
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // Cria um verificador com o mesmo algoritmo e issuer
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            // A verificação já retorna o token decodificado ou lança uma exceção
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception){
            // Se o token for inválido (assinatura, expiração, etc.), retorna vazio
            return "";
        }
    }

    public boolean isTokenValido(String token) {
        // A validação é feita verificando se o método validarToken retorna não vazio.
        return !validarToken(token).isEmpty();
    }

    public Long getUsuarioIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);
            // Pega o claim "id" e o converte para Long
            return decodedJWT.getClaim("id").asLong();
        } catch (JWTVerificationException exception){
            return null;
        }
    }
}

