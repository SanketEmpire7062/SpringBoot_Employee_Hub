package com.empire.securityconfig;

import com.empire.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;


    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {


        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/registration", "/save").permitAll()
                        .requestMatchers("/forgot-password", "/update-password", "/forgotpassword/**").permitAll()
                        .requestMatchers("/login", "/css/**").permitAll()
                        .requestMatchers("/index").permitAll()

                        .requestMatchers("/admindashboard/", "/css/**").hasRole("ADMIN")
                        .requestMatchers("/employeedashboard/", "/css/**").hasRole("USER")
                        .anyRequest().authenticated()

                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/employeedashboard", true)
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .failureUrl("/login?error=true") // Redirect to /login?error on failure
                        .successHandler(customAuthenticationSuccessHandler)
                        .permitAll()

                )


                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );
        return http.build();

    }

    @Bean
    public AuthenticationManager authmanager(AuthenticationConfiguration authenticationconfiguration) throws Exception {
        return authenticationconfiguration.getAuthenticationManager();
    }




   /*@Bean
    public AuthenticationManager authmanager(HttpSecurity http) throws Exception{
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(regservice)
                .build();

    }*/

  /*  private AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            // Redirect back to the login page with an error message
            request.getSession().setAttribute("errorMessage", "Invalid email or password");
            response.sendRedirect("/login");
        };
    }*/

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
