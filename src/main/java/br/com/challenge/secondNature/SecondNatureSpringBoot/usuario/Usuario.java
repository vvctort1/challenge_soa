package br.com.challenge.secondNature.SecondNatureSpringBoot.usuario;

import br.com.challenge.secondNature.SecondNatureSpringBoot.validacao.Validavel;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name="usuarios")
@Entity(name="Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_usuario")
public class Usuario implements Validavel, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_usuario;

    @Column(nullable = false, length = 100)
    String nome;

    @Column(nullable = false, unique = true, length = 100)
    String email;

    @Column(nullable = false)
    String senha;

    @Column(nullable = false)
    Boolean ativo = true;

    public Usuario(DadosCadastroUsuarioDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoUsuarioDTO dados){
        if(dados.nome() != null && !dados.nome().trim().isEmpty()){
            this.nome = dados.nome();
        }

        if(dados.email() != null && !dados.email().trim().isEmpty()){
            this.email = dados.email();
        }

        if(dados.senha() != null && !dados.senha().trim().isEmpty()){
            this.senha = dados.senha();
        }
    }

    public void excluir(){
        this.ativo = false;
    }

    @Override
    public boolean validar() {
        return nome != null && !nome.isEmpty()
                && email != null && !email.isEmpty()
                && senha != null && !senha.isEmpty()
                && ativo != null;
    }

    @Override
    public String mensagemErro() {
        if (nome == null || nome.isEmpty()) return "Nome é obrigatório";
        if (email == null || email.isEmpty()) return "Email é obrigatório";
        if (senha == null || senha.isEmpty()) return "Senha é obrigatória";
        return "Dados inválidos";
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
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
        return ativo;
    }
}