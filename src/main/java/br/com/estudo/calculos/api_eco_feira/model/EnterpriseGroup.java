package br.com.estudo.calculos.api_eco_feira.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EnterpriseGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "enterpriseGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enterprise> enterprises;

    private boolean active;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDateTime;

    @Column(nullable = false)
    private LocalDateTime lastModifiedDateTime;

    @ManyToOne
    @JoinColumn(name = "userHelpDeskId", nullable = false, updatable = false)
    private UserHelpDesk userHelpDeskRegister;

}
