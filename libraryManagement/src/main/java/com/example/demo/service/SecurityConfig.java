package com.example.demo.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler; 

@Configuration 
@EnableWebSecurity
public class SecurityConfig  { 

//   @Bean 
//   protected UserDetailsService userDetailsService() {
//      UserDetails user = User.builder()
//         .username("user")
//         .password(passwordEncoder().encode("user123"))
//         .roles("USER")
//         .build();
//      UserDetails admin = User.builder()
//         .username("admin")
//         .password(passwordEncoder().encode("admin123"))
//         .roles("USER", "ADMIN")
//         .build();
//      return new InMemoryUserDetailsManager(user, admin);
//   }
     
//	private final loginService userDetailsService;
//
//    public SecurityConfig(loginService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
    
   @Bean 
   protected PasswordEncoder passwordEncoder() { 
      return new BCryptPasswordEncoder(); 
   }

   @Bean
   protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
      return http
         .csrf(AbstractHttpConfigurer::disable)
         .authorizeHttpRequests(
            request -> request.requestMatchers("/login").permitAll()
            .requestMatchers("/**").authenticated()
            .requestMatchers("/admin/**").hasRole("Admin") // 🚫 Blocks USERS from admin pages
            .requestMatchers("/user/**").hasRole("User")
            .anyRequest().authenticated()
         )
         .formLogin(form -> form
                 .loginPage("/login")
                 .loginProcessingUrl("/login")
                 .successHandler(customAuthenticationSuccessHandler()) // Custom redirect based on role
                 .permitAll()
             )   
         .logout(config -> config  
         .logoutUrl("/logout") 
         .invalidateHttpSession(true) // ✅ Clear session
         .deleteCookies("JSESSIONID")
         .logoutSuccessUrl("/login")) 
         .build();
   }
   
   @Bean
   public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
       return (request, response, authentication) -> {
           authentication.getAuthorities().forEach(grantedAuthority -> {
               try {
            	 //  System.out.println(grantedAuthority.getAuthority()+" hi");
                   if (grantedAuthority.getAuthority().equals("ROLE_Admin")) {
                       response.sendRedirect("/admin");
                   } else if (grantedAuthority.getAuthority().equals("ROLE_User")) {
                       response.sendRedirect("/user/userbooksdetails");
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               }
           });
       };
   }
   
   @Bean 
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
   }

   
}