package br.com.challenge.secondNature.SecondNatureSpringBoot.acesso;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

@Table(name="acessos")
@Entity(name="Acesso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_acesso")
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_acesso;

    @Column(name="id_usuario", nullable = false)
    Long id_usuario;

    @Column(name="data_acesso", nullable = false)
    LocalDateTime data_acesso;


    public Acesso(DadosCadastroAcessoDTO dados) {
        this.id_usuario = dados.id_usuario();
        this.data_acesso = LocalDateTime.now();
    }


}
