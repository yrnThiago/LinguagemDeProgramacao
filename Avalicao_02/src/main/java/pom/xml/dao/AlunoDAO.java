package pom.xml.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pom.xml.factory.ConnectionFactory;
import pom.xml.modelo.Aluno;

public class AlunoDAO {
    private Connection connection;

    public AlunoDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public Aluno pegaAlunoPorID (int id) {
        String sql = "SELECT * FROM alunos WHERE aluno_id = " + id;
        Aluno aluno = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                int alunoId = resultSet.getInt("aluno_id");
                String alunoCPF = resultSet.getString("aluno_cpf");
                String alunoNome = resultSet.getString("aluno_nome");
                String alunoDataNasc = resultSet.getString("aluno_dataNasc");
                double alunoPeso = resultSet.getDouble("aluno_peso");
                double alunoAltura = resultSet.getDouble("aluno_altura");

                aluno = new Aluno(alunoId, alunoCPF, alunoNome, alunoDataNasc, alunoPeso, alunoAltura);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return aluno;
    }

    public List pegaAlunos () {
        String sql = "SELECT * FROM alunos";
        List<Aluno> alunos = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            ResultSetMetaData metadata = resultSet.getMetaData();
            while (resultSet.next()) {
                int alunoId = resultSet.getInt("aluno_id");
                String alunoCPF = resultSet.getString("aluno_cpf");
                String alunoNome = resultSet.getString("aluno_nome");
                String alunoDataNasc = resultSet.getString("aluno_dataNasc");
                double alunoPeso = resultSet.getDouble("aluno_peso");
                double alunoAltura = resultSet.getDouble("aluno_altura");

                Aluno aluno = new Aluno(alunoId, alunoCPF, alunoNome, alunoDataNasc, alunoPeso, alunoAltura);
                alunos.add(aluno);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alunos;
    }

    public Aluno adiciona(Aluno aluno) {
        String sql = "INSERT INTO alunos (aluno_cpf, aluno_nome, aluno_dataNasc, aluno_peso, aluno_altura) VALUES (?, ?, ?, ?, ?)";
        Aluno alunoAdicionado = null;
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, aluno.getCPF());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getDataNasc());
            stmt.setDouble(4, aluno.getPeso());
            stmt.setDouble(5, aluno.getAltura());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int alunoId = generatedKeys.getInt(1);
                    String alunoCPF = aluno.getCPF();
                    String alunoNome = aluno.getNome();
                    String alunoDataNasc = aluno.getDataNasc();
                    double alunoPeso = aluno.getPeso();
                    double alunoAltura = aluno.getAltura();
                    
                    alunoAdicionado = new Aluno(alunoId, alunoCPF, alunoNome, alunoDataNasc, alunoPeso, alunoAltura);
                }
            }
            
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return alunoAdicionado;
    }

    public void atualizaAlunoPorID(Aluno alunoAtualizado, int id) {
        String sql = "UPDATE alunos SET aluno_cpf = ?, aluno_nome = ?, aluno_dataNasc = ?, aluno_peso = ?, aluno_altura = ? WHERE aluno_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
    
            stmt.setString(1, alunoAtualizado.getCPF());
            stmt.setString(2, alunoAtualizado.getNome());
            stmt.setString(3, alunoAtualizado.getDataNasc());
            stmt.setDouble(4, alunoAtualizado.getPeso());
            stmt.setDouble(5, alunoAtualizado.getAltura());
            stmt.setInt(6, id);
    
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    

    public boolean excluiAlunoPorID(int id) {
        String sql = "DELETE FROM alunos WHERE aluno_id = " + id;
        boolean alunoFoiExcluido = false;
        try {
            Statement stmt = connection.createStatement();
            int rowsAffected = stmt.executeUpdate(sql);
            stmt.close();
    
            if (rowsAffected > 0) {
                alunoFoiExcluido = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return alunoFoiExcluido;
    }
}

