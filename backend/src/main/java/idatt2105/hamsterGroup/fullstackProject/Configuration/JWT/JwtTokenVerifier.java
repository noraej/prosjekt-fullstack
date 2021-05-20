package idatt2105.hamsterGroup.fullstackProject.Configuration.JWT;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import idatt2105.hamsterGroup.fullstackProject.Component.JWTHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter for verifying a token from request header
 */
public class JwtTokenVerifier extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenVerifier.class);

    /**
     * Verifies the JWT token from request,return 403 if expired
     * @param request - request from client
     * @param response - response to client
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        LOGGER.info("doFilterInternal(HttpServletRequest request, HttpServletResponse response, " +
                "FilterChain filterChain) is called");

        // If request tries to create user, it will not try to verify token, but continue the through
        // filters until it gets to registration endpoint
        if(request.getRequestURI().equals("/api/v1/users") && request.getMethod().equals("POST")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("Authorization");
        JWTHandler.verifyToken(token);
        // Sends the request to the next filter, which will be exception-handler and then the controller
        filterChain.doFilter(request, response);
    }
}
