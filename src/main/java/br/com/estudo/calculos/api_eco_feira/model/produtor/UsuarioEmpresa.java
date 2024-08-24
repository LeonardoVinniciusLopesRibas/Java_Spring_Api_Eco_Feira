package br.com.estudo.calculos.api_eco_feira.model.produtor;

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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UsuarioEmpresa implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuarioEmpresa;

    @Column(nullable = false)
    @NotNull(message = "O usuário do suporte é obrigatório!")
    private String usuario;

    @Column(nullable = false)
    @NotNull(message = "A senha é do usuário do suporte é obrigatória")
    @Size(min = 8, message = "O número de caracteres insuficientes - Mínimo 8 caracteres")
    private String senha;

    @ManyToOne
    @JoinColumn(name = "perfilAcessoId", nullable = false)
    private PerfilAcesso perfilAcessoId;

    @OneToMany(mappedBy = "usuarioEmpresa")
    private List<UsuarioEmpresa_Empresa_Associados> usuarioEmpresa_empresa_associados = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioEmpresa")
    private List<Produto_Promocao_Associados> produto_promocao_associados = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioEmpresa")
    private List<Produto> produtos = new ArrayList<>();

    private boolean ativo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataHoraAlteracao = LocalDateTime.now();


    //MÉTODOS AUTOMÁTICOS PARA VALIDAÇÃO DO SPRING
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(perfilAcessoId.getDescricao()));
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
