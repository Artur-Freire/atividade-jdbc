package com.freire.dao;
import com.freire.model.Aluno;
import java.util.List;

public interface AlunoDAO {
    void salvar(Aluno aluno);
    Aluno buscarPorMatricula(String matricula);
    List<Aluno> listarTodos();
    void atualizar(Aluno aluno);
    void excluir(String matricula);
}
