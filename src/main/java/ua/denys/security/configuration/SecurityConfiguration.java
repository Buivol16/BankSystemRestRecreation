package ua.denys.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import ua.denys.controller.LoginController;
import ua.denys.security.handler.BankAuthenticationFailureHandler;
import ua.denys.security.service.BankAuthenticationManager;
import ua.denys.security.service.BankUserDetailsService;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private LoginController loginController;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, BankUserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder) throws Exception {
        return httpSecurity
                .formLogin()
                .loginPage("/login")
                .usernameParameter("name")
                .passwordParameter("pass")
                .defaultSuccessUrl("/home")
                .failureHandler(authenticationFailureHandler(loginController))
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/register")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager(passwordEncoder))
                .build();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(LoginController loginController){
        return new BankAuthenticationFailureHandler(loginController);
    }

    @Bean
    public BankAuthenticationManager authenticationManager(BCryptPasswordEncoder passwordEncoder){
        return new BankAuthenticationManager(passwordEncoder);
    }
}
