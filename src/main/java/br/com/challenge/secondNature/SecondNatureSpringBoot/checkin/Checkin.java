package br.com.challenge.secondNature.SecondNatureSpringBoot.checkin;


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
public class Checkin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_checkin;

    @Column(name = "id_usuario",nullable = false)
    Long id_usuario;

    @Column(name = "humor", nullable = false)
    String humor;

    @Column(name = "data_checkin", nullable = false)
    LocalDateTime data_checkin;

    @Enumerated(EnumType.STRING)
    Impulsividade impulsividade_nivel;

    public Checkin(DadosCadastroCheckinDTO dados){
        this.id_usuario = dados.id_usuario();
        this.humor = dados.humor();
        this.data_checkin = LocalDateTime.now();
        this.impulsividade_nivel = dados.impulsividade_nivel();
    }
}
