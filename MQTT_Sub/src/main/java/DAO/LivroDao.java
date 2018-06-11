package DAO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DAO.ConexaoBanco1;
import Model.LivroM;

@SuppressWarnings("unused")
public class LivroDao {

@SuppressWarnings("static-access")
public boolean InserirLivro(LivroM liv) {
    
    String SQL = "INSERT INTO livraria2.livro values (?, ?, ?, ?, ?)"; 
    
    try {
            PreparedStatement pst = ConexaoBanco2.getConexaoMySQL().prepareStatement(SQL);

            pst.setInt(1, liv.getId());
            pst.setString(2, liv.getAutor());
            pst.setInt(3, liv.getAno());
            pst.setString(4, liv.getTitulo());
            pst.setString(5, liv.getGenero());

            pst.executeUpdate();
    } catch(SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
            return false;
    }
        return false;	
}

    @SuppressWarnings("static-access")
    
    public void busca(LivroM livro){
        String SQL = "SELECT * FROM livraria.livro WHERE id = ?";

        try {
            PreparedStatement stmt = ConexaoBanco1.getConexaoMySQL().prepareStatement(SQL);
            stmt.setInt(1, livro.getId());
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                    LivroM liv = new LivroM();

                    liv.setId(result.getInt("id"));
                    liv.setAutor(result.getString("autor"));
                    liv.setAno(result.getInt("ano"));
                    liv.setTitulo(result.getString("titulo"));
                    liv.setGenero(result.getString("genero"));

                    InserirLivro(liv);

            }else {
                    System.out.println("Nenhum registro encontrado!");
            }

        } catch(SQLException ex) {
                System.out.println("Erro: " + ex.getMessage());
        }

    }
}