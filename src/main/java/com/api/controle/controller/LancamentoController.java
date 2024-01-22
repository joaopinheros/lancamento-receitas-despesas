package com.api.controle.controller;

import com.api.controle.model.LancamentoModel;
import com.api.controle.repository.LancamentoRepository;
import com.api.controle.service.Pessoa.LancamentoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
/**
 * Controlador para lidar com operações relacionadas a lançamentos.
 */
@RestController
@RequestMapping("/lancamento")
public class LancamentoController {

    /**
     * Repositório para operações de banco de dados relacionadas a lançamentos.
     */
    @Autowired
    private LancamentoRepository lancamentoRepository;

    /**
     * Serviço para lógica de negócios relacionada a lançamentos.
     */
    @Autowired
    private LancamentoService lancamentoService;

    /**
     * Obtém uma lista de todos os lançamentos.
     *
     * @return Lista de objetos do tipo LancamentoModel.
     */
    @GetMapping
    public List<LancamentoModel> listarLancamento() {
        return lancamentoRepository.findAll();
    }

    /**
     * Obtém um lançamento com base no ID fornecido.
     *
     * @param id O ID do lançamento a ser buscado.
     * @return Resposta HTTP contendo o LancamentoModel correspondente ou status de erro.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LancamentoModel> buscarLancamentoPorID(@PathVariable Long id) {
        return lancamentoService.buscarPorID(id);
    }

    /**
     * Cria um novo lançamento.
     *
     * @param lancamentoModel O objeto LancamentoModel a ser criado.
     * @param response        HttpServletResponse usado para configurar o cabeçalho de resposta.
     * @return Resposta HTTP contendo o LancamentoModel criado e a URI do recurso criado.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LancamentoModel> criarLancamento(@RequestBody LancamentoModel lancamentoModel,
                                                           HttpServletResponse response) {
        LancamentoModel lancamentoSalvo = lancamentoRepository.save(lancamentoModel);
        lancamentoSalvo.setDatalancamento(LocalDate.now(ZoneId.of("UTC")));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(lancamentoModel.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(lancamentoSalvo);
    }

    /**
     * Altera um lançamento existente com base no ID fornecido.
     *
     * @param id             O ID do lançamento a ser alterado.
     * @param lancamentoModel O objeto LancamentoModel com as alterações desejadas.
     * @return Resposta HTTP contendo o LancamentoModel alterado ou status de erro.
     */
    @PutMapping("/{id}")
    public ResponseEntity<LancamentoModel> alterarLancamento(@PathVariable Long id,
                                                             @RequestBody LancamentoModel lancamentoModel) {
        return lancamentoService.alterarLancamento(id, lancamentoModel);
    }

    /**
     * Deleta um lançamento com base no ID fornecido.
     *
     * @param id             O ID do lançamento a ser deletado.
     * @param lancamentoModel O objeto LancamentoModel a ser usado durante a operação de exclusão.
     * @return Resposta HTTP contendo o LancamentoModel deletado ou status de erro.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<LancamentoModel> deletarLancamento(@PathVariable Long id,
                                                             LancamentoModel lancamentoModel) {
        return lancamentoService.deletarLancamento(id, lancamentoModel);
    }

}
