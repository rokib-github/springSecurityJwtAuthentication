package com.rokib.springSecurityJwtAuthentication.security.jwt;

import com.rokib.springSecurityJwtAuthentication.security.core.AppUserPrinciple;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

@Component
@Slf4j
public class JwtUtils {

    private final String jwtSecret;
    private final long jwtExpiration;

    public JwtUtils(
            @Value("${app.jwtSecret}") String jwtSecret,
            @Value("${app.jwtExpiration}") long jwtExpiration) {
        this.jwtSecret = jwtSecret;
        this.jwtExpiration = jwtExpiration;
    }

    /**
     * The generateJwtToken() method will generate Jwt Token
     *
     * @author Md. Rokibul hasan
     * @version 1.0
     * @since 2020-04-07
     */
    public String generateJwtToken(Authentication authentication) {

        AppUserPrinciple userPrincipal = (AppUserPrinciple) authentication.getPrincipal();
        Date now = new Date();
        Date expiryTime = new Date(now.getTime() + jwtExpiration);
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(now)
                .setExpiration(expiryTime)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * The getClaimsFromJwtToken() method will return claims from token payload
     *
     * @author Md. Rokibul hasan
     * @version 1.0
     * @since 2020-04-07
     */
    public String getClaimsFromJwtToken(String token) {
        String userName = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return userName;
    }

    /**
     * The validateJwtToken() method will validate JWT signature, Expiration time etc.
     *
     * @author Md. Rokibul hasan
     * @version 1.0
     * @since 2020-04-07
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty -> Message: {}", e);
        }
        return false;
    }

    /**
     * The getJwt() method will return JWT token from Header
     *
     * @author Md. Rokibul hasan
     * @version 1.0
     * @since 2020-04-07
     */
    public String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (Objects.nonNull(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
}
