package br.com.estudo.calculos.api_eco_feira.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCity;

    @Column(nullable = false)
    @NotNull(message = "O nome da cidade não pode estar vazio")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "O código do ibge é obrigatório")
    private int ibge;

    //MUITAS CIDADES PARA UM ESTADO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stateId", nullable = false)
    private State state;

    private boolean active;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDateTime;

    @Column(nullable = false)
    private LocalDateTime lastModifiedDateTime;

    @ManyToOne
    @JoinColumn(name = "userHelpDeskId", nullable = false, updatable = false)
    private UserHelpDesk userHelpDeskRegister;

}
