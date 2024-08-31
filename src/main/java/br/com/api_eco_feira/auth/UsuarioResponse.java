package br.com.api_eco_feira.auth;

import br.com.api_eco_feira.model.prefeitura.Prefeitura;
import br.com.api_eco_feira.model.produtor.Empresa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

    private Long id;
    private String usuario;
    private String perfil;
    private boolean suporte;
    private boolean produtor;
    private boolean prefeitura;
    private Empresa empresaAssociation;
    private Prefeitura prefeituraAssociation;

    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.usuario = usuario.getUsuario();
        this.perfil = usuario.getPerfil();
        this.suporte = usuario.isSuporte();
        this.produtor = usuario.isProdutor();
        this.prefeitura = usuario.isPrefeitura();
        this.empresaAssociation = usuario.getEmpresaAssociation();
        this.prefeituraAssociation = usuario.getPrefeituraAssociation();
    }

}
