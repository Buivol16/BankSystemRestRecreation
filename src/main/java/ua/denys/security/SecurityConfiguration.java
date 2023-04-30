package ua.denys.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.server.WebFilterChain;
import ua.denys.enums.Role;

import static ua.denys.enums.Role.USER;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(BCryptPasswordEncoder passwordEncoder){
        final var userDetail = User.builder().username("admin").password(passwordEncoder.encode("root")).authorities(new SimpleGrantedAuthority(USER.name())).build();
        return new InMemoryUserDetailsManager(userDetail);
    }


}
