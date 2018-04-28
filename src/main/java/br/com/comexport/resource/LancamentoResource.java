package br.com.comexport.resource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.comexport.domain.LancamentoTO;
import br.com.comexport.domain.StatsTO;
import br.com.comexport.exception.CustomException;
import br.com.comexport.model.Lancamento;
import br.com.comexport.repository.LancamentoRepository;

@RestController
public class LancamentoResource {
	public static final Logger logger = LoggerFactory.getLogger(LancamentoResource.class);

	@Autowired
	LancamentoRepository repository;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/lancamentos-contabeis")
	public ResponseEntity<?> getLancamentoPorContaContabil(@RequestParam(value ="contaContabil", required = false) Integer contaContabil) {
		List<Lancamento> lancamentos = getLancamento(contaContabil);

		if (lancamentos.isEmpty()) {
			return new ResponseEntity(new CustomException("Nenhum registros encontrado."), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Lancamento>>(lancamentos, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/lancamentos-contabeis/{id}")
	public ResponseEntity<?> getLancamento(@PathVariable UUID id) {
		Optional<Lancamento> lancamento = repository.findById(id);
		if (!lancamento.isPresent()) {
			return new ResponseEntity(new CustomException("Nenhum registros encontrado."), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Lancamento>(lancamento.get(), HttpStatus.OK);
	}
	
	@PostMapping("/lancamentos-contabeis")
	public ResponseEntity<Object> createLancamento(@Valid @RequestBody LancamentoTO to) {
		try {
			Lancamento novo = new Lancamento();
			novo.setContaContabil(to.getContaContabil());
			novo.setData(to.getData());
			novo.setValor(to.getValor());
			
			Lancamento novoSave = repository.save(novo);
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(novoSave.getId()).toUri();
	
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new CustomException("Erro na criação"), HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/lancamentos-contabeis/_stats")
	public ResponseEntity<?> getLancamentoStats(@RequestParam(value ="contaContabil", required = false) Integer contaContabil) {
		List<Lancamento> lancamentos = getLancamento(contaContabil);
		
		if (lancamentos.isEmpty()) {
			return new ResponseEntity(new CustomException("Nenhum registros encontrado."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<StatsTO>(getStatsTO(lancamentos), HttpStatus.OK);
	}

	private StatsTO getStatsTO(List<Lancamento> lancamentos) {
		StatsTO to = new StatsTO();
		to.setQtd(lancamentos.size());
		BigDecimal maior = BigDecimal.ZERO;
		BigDecimal menor = lancamentos.get(0).getValor();
		for(Lancamento lancamento : lancamentos) {
			to.setSoma(to.getSoma().add(lancamento.getValor()));
			if(lancamento.getValor().compareTo(maior) > 0) {
				maior = lancamento.getValor();
			} 
			
			if (lancamento.getValor().compareTo(menor) < 0) {
				menor = lancamento.getValor();
			}			
		}
		to.setMax(maior);
		to.setMin(menor);
		to.setMedia(to.getSoma().divide(BigDecimal.valueOf(to.getQtd()), 2, RoundingMode.HALF_UP));
		
		return to;
	}
	
	
	private List<Lancamento> getLancamento(Integer contaContabil) {
		List<Lancamento> lancamentos = new ArrayList<>();
		if (contaContabil != null ) {
			lancamentos = repository.findAllByContaContabil(contaContabil);
		} else {
			lancamentos = repository.findAll();
		}
		return lancamentos;
	}
}
