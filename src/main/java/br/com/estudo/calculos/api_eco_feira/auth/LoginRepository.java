package br.com.estudo.calculos.api_eco_feira.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsuario(String login);

}
