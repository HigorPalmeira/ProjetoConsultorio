/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import main.java.higorpalmeira.com.github.consultorio.controller.ConsultaController;
import main.java.higorpalmeira.com.github.consultorio.controller.EspecialidadeController;
import main.java.higorpalmeira.com.github.consultorio.controller.MedicoController;
import main.java.higorpalmeira.com.github.consultorio.controller.PacienteController;
import main.java.higorpalmeira.com.github.consultorio.model.dao.DAOFactory;
import main.java.higorpalmeira.com.github.consultorio.service.ConsultaServiceImpl;
import main.java.higorpalmeira.com.github.consultorio.service.EspecialidadeServiceImpl;
import main.java.higorpalmeira.com.github.consultorio.service.MedicoServiceImpl;
import main.java.higorpalmeira.com.github.consultorio.service.PacienteServiceImpl;

/**
 *
 * @author higor
 */
public class Main {

    static Scanner scanner = new Scanner(System.in);
    static PacienteController pacienteController;
    static EspecialidadeController especialidadeController;
    static MedicoController medicoController;
    static ConsultaController consultaController;
    
    public static void main(String[] args) {
        
        pacienteController = new PacienteController(new PacienteServiceImpl(DAOFactory.criarPacienteDAO()));
        especialidadeController = new EspecialidadeController(new EspecialidadeServiceImpl(DAOFactory.criarEspecialidadeDAO()));
        medicoController = new MedicoController(new MedicoServiceImpl(DAOFactory.criarMedicoDAO()));
        consultaController = new ConsultaController(new ConsultaServiceImpl(DAOFactory.criarConsultaDAO()));
        
        menuEspecialidade();
        menuMedico();
        menuPaciente();
        menuConsulta();
    }
    
    private static void menuConsulta() {
        boolean running = true;
        
        while(running) {
            System.out.println("===\tMENU CONSULTA\t===");
            System.out.println("[ 1 ] Criar Consulta \n[ 2 ] Atualizar Consulta \n[ 3 ] Excluir Consulta \n[ 4 ] Lista Consultas \n[ 9 ] Sair");
            int opcao = scanner.nextInt();
            
            switch(opcao) {
                case 1 -> criarConsulta();
                    
                case 2 -> atualizarConsulta();
                    
                case 3 -> excluirConsulta();
                    
                case 4 -> listarConsultas();
                    
                case 9 -> running = false;
                    
                default -> System.out.println("Opção inva'lida!");
            }
        }
    }
    
    private static void criarConsulta() {
        LocalDateTime dataHorario;
        String observacoes, status;
        int idMedico, idPaciente;
        
        scanner.nextLine();
        
        System.out.println("===\tCriar Consulta\t===");
        
        System.out.println("Informe a data da consulta: ");
        System.out.println("- Dia: ");
        int dia = scanner.nextInt();
        System.out.println("- Mês: ");
        int mes = scanner.nextInt();
        System.out.println("- Ano: ");
        int ano = scanner.nextInt();
        
        System.out.println("Informe o horário da consulta: ");
        System.out.println("- Hora: ");
        int hora = scanner.nextInt();
        System.out.println("- Minutos: ");
        int minuto = scanner.nextInt();
        
        dataHorario = LocalDateTime.of(ano, mes, dia, hora, minuto);
        
        System.out.println("Informe o status da consulta: ");
        status = scanner.nextLine();
        
        System.out.println("Informe as observações: ");
        observacoes = scanner.nextLine();
        
        do {
            pacienteController.listarTodosPacientes();
            System.out.println("Informe o ID do paciente que será consultado (se não encontrar digite -1): ");
            idPaciente = scanner.nextInt();
            
            if (idPaciente == -1) {
                criarPaciente();
            }
        } while(idPaciente == -1);
        
        do {
            medicoController.listarTodosMedicos();
            System.out.println("Informe o ID do médico que irá fazer a consulta (se não encontrar digite -1): ");
            idMedico = scanner.nextInt();
            
            if (idMedico == -1) {
                criarMedico();
            }
        } while(idMedico == -1);
        
        consultaController.criarConsulta(idMedico, idPaciente, dataHorario, observacoes, status);
        
    }
    
