package br.com.api_eco_feira.dto.prefeitura;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrefeituraRequest {

    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;


}
