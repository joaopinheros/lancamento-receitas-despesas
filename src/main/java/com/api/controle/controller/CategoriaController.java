package com.api.controle.controller;

import com.api.controle.model.CategoriaModel;
import com.api.controle.repository.CategoriaRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Controlador para manipulação de recursos relacionados a categorias.
 */
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Retorna uma lista de todas as categorias.
     *
     * @return Lista de objetos CategoriaModel.
     */
    @GetMapping
    public List<CategoriaModel> listarCategoria() {
        return categoriaRepository.findAll();
    }

    /**
     * Cria uma nova categoria.
     *
     * @param categoriaModel Objeto CategoriaModel a ser criado.
     * @param response       Resposta HTTP que será modificada com o cabeçalho de localização.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarCategoria(@RequestBody CategoriaModel categoriaModel, HttpServletResponse response) {
        CategoriaModel categoriaSalva = categoriaRepository.save(categoriaModel);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(categoriaModel.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }

    /**
     * Busca uma categoria pelo ID e retorna uma resposta apropriada.
     *
     * @param id ID da categoria a ser buscada.
     * @return ResponseEntity contendo a categoria encontrada ou um status 404 se não encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaModel> buscarPorId(@PathVariable Long id) {
        Optional<CategoriaModel> categoriaOptional = categoriaRepository.findById(id);

        if (categoriaOptional.isPresent()) {
            return ResponseEntity.ok(categoriaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
