package com.api.controle.controller;

import com.api.controle.model.PessoaModel;
import com.api.controle.repository.PessoaRepository;
import com.api.controle.service.Pessoa.PessoaService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<PessoaModel> listarPessoas(){
        return pessoaRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarPessoa(@RequestBody PessoaModel pessoaModel, HttpServletResponse response){
        PessoaModel pessoaSalva = pessoaRepository.save(pessoaModel);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(pessoaModel.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaModel> buscarPessoaID(@PathVariable Long id) {
        return pessoaService.buscarPessoaID(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PessoaModel> deletarPessoa(@PathVariable Long id) {
        return pessoaService.deletarPessoa(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaModel> alterarPessoa(@PathVariable Long id, @RequestBody PessoaModel pessoaModel){
        return pessoaService.alterarPessoa(id, pessoaModel);
    }

    @PutMapping("/{id}/ativo")
    public ResponseEntity<PessoaModel> alterarPessoaAtivo(@PathVariable Long id, @RequestBody(required = true) Boolean ativo){
        return pessoaService.alterarPessoaAtivo(id, ativo);
    }
}
