package br.com.api_eco_feira.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Aceite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotNull(message = "E-mail não pode ser vazio")
    private String email;

    @NotNull(message = "Senha não pode ser vazia")
    @Size(min = 6, message = "Senha deve conter no mínimo 6 caracteres")
    private String senha;

    @CNPJ
    private String cnpj;

}
