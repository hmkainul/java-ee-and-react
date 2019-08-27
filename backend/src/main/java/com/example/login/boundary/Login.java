package com.example.login.boundary;

import java.util.Optional;
import java.security.MessageDigest;
import java.util.Base64;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import com.example.Tokens;

@Stateless
public class Login {

    @PersistenceContext
    EntityManager em;

    @Inject
    Tokens tokens;

    private static String QUERY = 
        "SELECT * FROM users WHERE username = ? AND password = ?";

    public Optional<String> login(String username, String password) {
        String jwt = null;
        try {
            em.createNativeQuery(QUERY)
                .setParameter(1, username)
                .setParameter(2, digest(password))
                .getSingleResult();
            jwt = tokens.createToken(username);
        } catch (NoResultException e) {
        }
        return Optional.ofNullable(jwt);
    }

    public String digest(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(password.getBytes("UTF-8"));
            return new String(Base64.getEncoder().encode(digest.digest()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
