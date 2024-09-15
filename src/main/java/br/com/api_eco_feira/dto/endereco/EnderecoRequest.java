package br.com.api_eco_feira.dto.endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequest {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private Long cidadeId;

}
