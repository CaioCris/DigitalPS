package br.com.fiap.to;

public class Imovel {
	
	private long codigo;
	private String endereco;
	private double preco;
	private boolean residencial;
	
	public Imovel() {
		super();
	}

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
