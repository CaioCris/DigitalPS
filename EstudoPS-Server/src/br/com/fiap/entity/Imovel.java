package br.com.fiap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="imovel", sequenceName="SQ_T_IMOVEL", allocationSize=1)
public class Imovel {
	
	@Id
	@GeneratedValue(generator="imovel", strategy=GenerationType.SEQUENCE)
	private long codigo;
	
	@Column(name="end_imovel")
	private String endereco;
	
	@Column(name="vl_imovel")
	private double preco;
	
	@Column(name="tp_residencial")
	private boolean residencial;
		
	public Imovel(String endereco, double preco, boolean residencial) {
		super();
		this.endereco = endereco;
		this.preco = preco;
		this.residencial = residencial;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public boolean isResidencial() {
		return residencial;
	}

	public void setResidencial(boolean residencial) {
		this.residencial = residencial;
	}
}
