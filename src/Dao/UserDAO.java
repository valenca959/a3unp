package Dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.UserModel;
import javax.swing.JOptionPane;

public class UserDAO {

    public List<UserModel> UserListAll() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<UserModel> users = new ArrayList<>();

        try {
            // Selecionando todos os campos da tabela Pessoa e Aluno e desconsiderando o usuário com id 1 que o Admin
            stmt = con.prepareStatement(
                "SELECT * FROM Pessoa LEFT JOIN Aluno ON Pessoa.id_pessoa = Aluno.id_aluno " +
                "WHERE Pessoa.id_pessoa != 1"
            );
            rs = stmt.executeQuery();

            while (rs.next()) {

                UserModel user = new UserModel();
                user.setid_pessoa(rs.getInt("id_pessoa"));
                user.setNome(rs.getString("nome"));
                user.setCpf(rs.getString("cpf"));
                user.setData_nascimento(rs.getString("data_nascimento"));
                user.setMensalidade(rs.getFloat("mensalidade"));
                users.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return users;
    }    

    //Método para buscar um usuário pelo nome
    public List<UserModel> UserListForName(String name) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        List<UserModel> users = new ArrayList<>();
    
        try {
            // Use LEFT JOIN para incluir alunos sem mensalidade (mensalidade será NULL)
            stmt = con.prepareStatement(
                "SELECT p.*, a.mensalidade " +
                "FROM Pessoa p " +
                "LEFT JOIN Aluno a ON p.id_pessoa = a.id_aluno " +
                "WHERE p.nome LIKE ?"
            );
            stmt.setString(1, "%" + name + "%");
    
            rs = stmt.executeQuery();
    
            while (rs.next()) {
                UserModel user = new UserModel();
                user.setid_pessoa(rs.getInt("id_pessoa"));
                user.setNome(rs.getString("nome"));
                user.setCpf(rs.getString("cpf"));
                user.setData_nascimento(rs.getString("data_nascimento"));
    
                // Verifica se a mensalidade é NULL (aluno sem mensalidade)
                if (rs.getObject("mensalidade") != null) {
                    user.setMensalidade(rs.getFloat("mensalidade")); 
                } else {
                    // Defina um valor padrão ou mensagem para alunos sem mensalidade
                    user.setMensalidade(0.0f); // Exemplo: 0 para indicar sem mensalidade
                }
    
                users.add(user);
            }
    
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return users;
    }

    public void UserCadastro(UserModel p) {
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO Pessoa (cpf, data_nascimento, nome) VALUES (?, ?, ?)");
            stmt.setString(1, p.getCpf());
            if (p.getData_nascimento() == null || p.getData_nascimento().isEmpty()) {
                stmt.setNull(2, java.sql.Types.DATE);
            } else {
                stmt.setString(2, p.getData_nascimento());
            }
            stmt.setString(3, p.getNome());
            stmt.executeUpdate();

            stmt = con.prepareStatement("INSERT INTO Aluno (email, mensalidade, id_aluno) VALUES (?, ?, (SELECT id_pessoa FROM Pessoa WHERE cpf = ?))");
            stmt.setString(1, p.getEmail());
            stmt.setFloat(2, p.getMensalidade());
            stmt.setString(3, p.getCpf());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public UserModel GetUserForId(int userId) {
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        UserModel user = null;
    
        try {
            stmt = con.prepareStatement("SELECT * FROM Pessoa LEFT JOIN Aluno ON Pessoa.id_pessoa = Aluno.id_aluno WHERE Pessoa.id_pessoa = ?");
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
    
            if (rs.next()) {
                user = new UserModel();
               
                user.setNome(rs.getString("nome"));
                user.setCpf(rs.getString("cpf"));
                user.setData_nascimento(rs.getString("data_nascimento"));
                user.setEmail(rs.getString("email"));
                user.setMensalidade(rs.getFloat("mensalidade"));

            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar usuário: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    
        return user;
    }

    public void UserUpdate(UserModel p) {
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE Pessoa SET cpf = ?, data_nascimento = ?, nome = ? WHERE id_pessoa = ?");
            stmt.setString(1, p.getCpf());
            stmt.setString(2, p.getData_nascimento());
            stmt.setString(3, p.getNome());
            stmt.setInt(4, p.getid_pessoa());

            stmt.executeUpdate();

            stmt = con.prepareStatement("UPDATE Aluno SET email = ?, mensalidade = ? WHERE id_aluno = ?");
            stmt.setString(1, p.getEmail());
            stmt.setFloat(2, p.getMensalidade());
            stmt.setInt(3, p.getid_pessoa());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }       
    }

    public void UserDelete(int userId) {
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            // Removendo linhas na tabela Aluno
            stmt = con.prepareStatement("DELETE FROM Aluno WHERE id_aluno = ?");
            stmt.setInt(1, userId);
            stmt.executeUpdate();

            // Removendo linhas na tabela Pessoa
            stmt = con.prepareStatement("DELETE FROM Pessoa WHERE id_pessoa = ?");
            stmt.setInt(1, userId);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }       
    }
}
