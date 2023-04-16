package hh.sof3as3.Concerts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
	 @Autowired
	    private UserDetailsService userDetailsService;	

	@Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests()
        	.requestMatchers("/css/**").permitAll() // Enable css when logged out
        	.requestMatchers("/signup", "/saveuser").permitAll()
        	.requestMatchers("/index", "/**").permitAll()
        	.requestMatchers("/search**").permitAll()
        	.requestMatchers(toH2Console()).hasAuthority("ADMIN")
        	.requestMatchers("/api/**").hasAuthority("ADMIN")

        	.anyRequest().authenticated()
        	.and()
        	 .csrf().ignoringRequestMatchers(toH2Console())
        	  .and()
        	  .headers().frameOptions().disable()
        	  .and()
      .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/index", true)
          .permitAll()
          .and()
      .logout()
          .permitAll()
          .and()
      .httpBasic();
    
     
      return http.build();
    }
	
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
	
}
