package org.ivanmros.holamundo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    //@Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
//    @Bean
//    protected InMemoryUserDetailsManager configAuthentication() {
//
//        List<UserDetails> users = new ArrayList<>();
//        List<GrantedAuthority> adminAuthority = new ArrayList<>();
//        adminAuthority.add(new SimpleGrantedAuthority("ADMIN"));
//        UserDetails admin= new User("admin", "{noop}admin", adminAuthority);
//        users.add(admin);
//
//        List<GrantedAuthority> userAuthority = new ArrayList<>();
//        adminAuthority.add(new SimpleGrantedAuthority("USER"));
//        UserDetails user= new User("user", "{noop}user", userAuthority);
//        users.add(user);
//
//        return new InMemoryUserDetailsManager(users);
//    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/editar/**", "/agregar/**", "/eliminar", "/guardar").hasAuthority("ADMIN")
                .anyRequest().permitAll()
                .and().exceptionHandling().accessDeniedPage("/errores/403")
                .and()
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                );

        return http.build();
    }

//    @Bean
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                //Creaar un usuario
//                .withUser("admin")
//                    .password("{noop}123")
//                    .roles("ADMIN", "USER")
//                .and()
//                //crear otro usuario
//                .withUser("usuario")
//                    .password("{noop}123")
//                    .roles("USER");
//    }
}
