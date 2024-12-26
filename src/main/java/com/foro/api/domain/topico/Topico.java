package com.foro.api.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.time.*;
import java.time.temporal.ChronoUnit;

import static java.lang.Boolean.TRUE;

//Nota: Lombok no está generando Constructor sin args , ni Getters
//GET Da ERROR500 , se resuelve retirando @NoArgsConstructor e introduciendo constructor vacío
//Devuelve HTTP200 y Json vacío , se resuelve retirando @Getter y Generando Getters y Setters
//Al no haber Getters , Jackson envía un JSON vacío.

@Table(name = "topicos")
@Entity(name = "Topico")
//@Getter
//@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String titulo;
    private String mensaje ;
    private String fecha;
    private Boolean status;
    private String autor;
    private String curso;
    // Constructor vacío para Jpa
    public Topico(){};

    public Topico(DatosTopico datosTopico) {
        this.titulo = datosTopico.titulo();
        this.mensaje = datosTopico.mensaje();
        this.fecha = fechaDeCreacion();
        this.status = TRUE;
        this.autor = datosTopico.autor();
        this.curso = datosTopico.curso();
    }
    private String fechaDeCreacion() {
        return LocalDateTime.now()
                .truncatedTo(ChronoUnit.SECONDS).toString();
    }

    @Override
    public String toString() {
        return "Topico{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", fechaDeCreacion=" + fecha +
                ", status=" + status +
                ", autor='" + autor + '\'' +
                ", curso='" + curso + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo() != null) {
            this.setTitulo(datosActualizarTopico.titulo());
        }
        if(datosActualizarTopico.mensaje()!=null) {
            this.setMensaje(datosActualizarTopico.mensaje());
        }
        // La fecha cambia siempre que se acceda al Tópico para actualizarlo
        this.setFecha(fechaDeCreacion());

        if(datosActualizarTopico.status()!=null) {
            this.setStatus(datosActualizarTopico.status());
        }
        if(datosActualizarTopico.autor()!=null) {
            this.setAutor(datosActualizarTopico.autor());
        }
        if(datosActualizarTopico.curso()!=null) {
            this.setCurso(datosActualizarTopico.curso());
        }
    }
}
