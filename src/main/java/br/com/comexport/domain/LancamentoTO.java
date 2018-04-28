package br.com.comexport.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.constraints.NotNull;

public class LancamentoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Conta Contábil Obrigatório")
	private Integer contaContabil;
	
	@NotNull(message="Data Obrigatório")
	private Date date;
	
	@NotNull(message="Valor Obrigatório")
	private BigDecimal valor;
	
	public Integer getContaContabil() {
		return contaContabil;
	}
	public void setContaContabil(Integer contaContabil) {
		this.contaContabil = contaContabil;
	}
	public Date getData() {
		return date;
	}
	public void setData(String data) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); 
		Date date;
		try {
			date = format.parse(data);
			this.date = date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
}
