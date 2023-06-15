package model;

import java.time.LocalDate;

public class Locacao {
	
	private String nome;
	private String CPF;
	private String localRetirada;
	private LocalDate dataRetirada;
	private String localDevolucao;
	private LocalDate dataDevolucao;


	public Locacao() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getLocalRetirada() {
		return localRetirada;
	}

	public void setLocalRetirada(String localRetirada) {
		this.localRetirada = localRetirada;
	}

	public LocalDate getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public String getLocalDevolucao() {
		return localDevolucao;
	}

	public void setLocalDevolucao(String localDevolucao) {
		this.localDevolucao = localDevolucao;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	@Override
	public String toString() {
		return "Locacao [nome=" + nome + ", CPF=" + CPF + ", localRetirada=" + localRetirada + ", dataRetirada="
				+ dataRetirada + ", localDevolucao=" + localDevolucao + ", dataDevolucao=" + dataDevolucao + "]";
	}

}