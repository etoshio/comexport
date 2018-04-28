package br.com.comexport.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Model Lancamento
 */
@Entity
@Table(name = "lancamento", catalog = "comexport")
public class Lancamento implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
	private UUID id;
	
	@Column(name = "conta_contabil", nullable = false)
	private Integer contaContabil;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false, length = 10)
	private Date data;
	
	@Column(name = "valor", nullable = false, precision = 15, scale = 2)
	private BigDecimal valor;

	public Lancamento() {
	}
	
	public void setId(UUID id) {
		this.id = id;
	}

    public UUID getId() {
        return id;
    }

	
	public Integer getContaContabil() {
		return this.contaContabil;
	}

	public void setContaContabil(Integer  contaContabil) {
		this.contaContabil = contaContabil;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
