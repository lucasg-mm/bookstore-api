package com.lucas.bookstore.resources;

import com.lucas.bookstore.domain.Livro;
import com.lucas.bookstore.dtos.LivroDTO;
import com.lucas.bookstore.services.LivroService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
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
    public ResponseEntity<Livro> update(@PathVariable Integer id, @Valid @RequestBody Livro obj){
        Livro newObj = service.update(id, obj);
        return ResponseEntity.ok(newObj);
    }

    @PatchMapping(value="/{id}")
    public ResponseEntity<Livro> updatePatch(@PathVariable Integer id, @Valid @RequestBody Livro obj){
        Livro newObj = service.update(id, obj);
        return ResponseEntity.ok(newObj);
    }

    @PostMapping
    public ResponseEntity<Livro> create(@RequestParam(value = "categoria", defaultValue = "0") Integer idCat,
                                        @Valid @RequestBody Livro obj){
        Livro newObj = service.create(idCat, obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}")
                .buildAndExpand(newObj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
