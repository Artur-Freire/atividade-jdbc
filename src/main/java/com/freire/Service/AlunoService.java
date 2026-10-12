package com.freire.Service;

import com.freire.dao.AlunoDAO;
import com.freire.model.Aluno;

import java.util.List;

public class AlunoService {

    private final AlunoDAO dao;

    public AlunoService(AlunoDAO dao) {
        this.dao = dao;
    }

    public void cadastrar(Aluno aluno) {
        // Regra 1: nome obrigatório
        if (aluno.getNome() == null || aluno.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }

        // Regra 2: matrícula com exatamente 9 dígitos numéricos
        if (aluno.getMatricula() == null || !aluno.getMatricula().matches("\\d{9}")) {
            throw new IllegalArgumentException("A matrícula deve ter exatamente 9 dígitos.");
        }

        // Regra 3: idade válida
        if (aluno.getIdade() <= 0) {
            throw new IllegalArgumentException("A idade deve ser maior que zero.");
        }

        // Regra 4: não permitir matrícula repetida
        if (dao.buscarPorMatricula(aluno.getMatricula()) != null) {
            throw new IllegalArgumentException("Já existe um aluno com essa matrícula.");
        }

        dao.salvar(aluno);
    }

    public Aluno buscar(String matricula) {
        return dao.buscarPorMatricula(matricula);
    }

    public List<Aluno> listar() {
        return dao.listarTodos();
    }

    public void atualizar(Aluno aluno) {
        if (dao.buscarPorMatricula(aluno.getMatricula()) == null) {
            throw new IllegalArgumentException("Aluno não encontrado para atualizar.");
        }
        dao.atualizar(aluno);
    }

    public void excluir(String matricula) {
        if (dao.buscarPorMatricula(matricula) == null) {
            throw new IllegalArgumentException("Aluno não encontrado para excluir.");
        }
        dao.excluir(matricula);
    }
}
