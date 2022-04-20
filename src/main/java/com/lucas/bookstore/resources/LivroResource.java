package com.lucas.bookstore.resources;

import com.lucas.bookstore.domain.Livro;
import com.lucas.bookstore.dtos.LivroDTO;
import com.lucas.bookstore.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {

    @Autowired
    private LivroService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Integer id){
        Livro obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAll(@RequestParam(value = "categoria", defaultValue="0") Integer id_cat){
        List<Livro> list = service.findAll(id_cat);
        List<LivroDTO> listDTO = list.stream().map((obj) -> new LivroDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok(listDTO);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Livro> update(@PathVariable Integer id, @RequestBody Livro obj){
        Livro newObj = service.update(id, obj);
        return ResponseEntity.ok(newObj);
    }

    @PatchMapping(value="/{id}")
    public ResponseEntity<Livro> updatePatch(@PathVariable Integer id, @RequestBody Livro obj){
        Livro newObj = service.update(id, obj);
        return ResponseEntity.ok(newObj);
    }
}
