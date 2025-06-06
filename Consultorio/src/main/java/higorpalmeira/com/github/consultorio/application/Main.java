/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.application;

import java.time.LocalDate;
import java.util.Scanner;
import main.java.higorpalmeira.com.github.consultorio.controller.EspecialidadeController;
import main.java.higorpalmeira.com.github.consultorio.controller.PacienteController;
import main.java.higorpalmeira.com.github.consultorio.model.dao.DAOFactory;
import main.java.higorpalmeira.com.github.consultorio.service.PacienteServiceImpl;

/**
 *
 * @author higor
 */
public class Main {

    static Scanner scanner = new Scanner(System.in);
    static PacienteController pacienteController;
    
    public static void main(String[] args) {
        
        pacienteController = new PacienteController(new PacienteServiceImpl(DAOFactory.criarPacienteDAO()));
        
        menuEspecialidade();
    }
    
    private static void menuPaciente() {
        boolean running = true;
        
        while(running) {
            System.out.println("===\tMENU PACIENTE\t===");
            System.out.println("[ 1 ] Criar Paciente \n[ 2 ] Atualizar Paciente \n[ 3 ] Excluir Especialidade \n[ 4 ] Listar Pacientes \n[ 9 ] Sair");
            int opcao = scanner.nextInt();
            
            switch(opcao) {
                case 1 -> criarPaciente();
                    
                case 2 -> atualizarPaciente();
                    
                case 3 -> excluirPaciente();
                    
                case 4 -> listarPacientes();
                    
                case 9 -> running = false;
                    
                default -> System.out.println("Opção inva'lida!");
            }
        }
    }
    
    private static void criarPaciente() {
        String nome, cpf, telefone, email;
        LocalDate dataNascimento;
        
        System.out.println("===\tCriar Paciente\t===");
        
        System.out.println("Informe o nome do paciente (max: 255): ");
        nome = scanner.next();
        
        System.out.println("Informe o CPF do paciente (###.###.###-##): ");
        cpf = scanner.next();
        
        System.out.println("Informe a data de nascimento do paciente: ");
        System.out.println("- Dia: ");
        int dia = scanner.nextInt();
        System.out.println("- Mês: ");
        int mes = scanner.nextInt();
        System.out.println("- Ano: ");
        int ano = scanner.nextInt();
        dataNascimento = LocalDate.of(ano, mes, dia);
        
        System.out.println("Informe o telefone do paciente: ");
        telefone = scanner.next();
        
        System.out.println("Informe o email do paciente: ");
        email = scanner.next();
        
        pacienteController.criarPaciente(nome, cpf, dataNascimento, telefone, email);
        
    }
    
    private static void atualizarPaciente() {
        String nome, cpf, telefone, email;
        LocalDate dataNascimento;
        int id;
        
        System.out.println("===\tAtualizar Paciente\t===");
        
        System.out.println("Informe o ID do paciente: ");
        id = scanner.nextInt();
        
        System.out.println("Informe o nome do paciente (max: 255): ");
        nome = scanner.next();
        
        System.out.println("Informe o CPF do paciente (###.###.###-##): ");
        cpf = scanner.next();
        
        System.out.println("Informe a data de nascimento do paciente: ");
        System.out.println("- Dia: ");
        int dia = scanner.nextInt();
        System.out.println("- Mês: ");
        int mes = scanner.nextInt();
        System.out.println("- Ano: ");
        int ano = scanner.nextInt();
        dataNascimento = LocalDate.of(ano, mes, dia);
        
        System.out.println("Informe o telefone do paciente: ");
        telefone = scanner.next();
        
        System.out.println("Informe o email do paciente: ");
        email = scanner.next();
        
        pacienteController.atualizarPaciente(id, nome, cpf, dataNascimento, telefone, email);
        
    }
    
    private static void excluirPaciente() {
        int id;
        
        System.out.println("===\tExcluir Paciente\t===");
        
        System.out.println("Informe o ID do paciente a ser deletado: ");
        id = scanner.nextInt();
        
        pacienteController.deletarPaciente(id);
    }
    
    private static void menuEspecialidade() {
        boolean running = true;
        
        while(running) {
            
            System.out.println("===\tMENU ESPECIALIDADE\t===");
            System.out.println("[ 1 ] Criar Especialidade \n[ 2 ] Atualizar Especialidade \n[ 3 ] Excluir Especialidade \n[ 4 ] Listar Especialidades");
            int opcao = scanner.nextInt();
            
            switch(opcao) {
                case 1 -> criarEspecialidade();
                    
                case 2 -> atualizarEspecialidade();
                    
                case 3 -> excluirEspecialidade();
                    
                case 4 -> listarEspecialidade();
                    
                case 9 -> running = false;
                    
                default -> System.out.println("Opcao inva'lida!");
            }
        }
    }
    
    private static void criarEspecialidade() {
        String descricao;
        
        System.out.println("===\tCriar Especialidade\t===");
        System.out.println("Informe a descricao (max:100): ");
        descricao = scanner.next();
        
        EspecialidadeController.criarEspecialidade(descricao, null);
        
    }
    
    private static void listarEspecialidade() {
        
        System.out.println("Deseja listar (1) TODAS ou (2) ESPECÍFICA? ");
        if (scanner.nextInt() == 1) {
            EspecialidadeController.listarEspecialidades();
        } else {
            System.out.println("Informe o ID da especialidade para ser listada: ");
            int id = scanner.nextInt();
            EspecialidadeController.listarEspecialidade(id, null);
        }
        
    }
    
    private static void excluirEspecialidade() {
        int id;
        System.out.println("===\tExcluir Especialidade\t===");
        System.out.println("Informe o ID da especialidade: ");
        id = scanner.nextInt();
        
        EspecialidadeController.excluirEspecialidade(id, null);
        
    }
    
    private static void atualizarEspecialidade() {
        int id;
        String descricao;
        System.out.println("===\tAtualizar Especialidade\t===");
        System.out.println("Informe o ID da especialidade: ");
        id = scanner.nextInt();
        
        System.out.println("Informe a nova descricao (max: 100): ");
        descricao = scanner.next();
        
        EspecialidadeController.editarEspecialidade(id, null, descricao);
        
    }
    
}
