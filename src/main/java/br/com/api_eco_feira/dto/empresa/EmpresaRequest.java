package br.com.api_eco_feira.dto.empresa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaRequest {

    private String nomeFantasia;
    private String razaoSocial;
    private String numeroTelefone;
    private String email;
    private String atividadePrincipal;
    private String descricao;

}
