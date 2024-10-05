package br.com.api_eco_feira.dto.demanda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemandaDtoRequest {

    private Long idPrefeitura;
    private double valorTotalPrefeitura;
    private LocalDate prazoMaximo;

}
