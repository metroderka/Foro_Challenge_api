package com.foro.api.domain.topico;

import lombok.extern.jackson.Jacksonized;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        String fecha ,
        Boolean status ,
        String autor,
        String curso) {
    public DatosListadoTopico(Topico topico){
        this(topico.getId(),topico.getTitulo(), topico.getMensaje(), topico.getFecha()
                ,topico.getStatus() ,topico.getAutor(), topico.getCurso());
    }
}
