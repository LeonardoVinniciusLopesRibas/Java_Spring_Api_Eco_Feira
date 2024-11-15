package br.com.api_eco_feira.dto.quantidade;

import br.com.api_eco_feira.model.produtor.Empresa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuantidadeAtendidaResponseList {

    private Long id;
    private String produtor;
    private String cnpj;
    private double quantidade;

}
