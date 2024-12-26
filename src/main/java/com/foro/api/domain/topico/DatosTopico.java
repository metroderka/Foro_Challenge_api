package com.foro.api.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosTopico(
        Long idUsuario,
        @NotNull String titulo,
        @NotNull String mensaje ,
        @NotNull String autor,
        @NotNull String curso){
}
