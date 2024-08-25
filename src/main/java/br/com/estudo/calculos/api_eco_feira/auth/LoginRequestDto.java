package br.com.estudo.calculos.api_eco_feira.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    private String usuario;
    private String senha;

}
