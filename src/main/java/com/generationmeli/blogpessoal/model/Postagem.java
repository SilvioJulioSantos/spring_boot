package com.generationmeli.blogpessoal.model;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "tb_postagens")//Nome da tabela
public class Postagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Titulo é obrigatório e não pode ser vazio!")// diz que não pode ser vazio / usar só com string
    @Size(min = 5, max = 100, message = "O titúlo deve conter no mínimo 5 e no máximo 100 caracteres")
    //informa o tamanho do campo
    private String titulo;

    @NotNull(message = "O texto é obrigatório!")// pode ser usado para qqr coisa
    @Size(min = 10, max = 1000, message = "O texto deve conter no mínimo 10 e no máximo 1000 caracteres")
    private String texto;

    @UpdateTimestamp//Data automaticamente do servidor
    private LocalDate data;


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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
























