package br.com.comexport.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class LancamentoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Preencha o campo conta contábil")
	private Integer contaContabil;
	
	@NotNull(message="Preencha o campo data")
	@DateTimeFormat(pattern="yyyyMMdd")
	private Date date;
	
	@NotNull(message="Preencha o campo valor")
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
