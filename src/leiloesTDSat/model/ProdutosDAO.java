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

    public static void venderProduto(int id) throws SQLException {
        try {
            conectaDAO conn = new conectaDAO();
            conn.conectar();

            String sql = "UPDATE produtos SET status = ? WHERE id = ?;";
            PreparedStatement consulta = conn.getConexao().prepareStatement(sql);

            consulta.setString(1, "Vendido");
            consulta.setInt(2, id);

            int linhas = consulta.executeUpdate();

            if (linhas == 0) {
                JOptionPane.showMessageDialog(null, "Não a produto com esse id");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static List<ProdutosDTO> listarProdutosVendidos() {

        List<ProdutosDTO> lista = new ArrayList<ProdutosDTO>();
        try {

            conectaDAO conexao = new conectaDAO();
            conexao.conectar();

            String sql = "SELECT * FROM produtos WHERE status = ?;";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);
            consulta.setString(1, "Vendido");
            
            ResultSet resposta = consulta.executeQuery();

            while (resposta.next()) {

                ProdutosDTO objProduto = new ProdutosDTO();

                objProduto.setId(resposta.getInt("id"));
                objProduto.setNome(resposta.getString("nome"));
                objProduto.setValor(resposta.getInt("valor"));
                objProduto.setStatus(resposta.getString("status"));

                lista.add(objProduto);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
        public static ProdutosDTO buscarId(int id) {
         ProdutosDTO objProduto = new ProdutosDTO();
        
        try {
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();

            String sql = "SELECT * FROM produtos WHERE id=?";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);
            consulta.setInt(1, id);

            ResultSet resposta = consulta.executeQuery();

            if (resposta.next()) {

                objProduto.setId(resposta.getInt("id"));
                objProduto.setNome(resposta.getString("nome"));
                objProduto.setValor(resposta.getInt("valor"));
                objProduto.setStatus(resposta.getString("status"));
            }
            
            conexao.desconectarLeilao();

        } catch (SQLException ex) {
            System.out.println("Erro ao buscar o " + id + " no banco de dados!");
        }
        
        return objProduto;
    }

}
