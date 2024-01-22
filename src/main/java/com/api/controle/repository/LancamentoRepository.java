package com.api.controle.repository;

import com.api.controle.model.LancamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<LancamentoModel, Long> {
}
