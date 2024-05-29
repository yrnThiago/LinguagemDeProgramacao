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
import pom.xml.modelo.HistoricoPeso;

public class HistoricoPesoDAO {
    private Connection connection;

    public HistoricoPesoDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public List pegaHistoricoPorAlunoID (int id) {
        String sql = "SELECT * FROM historicopeso WHERE aluno_id = " + id;
        List<HistoricoPeso> historicos = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                int historicoPesoId = resultSet.getInt("ID");
                int alunoId = resultSet.getInt("aluno_id");
                double alunoPeso = resultSet.getDouble("aluno_peso");
                String dataPeso = resultSet.getString("peso_data");

                HistoricoPeso historico = new HistoricoPeso(historicoPesoId, alunoId, dataPeso, alunoPeso);
                historicos.add(historico);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return historicos;
    }

    public List pegaHistoricos () {
        String sql = "SELECT * FROM historicopeso";
        List<HistoricoPeso> historicos = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            ResultSetMetaData metadata = resultSet.getMetaData();
            while (resultSet.next()) {
                int historicoPesoId = resultSet.getInt("ID");
                int alunoId = resultSet.getInt("aluno_id");
                double alunoPeso = resultSet.getDouble("aluno_peso");
                String dataPeso = resultSet.getString("peso_data");

                HistoricoPeso historico = new HistoricoPeso(historicoPesoId, alunoId, dataPeso, alunoPeso);

                historicos.add(historico);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historicos;
    }

    public void adiciona (HistoricoPeso historico) {
        String sql = "INSERT INTO historicopeso(aluno_id, peso_data, aluno_peso) VALUES(?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, historico.getAlunoID());
            stmt.setString(2, historico.getDataPeso());
            stmt.setDouble(3, historico.getPeso());
            stmt.execute();
            stmt.close();
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizaHistoricoPorID(HistoricoPeso historicoAtualizado, int id) {
        String sql = "UPDATE historicopeso SET aluno_peso = ?, peso_data = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
    
            stmt.setDouble(1, historicoAtualizado.getPeso());
            stmt.setString(2, historicoAtualizado.getDataPeso());
            stmt.setInt(3, id);
    
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    

    public boolean excluiHistoricoPorID(int id) {
        String sql = "DELETE FROM historicopeso WHERE aluno_id = " + id;
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
