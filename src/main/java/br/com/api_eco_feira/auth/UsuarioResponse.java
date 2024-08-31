package br.com.api_eco_feira.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

    private String usuario;
    private String perfil;
    private boolean suporte;
    private boolean produtor;
    private boolean prefeitura;

    public UsuarioResponse(Usuario usuario) {
        this.usuario = usuario.getUsuario();
        this.perfil = usuario.getPerfil();
        this.suporte = usuario.isSuporte();
        this.produtor = usuario.isProdutor();
        this.prefeitura = usuario.isPrefeitura();
    }

}
