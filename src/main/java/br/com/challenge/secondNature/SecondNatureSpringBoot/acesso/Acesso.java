package br.com.challenge.secondNature.SecondNatureSpringBoot.acesso;

import br.com.challenge.secondNature.SecondNatureSpringBoot.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

@Table(name="acessos")
@Entity(name="Acesso")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id_acesso")

public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_acesso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private LocalDateTime data;

    public Acesso() {}

    public Acesso(Usuario usuario, LocalDateTime data) {
        this.usuario = usuario;
        this.data = (data != null ? data : LocalDateTime.now());
    }

    public Long getId_acesso() { return id_acesso; }
    public Long getId_usuario() { return usuario.getId_usuario(); }
    public LocalDateTime getData() { return data; }
}
