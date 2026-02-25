package io.github.cursodsousa.libraryapi.repository;


import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){

        Livro livro = new Livro();
        livro.setIsbn("9878546-987984");
        livro.setPreco(BigDecimal.valueOf(150));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("1984");
        livro.setDataPublicacao(LocalDate.of(1986, 1, 2));


        Autor autor = autorRepository.findById(UUID.fromString("79d41b1d-314d-46a7-84f9-a4493ffee9ce"))
                .orElse(null);


        livro.setAutor(autor);


       repository.save(livro);

    }

    @Test
    void salvarCascadeTest(){

        Livro livro = new Livro();
        livro.setIsbn("9878546-987984");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro Livro");
        livro.setDataPublicacao(LocalDate.of(1980,1, 2 ));

        Autor autor = new Autor();
        autor.setNome("Joao");
        autor.setNascionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31).atStartOfDay());




        livro.setAutor(autor);

        repository.save(livro);

    }
    @Test
    void salvarAutorELivroTest(){

        Livro livro = new Livro();
        livro.setIsbn("9878546-987984");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Terceiro Livro");
        livro.setDataPublicacao(LocalDate.of(1980,1, 2 ));

        Autor autor = new Autor();
        autor.setNome("Jose");
        autor.setNascionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31).atStartOfDay());

        autorRepository.save(autor);

        livro.setAutor(autor);

        repository.save(livro);

    }
    @Test
    void atualizarAutorDoLivro(){
        UUID id = UUID.fromString("ef8e304d-a396-40fb-a656-6697af101399");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("79d41b1d-314d-46a7-84f9-a4493ffee9ce");
        Autor Maria = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(Maria);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletar(){
        UUID id = UUID.fromString("ef8e304d-a396-40fb-a656-6697af101399");
        repository.deleteById(id);

    }

    @Test
    @Transactional
    void pesquisaPorTituloTest(){
        List<Livro> lista = repository.findByTitulo("Grande Sertões Veredas");
        lista.forEach(System.out::println);

    }

    @Test
    void deletePorGenero(){
        repository.deleteByGenero(GeneroLivro.CIENCIA);
    }








}
