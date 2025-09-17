package br.com.challenge.secondNature.SecondNatureSpringBoot.checkin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CheckinRepository extends JpaRepository<Checkin, Long> {
    Page<Checkin> findAll(Pageable paginacao);

    // CORRIGIDO: Usando @Query para mapear corretamente o campo id_usuario
    @Query("SELECT c FROM Checkin c WHERE c.id_usuario = :id_usuario ORDER BY c.data_checkin DESC")
    Page<Checkin> findByIdUsuarioOrderByDataCheckinDesc(@Param("id_usuario") Long id_usuario, Pageable paginacao);

    @Query("SELECT c FROM Checkin c WHERE c.id_usuario = :id_usuario AND DATE(c.data_checkin) = CURRENT_DATE")
    Optional<Checkin> findCheckinHoje(@Param("id_usuario") Long id_usuario);

    @Query("SELECT COUNT(c) > 0 FROM Checkin c WHERE c.id_usuario = :id_usuario AND DATE(c.data_checkin) = CURRENT_DATE")
    Boolean jaFezCheckinHoje(@Param("id_usuario") Long id_usuario);
}