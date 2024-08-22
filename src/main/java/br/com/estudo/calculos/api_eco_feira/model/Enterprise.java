package br.com.estudo.calculos.api_eco_feira.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnterprise;

    @Column(nullable = false)
    @NotNull(message = "O nome fantasia é obrigatório")
    private String fantasyName;

    @Column(nullable = false)
    @NotNull(message = "O nome fantasia é obrigatório")
    private String companyName;

    @Column(nullable = false)
    @NotNull(message = "O nome fantasia é obrigatório")
    private String cnpj;

    @Column(nullable = false)
    @NotNull(message = "O telefone é obrigatório")
    private String phoneNumber;

    @Column(nullable = false)
    @NotNull(message = "O e-mail é obrigatório")
    private String email;

    @Column(nullable = false)
    @NotNull(message = "A atividade principal é obrigatória")
    private String mainActivity;

    @Column(length = 500)
    private String description;

    @Column(nullable = true)
    private String logoUrl;

    @OneToMany(mappedBy = "enterprise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

    private boolean active;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDateTime;

    @Column(nullable = false)
    private LocalDateTime lastModifiedDateTime;

    @ManyToOne
    @JoinColumn(name = "userHelpDeskId", nullable = false, updatable = false)
    private UserHelpDesk userHelpDeskRegister;

    @ManyToOne
    @JoinColumn(name = "enterpriseGroupId", nullable = false)
    private EnterpriseGroup enterpriseGroup;

    @OneToMany(mappedBy = "enterprise")
    private List<UserEnterpriseAssociation> userEnterpriseAssociations = new ArrayList<>();
}
