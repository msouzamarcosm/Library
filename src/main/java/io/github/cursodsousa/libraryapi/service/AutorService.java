package io.github.cursodsousa.libraryapi.service;

import com.fasterxml.jackson.annotation.OptBoolean;
import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository){
        this.repository = repository;
    }

    public Autor salvar(Autor autor){
        return repository.save(autor);
    }

    public void atualizar (Autor autor){
        if(autor.getId() ==  null){
            throw new IllegalArgumentException("Para atualizar, é necessário que o autor esteja salvo");
        }
        repository.save(autor);
    }

    public Optional<Autor> obterPorid(UUID id){
        return repository.findById(id);
    }

    public void deletar(Autor autor){

    repository.delete(autor);
    }

    public List<Autor> pesquisa(String nome, String nacionalidade) {
        if (nome != null && nacionalidade != null) {
            return repository.findByNomeAndNacionalidade(nome, nacionalidade);
        }
        if (nome != null) {
            return repository.findByNome(nome);
        }

        if (nacionalidade != null) {
            return repository.findByNacionalidade(nacionalidade);
        }
        return repository.findAll();
    }

}
