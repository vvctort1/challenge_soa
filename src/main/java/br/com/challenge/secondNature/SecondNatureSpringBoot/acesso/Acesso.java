package br.com.challenge.secondNature.SecondNatureSpringBoot.acesso;

import br.com.challenge.secondNature.SecondNatureSpringBoot.validacao.Validavel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name="acessos")
@Entity(name="Acesso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_acesso")
public class Acesso implements Validavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_acesso;

    @Column(name="id_usuario", nullable = false)
    Long id_usuario;

    @Column(name="data", nullable = false)
    LocalDateTime data;


    public Acesso(DadosCadastroAcessoDTO dados) {
        this.id_usuario = dados.id_usuario();
        this.data = LocalDateTime.now();
    }

    @Override
    public boolean validar() {
        return id_usuario != null && data != null;
    }

    @Override
    public String mensagemErro () {
        if (id_usuario == null) return "ID do usuário é obrigatório";
        if (data == null) return "Data é obrigatória";
        return "Dados de acesso inválidos";
    }

}
