package br.com.comexport.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LancamentoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Conta Contábil Obrigatório")
	private Integer contaContabil;
	
	@NotNull(message="Data Obrigatório")
	@JsonFormat(pattern="yyyyMMdd")
	private Date data;
	
	@NotNull(message="Valor Obrigatório")
	private BigDecimal valor;
	
	public Integer getContaContabil() {
		return contaContabil;
	}
	public void setContaContabil(Integer contaContabil) {
		this.contaContabil = contaContabil;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
}
