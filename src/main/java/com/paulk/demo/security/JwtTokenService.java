package com.paulk.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * A {@link Service} for managing {@link Jwts} and {@link Claims}.
 */
@Service
public class JwtTokenService {
    private static final String TOKEN_KEY = "ut1FfO9sSPjG1OKxVh";
    private static final String TYP = "typ";
    private static final String JWT = "JWT";

    /**
     * Generate a JWT token.
     *
     * @param username   - The {@link String} username to be processed.
     * @param claims     - The {@link Claims} to be submitted.
     * @param timeOffSet - The expiration time offset for the JWT Token.
     * @return A new {@link Jwts} token in {@link String} format signed with {@link SignatureAlgorithm#HS512}.
     */
    public static String generateToken(String username, Claims claims, Integer timeOffSet) {
        return Jwts.builder()
                .setHeaderParam(TYP, JWT)
                .setClaims(claims)
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + timeOffSet))
                .signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .compact();
    }

    /**
     * Verify the token submitted.
     *
     * @param token - The {@link String} JWT token to be processed.
     * @throws JwtException thrown if token is invalid.
     */
    public static void verifyToken(String token) throws JwtException {
        Jwts.parser()
                .setSigningKey(TOKEN_KEY)
                .parse(token.substring(7));
    }

    /**
     * Get the {@link Claims} from the token provided.
     *
     * @param token - The token to be processed.
     * @return The {@link Claims} embedded in the token.
     */
    public static Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(TOKEN_KEY)
                .parseClaimsJws(token.substring(7))
                .getBody();
    }

    /**
     * A utility method for updating the provided Tokens expiration date.
     *
     * @param token  - The {@link String} to be updated.
     * @param offSet - The expiration time in milliseconds to offset the new token expiration date.
     * @return The updated token.
     */
    public static String updateExpirationDateToken(String token, Integer offSet) {
        Claims claims = getClaimsFromToken(token);
        return Jwts.builder()
                .setHeaderParam(TYP, JWT)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + offSet))
                .signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .compact();
    }
}
