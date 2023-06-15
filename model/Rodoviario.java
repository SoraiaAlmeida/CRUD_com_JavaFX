package model;

import java.time.LocalDate;

public class Rodoviario {
	
	private String nome;
	private String CPF;
	private String origem;
	private String destino;
	private LocalDate dataViagem;
	private String horario;
	
	
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
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public LocalDate getData() {
		return dataViagem;
	}
	public void setData(LocalDate data) {
		this.dataViagem = data;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	@Override
	public String toString() {
		return "Rodoviaria [nome=" + nome + ", CPF=" + CPF + ", origem=" + origem + ", destino=" + destino + ", data="
				+ dataViagem + ", horario=" + horario + "]";
	}
	
	
	

}