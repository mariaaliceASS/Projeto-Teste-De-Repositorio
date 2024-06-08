package leiloesTDSat.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {

    public String url = "jdbc:mysql://localhost/uc11"; //Nome da base de dados
    public String user = "root"; //nome do usuário do MySQL
    public String password = "Ma91358203"; //senha do MySQL

    private Connection conexao;

    public Connection getConexao() {
        return conexao;
    }

    public void conectar() throws SQLException {
        try {
            Class<?> forName = Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, user, password);
            System.out.println("SUCESSO NA CONEXÃO!");
        } catch (ClassNotFoundException e) {
            System.out.println("Falha ao carregar a classe de conexão. Classe não encontrada!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
    }

    public void desconectarLeilao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("BANCO DE DADOS DESCONECTADO!");

            }
        } catch (SQLException e) {
            System.out.println("ERRO AO DESCONECTAR");

        }
    }

}
