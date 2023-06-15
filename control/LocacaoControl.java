package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Locacao;

public class LocacaoControl {
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty cpf = new SimpleStringProperty("");
	private StringProperty localRetirada = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> dataRetirada = new SimpleObjectProperty<>();
	private StringProperty localDevolucao = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> dataDevolucao = new SimpleObjectProperty<>();
	

	private ObservableList<Locacao> lista = FXCollections.observableArrayList();
	
	private Connection con;

	public LocacaoControl() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String DBURL = "jdbc:mariadb://localhost:3306/locacao";
			String DBUSER = "root";
			String DBPASS = "123456";
			con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


	public void reservar() {
		Locacao locacao = new Locacao();

		lista.add(locacao);

		String sql = "INSERT INTO locacoes (nome, cpf, local_retirada, data_retirada, local_devolucao, data_devolucao) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, nome.get());
			pst.setString(2, cpf.get());
			pst.setString(3, localRetirada.get());
			pst.setDate(4, java.sql.Date.valueOf(dataRetirada.get()));
			pst.setString(5, localDevolucao.get());
			pst.setDate(6, java.sql.Date.valueOf(dataDevolucao.get()));
			
			pst.executeUpdate();
			
			 System.out.println("Dados inseridos com sucesso!");
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alterar() {
	    String cpfReserva = cpf.get();

	    String sql = "UPDATE locacoes SET nome = ?, local_retirada = ?, data_retirada = ?, local_devolucao = ?, data_devolucao = ? WHERE cpf = ?";

	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, nome.get());
	        pst.setString(2, localRetirada.get());
	        pst.setDate(3, java.sql.Date.valueOf(dataRetirada.get()));
	        pst.setString(4, localDevolucao.get());
	        pst.setDate(5, java.sql.Date.valueOf(dataDevolucao.get()));
	        pst.setString(6, cpfReserva);

	        int rowsAffected = pst.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Reserva alterada com sucesso!");
	        } else {
	            System.out.println("Nenhuma reserva foi alterada.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	public void excluir() {
		Locacao locacao = new Locacao();

		lista.remove(locacao);

		String sql = "DELETE FROM locacoes WHERE cpf = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, cpf.get());
			pst.executeUpdate();
			System.out.println("A reserva foi excluída com sucesso.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void pesquisar() {
	    lista.clear();
	    String cpfSearch = cpf.get();
	    String sql = "SELECT * FROM locacoes WHERE cpf = ?";
	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, cpfSearch);
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            String nomeReserva = rs.getString("nome");
	            String cpfReserva = rs.getString("cpf");
	            String localRetiradaReserva = rs.getString("local_retirada");
	            LocalDate dataRetiradaReserva = rs.getDate("data_retirada").toLocalDate();
	            String localDevolucaoReserva = rs.getString("local_devolucao");
	            LocalDate dataDevolucaoReserva = rs.getDate("data_devolucao").toLocalDate();

	            Locacao locacao = new Locacao();
	            locacao.setNome(nomeReserva);
	            locacao.setCPF(cpfReserva);
	            locacao.setLocalRetirada(localRetiradaReserva);
	            locacao.setDataRetirada(dataRetiradaReserva);
	            locacao.setLocalDevolucao(localDevolucaoReserva);
	            locacao.setDataDevolucao(dataDevolucaoReserva);

	            lista.add(locacao);
	        }

	        if (!lista.isEmpty()) {
	            System.out.println("Reservas encontradas:");
	            for (Locacao locacao : lista) {
	                System.out.println("Nome: " + locacao.getNome());
	                System.out.println("CPF: " + locacao.getCPF());
	                System.out.println("Local Retirada: " + locacao.getLocalRetirada());
	                System.out.println("Data Retirada: " + locacao.getDataRetirada());
	                // Imprima outros atributos da reserva conforme necessário
	            }
	        } else {
	            System.out.println("Nenhuma reserva encontrada para o CPF informado.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}



	public StringProperty nomeProperty() {
		return nome;
	}
	
	public StringProperty cpfProperty() {
		return cpf;
	}

	public StringProperty localRetiradaProperty() {
		return localRetirada;
	}

	public ObjectProperty<LocalDate> dataRetiradaProperty() {
		return dataRetirada;
	}

	public StringProperty localDevolucaoProperty() {
		return localDevolucao;
	}

	public ObjectProperty<LocalDate> dataDevolucaoProperty() {
		return dataDevolucao;
	}

	public ObservableList<Locacao> getLista() {
		return lista;
	}
}