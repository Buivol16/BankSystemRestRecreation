package ua.denys.security.configuration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import ua.denys.controller.LoginController;
import ua.denys.security.service.BankUserDetailsService;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private LoginController loginController;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, BankUserDetailsService userDetailsService) throws Exception {
        return httpSecurity
                .formLogin()
                .loginPage("/login")
                .usernameParameter("name")
                .passwordParameter("pass")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login")
                .clearAuthentication(true)
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
                .build();
    }

}
