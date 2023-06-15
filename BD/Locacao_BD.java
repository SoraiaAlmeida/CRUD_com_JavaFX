package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class Locacao_BD {
	
	public static void inserir(Connection con) throws SQLException {
		String sql = "INSERT INTO locacoes "
		+ "(nome, CPF, local_retirada, data_retirada, local_devolucao, data_devolucao) VALUES "
		+ "('Manuela', '1234567889', 'SP', '2023-07-11', 'RJ', '2023-08-19')";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.executeUpdate();
		System.out.println("Registro inserido com sucesso");
		// Método para inserir um registro na tabela 'locacoes'
	}
	
	public static void consultar(Connection con) throws SQLException {
		String sql = "SELECT * FROM locacoes";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		
		while (rs.next()) { 
			 String nome = rs.getString("nome");
	           String cpf = rs.getString("CPF");
	           String localRetirada = rs.getString("local_retirada");
	           Date dataRetirada = rs.getDate("data_retirada");
	           String localDevolucao = rs.getString("local_devolucao");
	           Date dataDevolucao = rs.getDate("data_devolucao");
			
	           
	           System.out.printf("%s\t%s\t%s\t%s\t%s\t%s%n", nome, cpf, localRetirada, dataRetirada, localDevolucao, dataDevolucao);
		}
	}
	
	public static void main(String[] args) {
		try { 
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("Classe carregada");
			Connection con = 
					DriverManager.getConnection(
			"jdbc:mariadb://localhost:3306/Locacao", "root", "123456");
			System.out.println("Conexão estabelecida");			
			consultar(con);
		} catch(ClassNotFoundException | SQLException e) { 
			e.printStackTrace();
		}
	}
}
