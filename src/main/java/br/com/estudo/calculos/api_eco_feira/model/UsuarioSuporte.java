package br.com.estudo.calculos.api_eco_feira.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class UsuarioSuporte implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuarioSuporte;

    @Column(nullable = false)
    @NotNull(message = "O usuário do suporte é obrigatório!")
    private String usuario;

    @Column(nullable = false)
    @NotNull(message = "A senha é do usuário do suporte é obrigatória")
    @Size(min = 8, message = "O número de caracteres insuficientes - Mínimo 8 caracteres")
    private String senha;

    @ManyToOne
    @JoinColumn(name = "perfilAcessoID", nullable = false)
    private PerfilAcesso perfilAcessoID;

    @OneToMany(mappedBy = "usuarioSuporte")
    private List<Empresa> empresas = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioSuporte")
    private List<Cidade> cidades = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioSuporte")
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioSuporte")
    private List<GrupoEmpresa> grupoEmpresas = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioSuporte")
    private List<Estado> estados = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioSuporte")
    private List<Pais> paises = new ArrayList<>();



    //MÉTODOS AUTOMÁTICOS PARA VALIDAÇÃO DO SPRING
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(perfilAcessoID.getDescricao()));
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
