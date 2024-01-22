package savogineros.Gestionedispositivi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import savogineros.Gestionedispositivi.exceptions.UnauthorizedException;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    // doFilterInternal è il metodo di questo filtro verrà eseguito per ogni richiesta che richieda di essere autenticati, e dovrà:

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            throw new UnauthorizedException("Per favore metti il token nell' Authorization header");
        }
    // Creo un filtro che andò ad aggiungere alla Security Filter Chain

    // 1. Verifichiamo se la richiesta contiene un Authorization Header ed eventualmente estraiamo il token da esso
    // 2. Verifichiamo se il token è scaduto o se è stato manipolato (verifica signature)
    // 3. Se è tutto ok possiamo proseguire al prossimo elemento della chain (e prima o poi si arriverà al controller)
    }

}
