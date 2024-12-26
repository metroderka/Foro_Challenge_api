package com.foro.api.controller;

import com.foro.api.domain.topico.*;
import org.springframework.data.domain.Page;
//import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private ITopicoRepository topicoRepository;
    @Autowired
    private ServicioTopico servicioTopico;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(
            @RequestBody @Valid DatosTopico datosTopico,
            UriComponentsBuilder uriComponentsBuilder){
        //return ResponseEntity.ok(guardaTopico.saveTopicoToDB(datosTopico));
        return servicioTopico.saveTopicoToDB(datosTopico,uriComponentsBuilder);
    }
    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(
            @RequestBody @Valid  DatosActualizarTopico datosActualizarTopico,
            UriComponentsBuilder uriComponentsBuilder){
        return servicioTopico.updateTopicoToDB(datosActualizarTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional public ResponseEntity deleteTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> obtenerDetalleTopico(@PathVariable @Valid Long id){
        return ResponseEntity.ok(
                new DatosListadoTopico(topicoRepository.getReferenceById(id)));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> obtenerListaTopico(
            @PageableDefault(size = 5 , sort = "fecha" , direction = Sort.Direction.ASC)
            Pageable paginacion){
        List<Topico> topicosList = topicoRepository.findAll();
        System.out.println(topicosList);
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
    }

    public TopicoController(){}
}
