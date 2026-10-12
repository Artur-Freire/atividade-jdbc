package com.freire;

import com.freire.dao.AlunoDAO;
import com.freire.dao.AlunoDAOImpl;
import com.freire.model.Aluno;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AlunoDAO dao = new AlunoDAOImpl();

        // CREATE: cadastrar os dois alunos
        dao.salvar(new Aluno("Marcelo", "123456789", 32, "Engenharia"));
        dao.salvar(new Aluno("Gabriel", "987654321", 23, "Biologia"));
        System.out.println("Alunos cadastrados.");

        // READ: buscar um aluno por matrícula
        Aluno a = dao.buscarPorMatricula("123456789");
        System.out.println("Encontrado: " + a.getNome());

        // UPDATE: atualizar um dos alunos
        a.setIdade(21);
        dao.atualizar(a);
        System.out.println("Aluno atualizado.");

        // READ: listar todos
        List<Aluno> lista = dao.listarTodos();
        for (Aluno aluno : lista) {
            System.out.println(aluno.getMatricula() + " - " + aluno.getNome()
                    + " - " + aluno.getIdade() + " - " + aluno.getCurso());
        }

        // DELETE: excluir os dois alunos
        dao.excluir("123456789");
        dao.excluir("987654321");
        System.out.println("Alunos excluídos.");
    }
}