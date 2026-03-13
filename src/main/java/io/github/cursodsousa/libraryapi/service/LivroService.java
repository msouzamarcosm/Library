package io.github.cursodsousa.libraryapi.service;

import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import io.github.cursodsousa.libraryapi.repository.LivroRepository;
import io.github.cursodsousa.libraryapi.validator.LivroValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


import java.util.Optional;
import java.util.UUID;

import static io.github.cursodsousa.libraryapi.repository.specs.LivroSpecs.*;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    private final LivroValidator validator;

    public Livro salvar(Livro livro) {
        validator.validar(livro);
        return repository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id){

        return repository.findById(id);
    }

    public void deletar(Livro livro){
        repository.delete(livro);
    }

    public Page<Livro> pesquisa(
            String isbn,
            String titulo,
            String nomeAutor,
            GeneroLivro genero,
            Integer anoPublicacao,
            Integer pagina,
            Integer tamanhopagina){

      //  Specification<Livro> specs = Specification.
       //         where(LivroSpecs.isbnEqual(isbn))
         //       .and(LivroSpecs.tituloLike(titulo))
           //     .and(LivroSpecs.generoEqual(genero));

        Specification<Livro> specs = Specification.where((root, query, cb) ->
                cb.conjunction());

        if (isbn != null){

            specs = specs.and(isbnEqual(isbn));
        }
        if (titulo != null){

            specs = specs.and(tituloLike(titulo));
        }
        if (genero != null){

            specs = specs.and(generoEqual(genero));
        }

        if (anoPublicacao != null){
            specs = specs.and(anoPublicacaoEqual(anoPublicacao));
        }

        if (nomeAutor != null){
            specs = specs.and(nomeAutorLike(nomeAutor));
        }

        Pageable pageRequest = PageRequest.of(pagina, tamanhopagina);

        return repository.findAll(specs, pageRequest);
    }

    public void atualizar(Livro livro) {
        if (livro.getId() == null){
            throw new IllegalArgumentException("O id do livro deve ser informado para atualização");
        }
        validator.validar(livro);
        repository.save(livro);
    }
}
