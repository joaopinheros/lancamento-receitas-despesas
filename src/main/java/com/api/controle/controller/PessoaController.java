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

/**
 * Controlador para lidar com operações relacionadas a pessoas.
 */
@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    /**
     * Repositório para operações de banco de dados relacionadas a pessoas.
     */
    @Autowired
    private PessoaRepository pessoaRepository;

    /**
     * Serviço para lógica de negócios relacionada a pessoas.
     */
    @Autowired
    private PessoaService pessoaService;

    /**
     * Obtém uma lista de todas as pessoas.
     *
     * @return Lista de objetos do tipo PessoaModel.
     */
    @GetMapping
    public List<PessoaModel> listarPessoas() {
        return pessoaRepository.findAll();
    }

    /**
     * Cria uma nova pessoa.
     *
     * @param pessoaModel O objeto PessoaModel a ser criado.
     * @param response    HttpServletResponse usado para configurar o cabeçalho de resposta.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarPessoa(@RequestBody PessoaModel pessoaModel, HttpServletResponse response) {
        PessoaModel pessoaSalva = pessoaRepository.save(pessoaModel);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(pessoaModel.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }

    /**
     * Obtém uma pessoa com base no ID fornecido.
     *
     * @param id O ID da pessoa a ser buscado.
     * @return Resposta HTTP contendo o PessoaModel correspondente ou status de erro.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PessoaModel> buscarPessoaID(@PathVariable Long id) {
        return pessoaService.buscarPessoaID(id);
    }

    /**
     * Deleta uma pessoa com base no ID fornecido.
     *
     * @param id O ID da pessoa a ser deletada.
     * @return Resposta HTTP contendo o PessoaModel deletado ou status de erro.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<PessoaModel> deletarPessoa(@PathVariable Long id) {
        return pessoaService.deletarPessoa(id);
    }

    /**
     * Altera uma pessoa existente com base no ID fornecido.
     *
     * @param id             O ID da pessoa a ser alterada.
     * @param pessoaModel    O objeto PessoaModel com as alterações desejadas.
     * @return Resposta HTTP contendo o PessoaModel alterado ou status de erro.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PessoaModel> alterarPessoa(@PathVariable Long id, @RequestBody PessoaModel pessoaModel) {
        return pessoaService.alterarPessoa(id, pessoaModel);
    }

    /**
     * Altera o status "ativo" de uma pessoa com base no ID fornecido.
     *
     * @param id    O ID da pessoa a ter o status "ativo" alterado.
     * @param ativo O novo valor do status "ativo".
     * @return Resposta HTTP contendo o PessoaModel com o status "ativo" alterado ou status de erro.
     */
    @PutMapping("/{id}/ativo")
    public ResponseEntity<PessoaModel> alterarPessoaAtivo(@PathVariable Long id, @RequestBody(required = true) Boolean ativo) {
        return pessoaService.alterarPessoaAtivo(id, ativo);
    }
}