    private static void atualizarConsulta() {
        LocalDateTime dataHorario;
        String observacoes, status;
        int id, idMedico, idPaciente;
        
        scanner.nextLine();
        
        System.out.println("===\tAtualizar Consulta\t===");
        
        System.out.println("Informe o ID da consulta: ");
        id = scanner.nextInt();
        
        System.out.println("Informe a data da consulta: ");
        System.out.println("- Dia: ");
        int dia = scanner.nextInt();
        System.out.println("- Mês: ");
        int mes = scanner.nextInt();
        System.out.println("- Ano: ");
        int ano = scanner.nextInt();
        
        System.out.println("Informe o horário da consulta: ");
        System.out.println("- Hora: ");
        int hora = scanner.nextInt();
        System.out.println("- Minutos: ");
        int minuto = scanner.nextInt();
        
        dataHorario = LocalDateTime.of(ano, mes, dia, hora, minuto);
        
        System.out.println("Informe o status da consulta: ");
        status = scanner.nextLine();
        
        System.out.println("Informe as observações: ");
        observacoes = scanner.nextLine();
        
        do {
            pacienteController.listarTodosPacientes();
            System.out.println("Informe o ID do paciente que será consultado (se não encontrar digite -1): ");
            idPaciente = scanner.nextInt();
            
            if (idPaciente == -1) {
                criarPaciente();
            }
        } while(idPaciente == -1);
        
        do {
            medicoController.listarTodosMedicos();
            System.out.println("Informe o ID do médico que irá fazer a consulta (se não encontrar digite -1): ");
            idMedico = scanner.nextInt();
            
            if (idMedico == -1) {
                criarMedico();
            }
        } while(idMedico == -1);
        
        consultaController.atualizarConsulta(id, idMedico, idPaciente, dataHorario, observacoes, status);
        
    }
    
    private static void excluirConsulta() {
        int id;
        
        System.out.println("Informe o ID da consulta a ser deletada: ");
        id = scanner.nextInt();
        
        consultaController.deletarConsulta(id);
        
    }
    
    private static void listarConsultas() {
        
        System.out.println("Deseja listar (1) TODAS ou (2) ESPECÍFICA? ");
        if (scanner.nextInt() == 1) {
            consultaController.listarTodasConsultas();
            
        } else {
            System.out.println("Informe o ID da consulta para se listada: ");
            int id = scanner.nextInt();
            consultaController.buscarConsultaPorId(id);
        }
        
    }
    
    private static void menuMedico() {
        boolean running = true;
        
        while(running) {
            System.out.println("===\tMENU MÉDICO\t===");
            System.out.println("[ 1 ] Criar Médico \n[ 2 ] Atualizar Médico \n[ 3 ] Excluir Médico \n[ 4 ] Listar Médicos \n[ 9 ] Sair");
            int opcao = scanner.nextInt();
            
            switch(opcao) {
                case 1 -> criarMedico();
                    
                case 2 -> atualizarMedico();
                    
                case 3 -> excluirMedico();
                
                case 4 -> listarMedicos();
                    
                case 9 -> running = false;
                    
                default -> System.out.println("Opção inva'lida!");
            }
        }
    }
    
    private static void criarMedico() {
        String nome, crm, telefone, email;
        int idEspecialidade;
        
        scanner.nextLine();
        
        System.out.println("===\tCriar Médico\t===");
        
        System.out.println("Informe o nome do médico:");
        nome = scanner.nextLine();
        
        System.out.println("Informe o CRM do médico: ");
        crm = scanner.nextLine();
        
        System.out.println("Informe o telefone do médico: ");
        telefone = scanner.nextLine();
        
        System.out.println("Informe o email do médico: ");
        email = scanner.nextLine();
        
        do {
            especialidadeController.listarTodasEspecialidades();
            System.out.println("Informe o id da especialidade do médico (se não encontrar digite -1): ");
            idEspecialidade = scanner.nextInt();

            if (idEspecialidade == -1) {
                criarEspecialidade();
            }
        } while(idEspecialidade == -1);
        
        medicoController.criarMedico(nome, crm, idEspecialidade, telefone, email);
    }
    
    private static void atualizarMedico() {
        String nome, crm, telefone, email;
        int id, idEspecialidade;
        
        System.out.println("===\tAtualizar Médico\t===");
        
        System.out.println("Informe o ID do médico: ");
        id = scanner.nextInt();
        scanner.next();
        
        System.out.println("Informe o nome do médico: ");
        nome = scanner.nextLine();
        
        System.out.println("Informe o CRM do médico: ");
        crm = scanner.nextLine();
        
        System.out.println("Informe o telefone do médico: ");
        telefone = scanner.nextLine();
        
        System.out.println("Informe o email do médico: ");
        email = scanner.nextLine();
        
        do {
            especialidadeController.listarTodasEspecialidades();
            System.out.println("Informe o id da especialidade do médico (se não encontrar digite -1): ");
            idEspecialidade = scanner.nextInt();

            if (idEspecialidade == -1) {
                criarEspecialidade();
            }
        } while(idEspecialidade == -1);
        
        medicoController.atualizarMedico(id, nome, crm, idEspecialidade, telefone, email);
        
    }
    
    private static void excluirMedico() {
        int id;
        
        System.out.println("===\tExcluir Médico\t===");
        
        System.out.println("Informe o ID do médico a ser deletado: ");
        id = scanner.nextInt();
        
        medicoController.deletarMedico(id);
        
    }
    
