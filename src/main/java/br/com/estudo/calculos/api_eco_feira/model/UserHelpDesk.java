package br.com.estudo.calculos.api_eco_feira.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserHelpDesk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHelpDeskSupport;

    @Column(nullable = false)
    @NotNull(message = "O usuário do suporte é obrigatório!")
    private String user;

    @Column(nullable = false)
    @NotNull(message = "A senha é do usuário do suporte é obrigatória")
    @Size(min = 8, message = "O número de caracteres insuficientes - Mínimo 8 caracteres")
    private String password;

    @OneToMany(mappedBy = "userHelpDeskRegister")
    private List<Enterprise> enterprises = new ArrayList<>();

    @OneToMany(mappedBy = "userHelpDeskRegister")
    private List<City> cities = new ArrayList<>();

    @OneToMany(mappedBy = "userHelpDeskRegister")
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "userHelpDeskRegister")
    private List<EnterpriseGroup> enterpriseGroups = new ArrayList<>();

    @OneToMany(mappedBy = "userHelpDeskRegister")
    private List<State> states = new ArrayList<>();

    @OneToMany(mappedBy = "userHelpDeskRegister")
    private List<Country> countries = new ArrayList<>();

}
