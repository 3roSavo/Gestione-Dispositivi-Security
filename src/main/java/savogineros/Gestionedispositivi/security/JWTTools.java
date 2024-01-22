package savogineros.Gestionedispositivi.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import savogineros.Gestionedispositivi.entities.Utente;

import java.util.Date;
@Component // Rendo la classe componente per recuperarmi attraverso @Value il valore del segreto
public class JWTTools {
    @Value("${spring.jwt.secret}") // Assegno a secret il valore di spring.jwt.secret
    private String secret;

    // Qui mi creerò il token per l'utente che ha superato i controlli della mail e password
    // Un token è composto da tre parti: HEADER, PAYLOAD, SIGNATURE
    // Per crearlo dovrò recuperare le sue componenti
    public String createToken(Utente utente){
        String accessToken = Jwts.builder()   // builder -> costruiamo il token
                .subject(String.valueOf(utente.getId()))    // subject --> a chi appartiene il token (id dell'utente)
                // ho utilizzato String.valueOf() per convertire in String l'id che è di tipo UUID
                .issuedAt(new Date(System.currentTimeMillis()))    // Data di emissione  (IAT - Issued At)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))  // Data di scadenza  (Expiration date)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))   // Firmo il token, con annesso il segreto preso da application properties
                .compact();   // Finalizzo il token
        // ho utilizzato String.valueOf() per convertire in String l'id che è di tipo UUID
        return accessToken;
    }

    public void verifyToken(){

    }
}
