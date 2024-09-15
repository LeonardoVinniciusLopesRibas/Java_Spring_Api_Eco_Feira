package br.com.api_eco_feira.dto.estado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadoResponse {

    private Long idEstado;
    private String nome;
    private String sigla;
}
