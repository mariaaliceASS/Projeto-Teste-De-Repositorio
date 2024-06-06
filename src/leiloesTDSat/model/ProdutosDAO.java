package leiloesTDSat.model;


import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    public static boolean cadastrarProduto(ProdutosDTO produto) {
        try {

            conectaDAO conexao = new conectaDAO();
            conexao.conectar();

            String sql = "INSERT INTO produtos ( nome, valor, status) VALUES (?, ?, ?);";

            Connection con = conexao.getConexao();
            PreparedStatement inserir = con.prepareStatement(sql);
            inserir.setString(1, produto.getNome());
            inserir.setInt(2, produto.getValor());
            inserir.setString(3, produto.getStatus());

            inserir.execute();

            conexao.desconectarLeilao();

            return true;

        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar no banco de dados!");
            return false;
        }

    }

    public static List<ProdutosDTO> listarTodos() {

        List<ProdutosDTO> listaProdutos = new ArrayList<ProdutosDTO>();

        try {

            conectaDAO conexao = new conectaDAO();
            conexao.conectar();

            String sql = "SELECT * FROM produtos;";

            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);

            ResultSet resposta = consulta.executeQuery();
            //Consulta e um SELECT!

            while (resposta.next()) {

                ProdutosDTO objProduto = new ProdutosDTO();

                objProduto.setId(resposta.getInt("id"));
                objProduto.setNome(resposta.getString("nome"));
                objProduto.setValor(resposta.getInt("valor"));
                objProduto.setStatus(resposta.getString("status"));

                listaProdutos.add(objProduto);

            }

            conexao.desconectarLeilao();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Erro ao listar os registros do banco de dados!");

        }

        return listaProdutos;
    }

}
