package com.example;

import java.util.Optional;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class Tokens {

    public String createToken(String username) {
        return JWT
            .create()
            .withIssuer(issuer())
            .withSubject(username)
            .sign(algorithm());
    }

    private String issuer() {
        return "example";
    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256(secret());
    }

    private String secret() {
        return "foo";
    }

    public Optional<String> getUsernameFromToken(String token) {
        String username = null;
        try {
            username = tryVerify(token);
        } catch (JWTVerificationException e) {
        }
        return Optional.ofNullable(username);
    }

    private String tryVerify(String token) {
        JWTVerifier verifier = verifier();
        DecodedJWT decoded = verifier.verify(token);
        return decoded.getSubject();
    }

    private JWTVerifier verifier() {
        return JWT
            .require(algorithm())
            .withIssuer(issuer())
            .build();
    }

}
