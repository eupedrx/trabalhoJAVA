package controller;

import model.Funcionario;

import java.io.*;
import java.util.ArrayList;

public class FuncionarioController {
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private final String arquivo = "data/funcionarios.dat";

    public void cadastrarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        salvarDados();
    }

    public ArrayList<Funcionario> listarFuncionarios() {
        return funcionarios;
    }

    public void atualizarFuncionario(int index, String nome, double salario) {
        Funcionario funcionario = funcionarios.get(index);
        funcionario.setNome(nome);
        funcionario.setSalario(salario);
        salvarDados();
    }

    public void excluirFuncionario(int index) {
        funcionarios.remove(index);
        salvarDados();
    }

    public void carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            funcionarios = (ArrayList<Funcionario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(funcionarios);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
}