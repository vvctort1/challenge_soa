package br.com.challenge.secondNature.SecondNatureSpringBoot.acesso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;

public interface AcessoRepository extends JpaRepository<Acesso, Long> {
    Page<Acesso> findAll(Pageable paginacao); //bucar todos

    Page<Acesso> findByIdUsuarioOrderByDataAcessoDesc(Long id_usuario, Pageable paginacao); //usuário específico

    @Query("SELECT a FROM Acesso a WHERE DATE(a.data_acesso) = CURRENT_DATE")
    List<Acesso> findAcessosHoje();

    Long countByIdUsuario(Long id_usuario); //acessos por usuario

}
