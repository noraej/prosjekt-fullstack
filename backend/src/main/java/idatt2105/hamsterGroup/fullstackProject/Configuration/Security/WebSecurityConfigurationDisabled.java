package idatt2105.hamsterGroup.fullstackProject.Configuration.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Testing purposes only, turns off WebSecurity
 * Not used in SecurityControllerTest
 */
@Configuration
@Profile("test")
public class WebSecurityConfigurationDisabled extends WebSecurityConfigurerAdapter{
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**");
    }
}