    private static void listarMedicos() {
        
        System.out.println("Deseja listar (1) TODOS ou (2) ESPECÍFICO? ");
        if (scanner.nextInt() == 1) {
            medicoController.listarTodosMedicos();
            
        } else {
            System.out.println("Informe o ID do médico para ser listado: ");
            int id = scanner.nextInt();
            medicoController.buscarMedicoPorId(id);
        }
        
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
        
        scanner.nextLine();
        
        System.out.println("===\tCriar Paciente\t===");
        
        System.out.println("Informe o nome do paciente (max: 255): ");
        nome = scanner.nextLine();
        
        System.out.println("Informe o CPF do paciente (###.###.###-##): ");
        cpf = scanner.nextLine();
        
        System.out.println("Informe a data de nascimento do paciente: ");
        System.out.println("- Dia: ");
        int dia = scanner.nextInt();
        System.out.println("- Mês: ");
        int mes = scanner.nextInt();
        System.out.println("- Ano: ");
        int ano = scanner.nextInt();
        dataNascimento = LocalDate.of(ano, mes, dia);
        
        System.out.println("Informe o telefone do paciente: ");
        telefone = scanner.nextLine().trim();
        
        System.out.println("Informe o email do paciente: ");
        email = scanner.nextLine();
        
        pacienteController.criarPaciente(nome, cpf, dataNascimento, telefone, email);
        
    }
    
    private static void atualizarPaciente() {
        String nome, cpf, telefone, email;
        LocalDate dataNascimento;
        int id;
        
        System.out.println("===\tAtualizar Paciente\t===");
        
        System.out.println("Informe o ID do paciente: ");
        id = scanner.nextInt();
        scanner.next();
        
        System.out.println("Informe o nome do paciente (max: 255): ");
        nome = scanner.nextLine();
        
        System.out.println("Informe o CPF do paciente (###.###.###-##): ");
        cpf = scanner.nextLine();
        
        System.out.println("Informe a data de nascimento do paciente: ");
        System.out.println("- Dia: ");
        int dia = scanner.nextInt();
        System.out.println("- Mês: ");
        int mes = scanner.nextInt();
        System.out.println("- Ano: ");
        int ano = scanner.nextInt();
        dataNascimento = LocalDate.of(ano, mes, dia);
        
        System.out.println("Informe o telefone do paciente: ");
        telefone = scanner.nextLine();
        
        System.out.println("Informe o email do paciente: ");
        email = scanner.nextLine();
        
        pacienteController.atualizarPaciente(id, nome, cpf, dataNascimento, telefone, email);
        
    }
    
    private static void excluirPaciente() {
        int id;
        
        System.out.println("===\tExcluir Paciente\t===");
        
        System.out.println("Informe o ID do paciente a ser deletado: ");
        id = scanner.nextInt();
        
        pacienteController.deletarPaciente(id);
    }
    
    private static void listarPacientes() {
        
        System.out.println("Deseja listar (1) TODOS ou (2) ESPECÍFICO? ");
        if (scanner.nextInt() == 1) {
            pacienteController.listarTodosPacientes();
            
        } else {
            System.out.println("Informe o ID do paciente para ser listado: ");
            int id = scanner.nextInt();
            pacienteController.buscarPacientePorId(id);
        }
    }
    
    private static void menuEspecialidade() {
        boolean running = true;
        
        while(running) {
            
            System.out.println("===\tMENU ESPECIALIDADE\t===");
            System.out.println("[ 1 ] Criar Especialidade \n[ 2 ] Atualizar Especialidade \n[ 3 ] Excluir Especialidade \n[ 4 ] Listar Especialidades \n[ 9 ] Sair");
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
        
        scanner.nextLine();
        
        System.out.println("===\tCriar Especialidade\t===");
        System.out.println("Informe a descricao (max:100): ");
        descricao = scanner.nextLine();
        
        especialidadeController.criarEspecialidade(descricao);
        
    }
    
    private static void listarEspecialidade() {
        
        System.out.println("Deseja listar (1) TODAS ou (2) ESPECÍFICA? ");
        if (scanner.nextInt() == 1) {
            especialidadeController.listarTodasEspecialidades();
            
        } else {
            System.out.println("Informe o ID da especialidade para ser listada: ");
            int id = scanner.nextInt();
            
            especialidadeController.buscarEspecialidadePorId(id);
        }
        
    }
    
    private static void excluirEspecialidade() {
        int id;
        System.out.println("===\tExcluir Especialidade\t===");
        System.out.println("Informe o ID da especialidade: ");
        id = scanner.nextInt();
        
        especialidadeController.deletarEspecialidade(id);
        
    }
    
    private static void atualizarEspecialidade() {
        int id;
        String descricao;
        System.out.println("===\tAtualizar Especialidade\t===");
        System.out.println("Informe o ID da especialidade: ");
        id = scanner.nextInt();
        
        System.out.println("Informe a nova descricao (max: 100): ");
        descricao = scanner.nextLine();
        
        especialidadeController.atualizarEspecialidade(id, descricao);
        
    }
    
}
