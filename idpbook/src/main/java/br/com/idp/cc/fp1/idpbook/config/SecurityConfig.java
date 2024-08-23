package br.com.idp.cc.fp1.idpbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            ) // Configura o CSRF para usar tokens armazenados em cookies, o que é uma prática comum
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/", "/home", "/register", "/login", "/css/**").permitAll() // Páginas públicas
                .requestMatchers("/posts").permitAll()
                .requestMatchers("/posts/add").authenticated()
                .anyRequest().authenticated() // Qualquer outra requisição precisa estar autenticada
            )
            .formLogin(formLogin -> formLogin
                .loginPage("/login") // Página de login personalizada
                .loginProcessingUrl("/perform_login") 
                .failureUrl("/login?error=true")
                .usernameParameter("email")
                .defaultSuccessUrl("/posts", true) // Redireciona após o login bem-sucedido
                .permitAll()
            )

            .logout(logout -> logout
                //.logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")  // Redireciona para a página de login após logout
                .invalidateHttpSession(true)  // Invalida a sessão HTTP
                .deleteCookies("JSESSIONID")  // Exclui o cookie de sessão
                .permitAll() 
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utiliza o BCrypt para codificação de senhas
    }
}
