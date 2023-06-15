package BD;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;



public class Rodoviario_BD {
	
	public static void inserir(Connection con) throws SQLException {
		String sql = "INSERT INTO reservas_rod "
		+ "(nome, CPF, origem, destino, data_Viagem, horario) VALUES "
		+ "('Sheila', '1234567889', 'SP', 'RJ', '2023-08-19', '06:00')";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.executeUpdate();
		System.out.println("Registro inserido com sucesso");
	}
	
	public static void consultar(Connection con) throws SQLException {
		String sql = "SELECT * FROM reservas_rod";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		
		while (rs.next()) { 
			 String nome = rs.getString("nome");
	           String cpf = rs.getString("CPF");
	           String origem = rs.getString("Origem");
	           String destino = rs.getString("Destino");
	           Date dataViagem = rs.getDate("Data_Viagem");
	           String horario = rs.getString("Horario");
	          			
	           System.out.printf("%s\t%s\t%s\t%s\t%s\t%s%n", nome, cpf, origem, destino, dataViagem, horario);
		}
	}
	
	public static void main(String[] args) {
		try { 
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("Classe carregada");
			Connection con = 
					DriverManager.getConnection(
			"jdbc:mariadb://localhost:3306/Rodoviario", "root", "123456");
			System.out.println("Conexão estabelecida");			
			consultar(con);
		} catch(ClassNotFoundException | SQLException e) { 
			e.printStackTrace();
		}
	}
}

//	private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
//	private static final String JDBC_URL =
//			"jdbc:mariadb://localhost:3306/produtos";
//	private static final String JDBC_USER = "root";
//	private static final String JDBC_PASS = "123456";
//	
//	public static void main(String[] args) {
//		System.out.println("Teste BD");
//		try {
//		    Class.forName(JDBC_DRIVER);
//		    System.out.println("Driver importado com sucesso!");
//		    Connection con = 
//		    		DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
//		    System.out.println("Conectado no BD");
//		    
//	} catch (ClassNotFoundException e) {
//		    System.out.println("Driver não encontrado!");
//		    e.printStackTrace();
//		   	   } catch(SQLException e) {
//		   		   System.out.println("Falha na conexão com o BD!");
//		   		   e.printStackTrace();
//		   	   }
//	}
//
//}
