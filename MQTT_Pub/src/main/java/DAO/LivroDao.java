package DAO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Control.Mosquitto_pub;
import Model.LivroM;

public class LivroDao {

    public static boolean InserirPessoa(LivroM liv) {

        String sql = "INSERT INTO livraria.livro (autor, ano, titulo, genero) values (?, ?, ?, ?)"; 

        try {
            PreparedStatement stmt = ConexaoBanco1.getConexaoMySQL().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, liv.getAutor());
            stmt.setInt(2, liv.getAno());
            stmt.setString(3, liv.getTitulo());
            stmt.setString(4, liv.getGenero());


            if(stmt.executeUpdate()>0){

                ResultSet result = stmt.getGeneratedKeys();

                if (result.next()) {

                    System.out.println("ID: " + result.getInt(1));

                    Mosquitto_pub mosquittoPub = new Mosquitto_pub(Integer.toString(result.getInt(1)), "id", 1883, "localhost");

                    if (!mosquittoPub.SendMessege()){
                        JOptionPane.showMessageDialog(null, "Erro no enviar mensagem 'LivroDAO'", "Sistema", JOptionPane.ERROR_MESSAGE);
                    }		    
                }	

                return true;
            } 
        }
        catch(SQLException ex) {
                System.out.println("Erro: " + ex.getMessage());
                return false;
        }
        return false;	
    }
}