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
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAddress;

    @Column(nullable = false)
    @NotNull(message = "A rua é obrigatória")
    private String street;

    @Column(nullable = false)
    @NotNull(message = "O número é obrigatório")
    private String number;

    private String complement;

    @Column(nullable = false)
    @NotNull(message = "O bairro é obrigatório")
    private String neighborhood;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityId", nullable = false)
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterpriseId", nullable = false)
    private Enterprise enterprise;

    private boolean active;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDateTime;

    @Column(nullable = false)
    private LocalDateTime lastModifiedDateTime;

    @ManyToOne
    @JoinColumn(name = "userHelpDeskId", nullable = false, updatable = false)
    private UserHelpDesk userHelpDeskRegister;

}
