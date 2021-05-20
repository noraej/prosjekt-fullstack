package idatt2105.hamsterGroup.fullstackProject.Configuration.Security;

import idatt2105.hamsterGroup.fullstackProject.Configuration.JWT.JWTEmailAndPasswordAutFilter;
import idatt2105.hamsterGroup.fullstackProject.Configuration.JWT.JWTTokenVerifier;
import idatt2105.hamsterGroup.fullstackProject.Model.UserSecurity;
import idatt2105.hamsterGroup.fullstackProject.Service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configures WebSecurity for endpoints
 * Not used in tests
 */
@Profile("!test")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserSecurityService userSecurityService;

    /**
     * Method for configuration of http.
     * CSRF is currently countered by CORS, and this app is only used on localhost currently
     * Authentication by username and password, and JWT.
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(getJWTAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JWTTokenVerifier(), JWTEmailAndPasswordAutFilter.class)
                .addFilterAfter(new ExceptionHandlerFilter(), JWTTokenVerifier.class)
                .authorizeRequests()
                .antMatchers("/error").permitAll()
                .antMatchers("/api/v1/activities/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/v1/users").permitAll()
                .antMatchers("/api/v1/websocket/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors();
    }

    /**
     * Sets authentication provider defined in method under
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    /**
     * Sets passwordEncoder and userDetailsService from service folder
     * Needed for getting users from MySQL Database and not In-Memory database
     * Allows authentication from MySQL Database
     * @return DaoAuthenticationProvider
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userSecurityService);
        return provider;
    }
    /**
     * Changes login url
     * @param authenticationManager
     * @return Username and password filter for login
     */
    public JWTEmailAndPasswordAutFilter getJWTAuthenticationFilter(AuthenticationManager authenticationManager){
        final JWTEmailAndPasswordAutFilter filter = new JWTEmailAndPasswordAutFilter(authenticationManager);
        filter.setFilterProcessesUrl("/api/login");
        return filter;
    }
}
