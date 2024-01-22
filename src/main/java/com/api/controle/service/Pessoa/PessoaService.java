package com.api.controle.service.Pessoa;

import com.api.controle.model.PessoaModel;
import com.api.controle.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public ResponseEntity<PessoaModel> buscarPessoaID(Long id){
        Optional<PessoaModel> pessoaOptional = pessoaRepository.findById(id);

        if (pessoaOptional.isPresent()) {
            return ResponseEntity.ok(pessoaOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    public ResponseEntity<PessoaModel> deletarPessoa(Long id) {
        Optional<PessoaModel> pessoaOptional = pessoaRepository.findById(id);

        if (pessoaOptional.isPresent()) {
            pessoaRepository.delete(pessoaOptional.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<PessoaModel> alterarPessoa(Long id, PessoaModel pessoaModel){
        Optional<PessoaModel> pessoaOptional = pessoaRepository.findById(id);

        if (pessoaOptional.isPresent()) {
            PessoaModel pessoaExistente = pessoaOptional.get();
            BeanUtils.copyProperties(pessoaModel, pessoaExistente, "id");
            pessoaRepository.save(pessoaExistente);
            return ResponseEntity.ok(pessoaExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<PessoaModel> alterarPessoaAtivo(Long id, Boolean ativo) {
        Optional<PessoaModel> pessoaOptional = pessoaRepository.findById(id);

        if (pessoaOptional.isPresent()) {
            PessoaModel pessoaExistente = pessoaOptional.get();
            pessoaExistente.setAtivo(ativo);
            pessoaRepository.save(pessoaExistente);
            return ResponseEntity.ok(pessoaExistente);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
