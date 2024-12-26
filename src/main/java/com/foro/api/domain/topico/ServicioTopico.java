package com.foro.api.domain.topico;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class ServicioTopico {
    @Autowired
    private ITopicoRepository topicoRepository;

    public boolean registroAutorizado(DatosTopico datosTopico) {
        var checkTituloYMensaje = topicoRepository.findTopicoByTituloAndByMensaje(
                datosTopico.titulo(), datosTopico.mensaje());

        if (checkTituloYMensaje.isEmpty()) {
            System.out.println("Título y mensaje no existen. Registro autorizado.");
            return TRUE;
        } else {
            System.out.println("Título y mensaje ya existen. Registro rechazado.");
            System.out.println(checkTituloYMensaje.toString());
            return FALSE;
        }
    }

    public ResponseEntity<DatosRespuestaTopico> saveTopicoToDB(
            DatosTopico datosTopico, UriComponentsBuilder uriComponentsBuilder) {
        if (registroAutorizado(datosTopico) == TRUE) {
            Topico saveTopico = new Topico(datosTopico);
            topicoRepository.save(saveTopico);
            URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(
                    saveTopico.getId()).toUri();
            return ResponseEntity.created(url).body(new DatosRespuestaTopico(saveTopico.getId(),
                    saveTopico.getTitulo(),saveTopico.getMensaje(),
                    saveTopico.getFecha(),saveTopico.getStatus(),
                    saveTopico.getAutor(),saveTopico.getCurso()));
        }else{

            DatosRespuestaTopico entity = new DatosRespuestaTopico(null,
                    "Título y mensaje ya existen.",
                    "Registro Rechazado",null,
                    null,null,null);

            return new ResponseEntity<>(entity,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<DatosRespuestaTopico> updateTopicoToDB(
            @Valid DatosActualizarTopico datosActualizarTopico) {
        DatosTopico datosTopico = new DatosTopico(
                null, datosActualizarTopico.titulo(),
                datosActualizarTopico.mensaje(), null,null);
        if(registroAutorizado(datosTopico)){
            Topico saveTopico = topicoRepository.getReferenceById(datosActualizarTopico.id());
            saveTopico.actualizarDatos(datosActualizarTopico);
            DatosRespuestaTopico entity = new DatosRespuestaTopico(saveTopico.getId(),
                    saveTopico.getTitulo(),saveTopico.getMensaje(),
                    saveTopico.getFecha(),saveTopico.getStatus(),
                    saveTopico.getAutor(),saveTopico.getCurso());
        //    URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(saveTopico.getId()).toUri();
        //    return ResponseEntity.created(url).body(entity);
            return ResponseEntity.ok(entity);
        }else{

            DatosRespuestaTopico entity = new DatosRespuestaTopico(null,
                    "Título y mensaje sin modificar.",
                    "Registro Rechazado",null,
                    null,null,null);

            return new ResponseEntity<>(entity,HttpStatus.BAD_REQUEST);
        }
        }


    }

