package br.com.api_eco_feira.auth;

import br.com.api_eco_feira.config.JwtServiceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository repository;
    @Autowired
    private JwtServiceGenerator jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    public String logar(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsuario(),
                        loginRequestDto.getSenha()
                )
        );
        Usuario user = repository.findByUsuario(loginRequestDto.getUsuario()).get();
        String jwtToken = jwtService.generateToken(user);

        return jwtToken;
    }

}
