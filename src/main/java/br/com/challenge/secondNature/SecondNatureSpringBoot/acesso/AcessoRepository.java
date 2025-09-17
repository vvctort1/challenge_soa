package br.com.challenge.secondNature.SecondNatureSpringBoot.acesso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface AcessoRepository extends JpaRepository<Acesso, Long> {
    Page<Acesso> findAll(Pageable paginacao);

    // CORRIGIDO: Usando @Query para mapear corretamente o campo id_usuario
    @Query("SELECT a FROM Acesso a WHERE a.id_usuario = :id_usuario ORDER BY a.data_acesso DESC")
    Page<Acesso> findByIdUsuarioOrderByDataAcessoDesc(@Param("id_usuario") Long id_usuario, Pageable paginacao);

    @Query("SELECT a FROM Acesso a WHERE DATE(a.data_acesso) = CURRENT_DATE")
    List<Acesso> findAcessosHoje();

    // CORRIGIDO: Usando @Query para contar acessos por usuário
    @Query("SELECT COUNT(a) FROM Acesso a WHERE a.id_usuario = :id_usuario")
    Long countByIdUsuario(@Param("id_usuario") Long id_usuario);
}