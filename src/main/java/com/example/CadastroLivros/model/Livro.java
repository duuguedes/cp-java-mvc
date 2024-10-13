package com.example.CadastroLivros.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="TB_MVC_LIVROS")
@Getter @Setter @NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "livro")
    @SequenceGenerator(name = "livro", sequenceName = "sq_biblioteca_livro", allocationSize = 1)
    @Column(name="cdLivro", length = 9)
    private Long id;

    @Column(name = "nm_livro", nullable = false, length = 100)
    private String titulo;

    @Column(name = "nm_autor", nullable = false, length = 100)
    private String autor;

    @Column(name = "ds_genero", nullable = false, length = 70)
    private String genero;

    @Column(name = "dt_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Column(name = "ds_resumo", nullable = false, length = 500)
    private String resumo;


}
