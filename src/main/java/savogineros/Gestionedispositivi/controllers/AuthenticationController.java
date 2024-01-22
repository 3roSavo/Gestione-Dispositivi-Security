package savogineros.Gestionedispositivi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import savogineros.Gestionedispositivi.payloadsDTO.Utente.LoginUtenteRequestDTO;
import savogineros.Gestionedispositivi.payloadsDTO.Utente.LoginUtenteResponseDTO;
import savogineros.Gestionedispositivi.services.AuthenticationService;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginUtenteResponseDTO login(@RequestBody LoginUtenteRequestDTO loginUtente) {
        String token = authenticationService.authenticateUtente(loginUtente);
        return new LoginUtenteResponseDTO(token);
    }
}
