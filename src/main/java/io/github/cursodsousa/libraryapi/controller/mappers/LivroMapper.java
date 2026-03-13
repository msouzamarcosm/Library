package io.github.cursodsousa.libraryapi.controller.mappers;

import io.github.cursodsousa.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.cursodsousa.libraryapi.controller.dto.ResultadoPequisaLivroDTO;
import io.github.cursodsousa.libraryapi.model.Livro;
import io.github.cursodsousa.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AutorMapper.class)
public abstract class LivroMapper {


    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null) )")

    public abstract Livro toEntity(CadastroLivroDTO dto);

    @Mapping(target = "genero", source = "genero")
    public abstract ResultadoPequisaLivroDTO toDTO(Livro livro);
}
