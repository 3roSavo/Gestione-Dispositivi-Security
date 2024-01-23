package savogineros.Gestionedispositivi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Disabilitiamo alcuni comportamenti di default
        httpSecurity.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable());
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());



        // Aggiungiamo filtri custom
        // Aggiungiamo/Rimuoviamo regole di protezione su singoli endpoint
        // in maniera che venga o non venga richiesta l'autenticazione per accedervi
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/**").permitAll());
        //  tutte le richieste cui l'url seguono questo pattern saranno permesse. Questo pattern si riferisce a tutte le richieste /**
        return httpSecurity.build();
    }
}
