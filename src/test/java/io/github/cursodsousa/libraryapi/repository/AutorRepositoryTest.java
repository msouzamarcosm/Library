package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;



@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;


    @Autowired
    LivroRepository livroRepository;

   @Test
    public void salvarTest(){

        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNascionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31).atStartOfDay());

        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);


    }
    @Test
    public void atualizarTest(){
        var id = UUID.fromString("79d41b1d-314d-46a7-84f9-a4493ffee9ce");

        Optional<Autor> possivelAutor =  repository.findById(id);

        if(possivelAutor.isPresent()) {

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor: ");
            System.out.println(possivelAutor.get());

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30).atStartOfDay());

            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest(){

        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores "+ repository.count());
    }

    @Test
    public void deletePorIdTest(){
       var id = UUID.fromString("792d4c92-bce2-463f-9cbf-3c00934e5bd6");
       repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("c9ba87ba-b0c1-432c-acd6-39c26f255761");
        var maria =  repository.findById(id).get();
        repository.delete(maria);
    }

    @Test
    void salvarAutorComLivrosTest() {
        Autor autor = new Autor();
        autor.setNome("Graciliano Ramos");
        autor.setNascionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1892, 10, 27).atStartOfDay());

        Livro livro = new Livro();
        livro.setIsbn("978-85-209-1209-6");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setTitulo("Grande Sertões Veredas");
        livro.setDataPublicacao(LocalDate.of(1956, 10, 2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("913-97-802-9273-1");
        livro2.setPreco(BigDecimal.valueOf(75));
        livro2.setGenero(GeneroLivro.ROMANCE);
        livro2.setTitulo("Vidas Secas");
        livro2.setDataPublicacao(LocalDate.of(1938, 4, 12));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);



        livroRepository.saveAll(autor.getLivros());
    }

}
