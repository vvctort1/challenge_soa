package br.com.challenge.secondNature.SecondNatureSpringBoot.usuario;

import jakarta.persistence.*;
import lombok.*;

@Table(name="usuarios")
@Entity(name="Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private Boolean ativo = true;

    public Usuario(DadosCadastroUsuarioDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoUsuarioDTO dados) {
        if (dados.nome() != null && !dados.nome().trim().isEmpty()) {
            this.nome = dados.nome();
        }
        if (dados.email() != null && !dados.email().trim().isEmpty()) {
            this.email = dados.email();
        }
        if (dados.senha() != null && !dados.senha().trim().isEmpty()) {
            this.senha = dados.senha();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
