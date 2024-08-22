package br.com.estudo.calculos.api_eco_feira.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EnterpriseGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnterpriseGroup;
    private String fantasyName;
    private String companyName;
    private String cnpj;

}
