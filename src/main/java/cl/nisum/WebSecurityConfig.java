package cl.nisum;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	 
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		   httpSecurity.authorizeRequests()		   
		   .antMatchers("/").permitAll().and()
           .authorizeRequests().antMatchers("/console/**").permitAll()
           .antMatchers("/api/v1/user/private/**").authenticated();
		   httpSecurity.csrf().disable()
		   .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);		   
		   httpSecurity.headers().frameOptions().disable();
	}
	
}