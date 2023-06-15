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
import model.Rodoviario;


public class RodoviarioControl {
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty cpf = new SimpleStringProperty("");
	private StringProperty origem = new SimpleStringProperty("");
	private StringProperty destino = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> dataViagem = new SimpleObjectProperty<>();
	private StringProperty horario = new SimpleStringProperty("");
	
	
	

	private ObservableList<Rodoviario> lista = FXCollections.observableArrayList();
	
	private Connection con;

	public RodoviarioControl() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String DBURL = "jdbc:mariadb://localhost:3306/Rodoviario";
			String DBUSER = "root";
			String DBPASS = "123456";
			con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void reservar() {
		Rodoviario rodoviario = new Rodoviario();

		lista.add(rodoviario);

		String sql = "INSERT INTO reservas_rod (Nome, CPF, Origem, Destino, Data_Viagem, Horario) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, nome.get());
			pst.setString(2, cpf.get());
			pst.setString(3, origem.get());
			pst.setString(4, destino.get());
			pst.setDate(5, java.sql.Date.valueOf(dataViagem.get()));
			pst.setString(6, horario.get());
			
		
			
			pst.executeUpdate();
			
			 System.out.println("Dados inseridos com sucesso!");
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alterar() {
	    String cpfReserva = cpf.get();

	    String sql = "UPDATE reservas_rod SET nome = ?, origem = ?, destino = ?, data_Viagem = ?, horario = ? WHERE cpf = ?";

	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, nome.get());
	        pst.setString(2, origem.get());
	        pst.setString(3, destino.get());
	        pst.setDate(4, java.sql.Date.valueOf(dataViagem.get()));
	        pst.setString(5, horario.get());
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
		Rodoviario rodoviario = new Rodoviario();

		lista.remove(rodoviario);

		String sql = "DELETE FROM reservas_rod WHERE cpf = ?";
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
	    String sql = "SELECT * FROM reservas_rod WHERE cpf = ?";
	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, cpfSearch);
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            String nomeReserva = rs.getString("Nome");
	            String cpfReserva = rs.getString("CPF");
	            String origemReserva = rs.getString("Origem");
	            String destinoReserva = rs.getString("Destino");
	            LocalDate dataViagemReserva = rs.getDate("Data_Viagem").toLocalDate();
	            String HorarioReserva = rs.getString("Horario");
	            

	            Rodoviario rodoviario = new Rodoviario();
	            rodoviario.setNome(nomeReserva);
	            rodoviario.setCPF(cpfReserva);
	            rodoviario.setOrigem(origemReserva);
	            rodoviario.setDestino(destinoReserva);
	            rodoviario.setData(dataViagemReserva);
	            rodoviario.setHorario(HorarioReserva);

	            lista.add(rodoviario);
	        }

	        if (!lista.isEmpty()) {
	            System.out.println("Reservas encontradas:");
	            for (Rodoviario rodoviario : lista) {
	                System.out.println("Nome: " + rodoviario.getNome());
	                System.out.println("CPF: " + rodoviario.getCPF());
	                System.out.println("Origem: " + rodoviario.getOrigem());
	                System.out.println("Destino: " + rodoviario.getDestino());
	                System.out.println("Data: " + rodoviario.getData());
	               
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

	public StringProperty origemProperty() {
		return origem;
	}
	
	public StringProperty destinoProperty() {
		return destino;
	}
	public ObjectProperty<LocalDate> dataViagemProperty() {
		return dataViagem;
	}

	public StringProperty horarioProperty() {
		return horario;
	}


	public ObservableList<Rodoviario> getLista() {
		return lista;
	}
}