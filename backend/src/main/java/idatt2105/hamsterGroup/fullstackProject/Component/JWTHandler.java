package idatt2105.hamsterGroup.fullstackProject.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import idatt2105.hamsterGroup.fullstackProject.Configuration.JWT.JwtSigningKey;
import idatt2105.hamsterGroup.fullstackProject.Model.UserSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

/**
 * Handler class for JWT (JSON Web Tokens)
 */
public class JWTHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTHandler.class);

    private JWTHandler(){}

    /**
     * Method to verify JWT
     * Makes sure that a user has the rights to execute authorised tasks
     * @param token
     * @return Authentication created from the JWT
     */
    public static Authentication verifyToken(String token){
        try{
            Jws<Claims> claimsJWs = Jwts.parserBuilder()
                    .setSigningKey(JwtSigningKey.getInstance())
                    .build().parseClaimsJws(token);
            Claims body = claimsJWs.getBody();

            String email = body.getSubject();

            int userId = (Integer)body.get("userId");

            List<Map<String, String>> authoritiesMapping = (List<Map<String, String>>) body.get("authorities");

            List<GrantedAuthority> grantedAuthorities = authoritiesMapping.stream()
                    .map(auth -> new SimpleGrantedAuthority(auth.get("authority"))).collect(Collectors.toList());

            UserSecurity userSecurity = new UserSecurity(userId, email, "", grantedAuthorities);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userSecurity, token, grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return authentication;

        } catch(JwtException e) {
            LOGGER.warn("Something went wrong trying to verify JWT token: {}\nException message: {}", token, e.getMessage());
            return null;
        }
    }
}
