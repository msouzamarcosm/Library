package io.github.cursodsousa.libraryapi.service;


import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import io.github.cursodsousa.libraryapi.repository.AutorRepository;
import io.github.cursodsousa.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;



    @Transactional
    public void atualizacaoSemAtualizar(){

        var livro =  livroRepository
                .findById(UUID.fromString("035b4cd3-bae8-46dc-88cf-9f7525aa44b3"))
                .orElse(null);

        livro.setDataPublicacao(LocalDate.of(1936,1,2));

    }

    @Transactional
    public void executar(){
        Autor autor = new Autor();
        autor.setNome("Clarice Lispector");
        autor.setNascionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1920, 12, 10));

        autorRepository.save(autor);



        Livro livro = new Livro();
        livro.setIsbn("9878546-987984-9");
        livro.setPreco(BigDecimal.valueOf(30));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("A Hora da Estrela");
        livro.setDataPublicacao(LocalDate.of(1977,1, 2 ));

        livro.setAutor(autor);

        livroRepository.save(livro);

        if (autor.getNome().equals("Jose")){
            throw new RuntimeException("Rollback!");
        }



    }


}
