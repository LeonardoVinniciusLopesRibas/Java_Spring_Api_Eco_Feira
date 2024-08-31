package br.com.api_eco_feira.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCidade;

    @Column(nullable = false)
    @NotNull(message = "O nome da cidade não pode estar vazio")
    private String nome;

    @Column(nullable = false)
    @NotNull(message = "O código do ibge é obrigatório")
    private int ibge;

    @ManyToOne
    @JoinColumn(name = "estadoId", nullable = false)
    private Estado estado;

    private boolean ativo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataHoraAlteracao = LocalDateTime.now();

}
