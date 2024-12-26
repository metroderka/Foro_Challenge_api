package com.foro.api.domain.topico;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(

        @NotNull Long id,
        @NotNull String titulo,
        @NotNull String mensaje,
     //   String fecha ,
        @NotNull Boolean status ,
        @NotNull String autor,
        @NotNull String curso) {

}
