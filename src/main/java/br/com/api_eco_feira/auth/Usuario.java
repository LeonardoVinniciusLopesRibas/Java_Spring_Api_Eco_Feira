package br.com.api_eco_feira.auth;

import br.com.api_eco_feira.model.prefeitura.Prefeitura;
import br.com.api_eco_feira.model.produtor.Empresa;
import br.com.api_eco_feira.model.produtor.Promocao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O usuário é obrigatório")
    @Column(nullable = false)
    private String usuario;

    @NotNull(message = "A senha é obrigatória")
    @Column(nullable = false)
    private String senha;
    private String perfil;
    private boolean suporte;
    private boolean produtor;
    private boolean prefeitura;

    @ManyToOne
    @JoinColumn(name = "empresaId")
    private Empresa empresaAssociation;

    @ManyToOne
    @JoinColumn(name = "prefeituraId")
    private Prefeitura prefeituraAssociation;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.perfil));
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return usuario;
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
