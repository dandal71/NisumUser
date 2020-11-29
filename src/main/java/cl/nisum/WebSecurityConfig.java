package cl.nisum;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		   httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
           .authorizeRequests().antMatchers("/console/**").permitAll()
           .antMatchers("/api/v2/user/**").authenticated();
		   httpSecurity.csrf().disable();
		   httpSecurity.headers().frameOptions().disable();

	}
}