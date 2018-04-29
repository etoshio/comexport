package br.com.comexport.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.comexport.model.Lancamento;

/**
 * Interface LancamentoRepository
 * @author elcio
 *
 */
@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, UUID> {

	List<Lancamento> findAllByContaContabil(Integer contaContabil);

}
