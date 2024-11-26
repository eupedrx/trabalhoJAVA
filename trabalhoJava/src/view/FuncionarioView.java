package view;

import controller.FuncionarioController;
import controller.model.*;
import model.*;

import java.util.Scanner;

public class FuncionarioView {
    private FuncionarioController controller = new FuncionarioController();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        controller.carregarDados();

        int opcao;
        do {
            System.out.println("\n1. Cadastrar Funcionário");
            System.out.println("2. Listar Funcionários");
            System.out.println("3. Atualizar Funcionário");
            System.out.println("4. Excluir Funcionário");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> cadastrarFuncionario();
                case 2 -> listarFuncionarios();
                case 3 -> atualizarFuncionario();
                case 4 -> excluirFuncionario();
                case 5 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }

    private void cadastrarFuncionario() {
        System.out.println("Digite o nome do funcionário: ");
        String nome = scanner.next();
        System.out.println("Digite o salário: ");
        double salario = scanner.nextDouble();

        System.out.println("Escolha o tipo de funcionário: ");
        System.out.println("1. Desenvolvedor");
        System.out.println("2. Gerente");
        System.out.println("3. Treinador");
        System.out.println("4. Gerente Desenvolvedor");

        int tipo = scanner.nextInt();
        Funcionario funcionario = switch (tipo) {
            case 1 -> new Desenvolvedor(nome, salario);
            case 2 -> new Gerente(nome, salario);
            case 3 -> new Treinador(nome, salario);
            case 4 -> new GerenteDesenvolvedor(nome, salario);
            default -> null;
        };

        if (funcionario != null) {
            controller.cadastrarFuncionario(funcionario);
            System.out.println("Funcionário cadastrado com sucesso!");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private void listarFuncionarios() {
        for (Funcionario f : controller.listarFuncionarios()) {
            System.out.println(f.mostrarDetalhes());
        }
    }

    private void atualizarFuncionario() {
        listarFuncionarios();
        System.out.println("Escolha o índice do funcionário para atualizar: ");
        int index = scanner.nextInt();
        System.out.println("Digite o novo nome: ");
        String nome = scanner.next();
        System.out.println("Digite o novo salário: ");
        double salario = scanner.nextDouble();

        controller.atualizarFuncionario(index, nome, salario);
        System.out.println("Funcionário atualizado com sucesso!");
    }

    private void excluirFuncionario() {
        listarFuncionarios();
        System.out.println("Escolha o índice do funcionário para excluir: ");
        int index = scanner.nextInt();

        controller.excluirFuncionario(index);
        System.out.println("Funcionário excluído com sucesso!");
    }
}