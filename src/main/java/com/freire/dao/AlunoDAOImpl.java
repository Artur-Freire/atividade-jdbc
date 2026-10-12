package com.freire.dao;
import com.freire.Config.ConnectionFactory;
import com.freire.model.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAOImpl implements AlunoDAO {

    @Override
    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO aluno (matricula, nome, idade, curso) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, aluno.getMatricula());
            statement.setString(2, aluno.getNome());
            statement.setInt(3, aluno.getIdade());
            statement.setString(4, aluno.getCurso());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Erro ao salvar aluno: " + e.getMessage(), e);
        }
    }

    @Override
    public Aluno buscarPorMatricula(String matricula) {
        String sql = "SELECT matricula, nome, idade, curso FROM aluno WHERE matricula = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, matricula);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Aluno(
                            rs.getString("matricula"),
                            rs.getString("nome"),
                            rs.getInt("idade"),
                            rs.getString("curso")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar aluno: " + e.getMessage(), e);
        }
        return null; // não encontrou
    }

    @Override
    public List<Aluno> listarTodos() {
        String sql = "SELECT matricula, nome, idade, curso FROM aluno";
        List<Aluno> alunos = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                alunos.add(new Aluno(
                        rs.getString("matricula"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("curso")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alunos: " + e.getMessage(), e);
        }
        return alunos;
    }

    @Override
    public void atualizar(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, idade = ?, curso = ? WHERE matricula = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, aluno.getNome());
            statement.setInt(2, aluno.getIdade());
            statement.setString(3, aluno.getCurso());
            statement.setString(4, aluno.getMatricula());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar aluno: " + e.getMessage(), e);
        }
    }

    @Override
    public void excluir(String matricula) {
        String sql = "DELETE FROM aluno WHERE matricula = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, matricula);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir aluno: " + e.getMessage(), e);
        }
    }
}

