package com.foro.api.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ITopicoRepository extends JpaRepository <Topico,Long> {

    Page<Topico> findAll(Pageable paginacion);

    @Query("""
            select t from Topico t
            where
            t.titulo = :titulo
            and t.mensaje = :mensaje
            """)
    Optional<Topico> findTopicoByTituloAndByMensaje(String titulo , String mensaje);



}
