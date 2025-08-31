package br.com.movieflix.config;

import br.com.movieflix.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TokenService {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);   //forma de criptografia

        return JWT.create()             //cria o token
                .withSubject(user.getEmail()) //quem é o meu username
                .withClaim("userId", user.getId()) //outras informações que eu vou adicionar no user
                .withClaim("name", user.getName())
                .withExpiresAt(Instant.now().plusSeconds(86400)) //o tempo que esse token expira
                .withIssuedAt(Instant.now()) //o momento que o token foi criado
                .withIssuer("MovieFlix") //quem criou esse token
                .sign(algorithm); //no final retorna criptografado a String
    }

}
