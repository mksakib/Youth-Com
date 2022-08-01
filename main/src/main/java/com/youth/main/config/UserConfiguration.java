package com.youth.main.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security
    .authentication.dao.DaoAuthenticationProvider;
import org.springframework.security
    .config.annotation.authentication
        .builders.AuthenticationManagerBuilder;
import org.springframework.security
    .config.annotation.web.builders.HttpSecurity;
import org.springframework.security
    .config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security
    .config.annotation.web.configuration
      .WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security
    .crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.util.UrlPathHelper;

@Configuration
@EnableWebSecurity
public class UserConfiguration 
            extends WebSecurityConfigurerAdapter {

//   @Lazy
//   @Autowired
//   private UserService userService;
   
//   @Autowired
//   private DataSource dataSource;
    
  @Bean
   public UserDetailsService userDetailsService() {
       return new UserServiceConfig();
   }

   @Bean
   @Autowired
   public BCryptPasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   /*@Bean
   public DaoAuthenticationProvider authenticationProvider() {
      
      DaoAuthenticationProvider auth = 
            new DaoAuthenticationProvider();
      auth.setUserDetailsService(userDetailsService());
      auth.setPasswordEncoder(passwordEncoder());
      return auth;
   }

    @Override
   protected void configure(AuthenticationManagerBuilder auth) 
          throws Exception {
      
      auth.authenticationProvider(authenticationProvider());
   }*/

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      
	   http.authorizeRequests()
	   	   .antMatchers("/user/**")
	   	   .hasRole("USER")
	   	   .antMatchers("/**")
	   	   .permitAll()
	   	   .and()
	   	   .formLogin()
           .failureHandler(new AuthenticationFailureHandler() {
        	   
        	   @Override
        	   public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        			   AuthenticationException exception) throws IOException, ServletException {
        		   
        		   System.out.println("login failed, error : "+exception.getLocalizedMessage());
        		   
        		   UrlPathHelper urlPathHelper = new UrlPathHelper();
        		   response.sendRedirect("/user_login?errorccurence");
        		   
        	   }
        	   
           })
	   	   .loginPage("/user_login")
	   	   .loginProcessingUrl("/dologin")
	   	   .defaultSuccessUrl("/")
	   	   .and()
	   	   .csrf()
	   	   .disable();
	   
	   
//	   http.authorizeRequests()
//	   	   .antMatchers("/*",
//	   			"/user_registration**",
//                "/js/**",
//                "/css/**",
//                "/img/**").permitAll()
//           .antMatchers("/user_login")
//       	   .hasAuthority("USER")
//           .and()
//           .formLogin()
//           .loginPage("/login")
//           .permitAll()
//           .and()
//           .logout()
//           .invalidateHttpSession(true)
//           .clearAuthentication(true)
//           .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//           .logoutSuccessUrl("/login?logout")
//           .permitAll();
	   
      
      

   }
}











