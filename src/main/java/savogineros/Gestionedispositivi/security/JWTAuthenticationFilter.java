package savogineros.Gestionedispositivi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import savogineros.Gestionedispositivi.exceptions.UnauthorizedException;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    // Creo un filtro che andrò ad aggiungere alla Security Filter Chain
    @Autowired
    private JWTTools jwtTools;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    // doFilterInternal è il metodo di questo filtro che verrà eseguito per ogni richiesta che richieda di essere autenticati, e dovrà:

        String authHeader = request.getHeader("Authorization"); // "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjOTY0YjQwYy1iMzliLTQ4NzYtYTBkYi1jNDA4Yjc5ZDlhNDUiLCJpYXQiOjE3MDU5MjIyMjYsImV4cCI6MTcwNjUyNzAyNn0.stxc0Bko-lN8ej_Yp8hpjLTQmmlnzqJChrDQ7XkAR0Q"

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Devo controllare che ci sia l'autorizzazione e che cominci con Bearer
            throw new UnauthorizedException("Per favore metti il token nell' Authorization header");
        } else {

            // Devo estrarmi il token tagliando via "Bearer "
            // si può utilizzare replace(), substring(), splice()?
            String accessToken = authHeader.substring(7);

            // 1. Verifichiamo se la richiesta contiene un Authorization Header ed eventualmente estraiamo il token da esso
            jwtTools.verifyToken(accessToken);

            // 2. Verifichiamo se il token è scaduto o se è stato manipolato (verifica signature)
            // 3. Se è tutto ok possiamo proseguire al prossimo elemento della chain (e prima o poi si arriverà al controller)
        }
    }

}
