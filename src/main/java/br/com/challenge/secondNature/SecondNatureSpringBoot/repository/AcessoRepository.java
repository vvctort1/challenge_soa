package br.com.challenge.secondNature.SecondNatureSpringBoot.repository;

import br.com.challenge.secondNature.SecondNatureSpringBoot.acesso.Acesso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AcessoRepository extends JpaRepository<Acesso, Long> {

    // Método básico do JpaRepository - deve funcionar
    Page<Acesso> findAll(Pageable paginacao);

    // Query específica para buscar por usuário
    @Query("SELECT a FROM Acesso a WHERE a.id_usuario = :id_usuario ORDER BY a.data DESC")
    Page<Acesso> findByIdUsuarioOrderByDataAcessoDesc(@Param("id_usuario") Long id_usuario, Pageable paginacao);

    // Query para buscar acessos de hoje - CORRIGIDA
    @Query(value = "SELECT * FROM acessos WHERE DATE(data) = CURDATE()", nativeQuery = true)
    List<Acesso> findAcessosHoje();

    // Query para contar acessos por usuário
    @Query("SELECT COUNT(a) FROM Acesso a WHERE a.id_usuario = :id_usuario")
    Long countByIdUsuario(@Param("id_usuario") Long id_usuario);
}
