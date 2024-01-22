package com.api.controle.service.Pessoa;

import com.api.controle.model.LancamentoModel;
import com.api.controle.repository.LancamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public ResponseEntity<LancamentoModel> buscarPorID(Long id){
        Optional<LancamentoModel> lancamentoOptional = lancamentoRepository.findById(id);

        if(lancamentoOptional.isPresent()){
            return ResponseEntity.ok(lancamentoOptional.get());
        } else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    public ResponseEntity<LancamentoModel> deletarLancamento(Long id, LancamentoModel lancamentoModel){
        Optional<LancamentoModel> lancamentoOptional = lancamentoRepository.findById(id);

        if(lancamentoOptional.isPresent()){
            lancamentoRepository.delete(lancamentoOptional.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    public ResponseEntity<LancamentoModel> alterarLancamento(Long id, LancamentoModel lancamentoModel){
        Optional<LancamentoModel> lancamentoOptional = lancamentoRepository.findById(id);

        if(lancamentoOptional.isPresent()){
            LancamentoModel lancamentoExistente = lancamentoOptional.get();
            BeanUtils.copyProperties(lancamentoModel, lancamentoExistente, "id");
            lancamentoModel.setDatalancamento(LocalDate.now(ZoneId.of("UTC")));
            lancamentoRepository.save(lancamentoExistente);
            return ResponseEntity.ok(lancamentoExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
