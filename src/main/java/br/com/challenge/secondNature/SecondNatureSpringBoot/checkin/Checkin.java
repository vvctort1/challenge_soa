package br.com.challenge.secondNature.SecondNatureSpringBoot.checkin;


import br.com.challenge.secondNature.SecondNatureSpringBoot.validacao.Validavel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name="checkins")
@Entity(name="Checkin")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id_checkin")
public class Checkin implements Validavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_checkin;

    @Column(name = "id_usuario",nullable = false)
    Long id_usuario;

    @Column(name = "humor", nullable = false)
    String humor;

    @Column(name = "data", nullable = false)
    LocalDateTime data;

    @Enumerated(EnumType.STRING)
    Impulsividade impulsividade_nivel;

    public Checkin(DadosCadastroCheckinDTO dados){
        this.id_usuario = dados.id_usuario();
        this.humor = dados.humor();
        this.data = LocalDateTime.now();
        this.impulsividade_nivel = dados.impulsividade_nivel();
    }

    @Override
    public boolean validar() {
        return id_usuario != null && humor != null && !humor.isEmpty() && data != null && impulsividade_nivel != null;
    }

    @Override
    public String mensagemErro() {
        if (id_usuario == null) return "ID do usuário é obrigatório";
        if (humor == null || humor.isEmpty()) return "Humor é obrigatório";
        if (data == null) return "Data é obrigatória";
        if (impulsividade_nivel == null) return "Nível de impulsividade é obrigatório";
        return "Dados de check-in inválidos";
    }
}
