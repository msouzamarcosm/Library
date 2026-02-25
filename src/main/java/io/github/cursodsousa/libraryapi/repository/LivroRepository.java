package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    List<Livro> findByAutor (Autor autor);

    List<Livro> findByTitulo(String titulo);

    List<Livro> findByIsbn (String isbn);

    List<Livro> findByDataPublicacaoBetween (LocalDate inicio, LocalDate fim);

    @Query(" select l from Livro as l  order by l.titulo, l.preco ")

    List<Livro>  listarTodosOrdenadoPorTituloAndPreco();

    @Query("select l from Livro l where l.genero = :genero")
    List<Livro> findByGenero(@Param("genero") GeneroLivro generoLivro);


    @Modifying
    @Transactional
    @Query(" delete from Livro l where l.genero = ?1 ")
    void deleteByGenero(GeneroLivro genero);

    @Modifying
    @Transactional
    @Query(" update Livro set dataPublicacao = ?1  ")
    void updateDataPublicacao(LocalDate novaData);


}




