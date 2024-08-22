package br.com.estudo.calculos.api_eco_feira.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserHelpDesk implements UserDetails {

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

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;

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



    //MÉTODOS AUTOMÁTICOS PARA VALIDAÇÃO DO SPRING
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getDescription()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return user;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
