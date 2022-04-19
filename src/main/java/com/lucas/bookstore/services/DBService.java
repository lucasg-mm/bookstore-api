package com.lucas.bookstore.services;

import com.lucas.bookstore.domain.Categoria;
import com.lucas.bookstore.domain.Livro;
import com.lucas.bookstore.repositories.CategoriaRepository;
import com.lucas.bookstore.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LivroRepository livroRepository;

    public void instanciaBaseDeDados(){
        Categoria cat1 = new Categoria(null, "Informática", "Livros de TI.");
        Categoria cat2 = new Categoria(null, "True Crime", "Livros sobre crimes reais.");
        Categoria cat3 = new Categoria(null, "Manga", "Histórias em quadrinhos japonesas.");
        Categoria cat4 = new Categoria(null, "Fantasia", "Livros sobre mundos fantásticos.");

        Livro l1 = new Livro(null, "Clean Code", "Robert Martin", "Lorem ipsum dolor", cat1);
        Livro l2 = new Livro(null, "Zodiac", "Robert Graysmith", "Lorem ipsum dolor", cat2);
        Livro l3 = new Livro(null, "Attack on Titan Vol. 3", "Hajime Isayama", "Tatakae", cat3);
        Livro l4 = new Livro(null, "Fullmetal Alchemist Vol. 1", "Hiromu Arakawa",
                "Lorem ipsum dolor", cat3);
        Livro l5 = new Livro(null, "The Hobbit", "J. R. R. Tolkien", "Lorem ipsum dolor", cat4);

        cat1.getLivros().addAll(Arrays.asList(l1));
        cat2.getLivros().addAll(Arrays.asList(l2));
        cat3.getLivros().addAll(Arrays.asList(l3, l4));
        cat4.getLivros().addAll(Arrays.asList(l5));

        this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
        this.livroRepository.saveAll((Arrays.asList(l1, l2, l3, l4, l5)));
    }
}
