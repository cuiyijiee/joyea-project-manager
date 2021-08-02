package me.cuiyijie.joyea.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {


    private final static String SECRET = "7758258";
    private final static String ISSUER = "joyea";


    public static String createToken(String id) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withJWTId(id)
                .withIssuer(ISSUER)
                .sign(algorithm);
    }

    public static boolean verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public static String parseToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        String id = jwt.getId();
        return id;
    }

}
