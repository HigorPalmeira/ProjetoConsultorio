/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import main.java.higorpalmeira.com.github.consultorio.controller.*;
import main.java.higorpalmeira.com.github.consultorio.model.dao.DAOFactory;
import main.java.higorpalmeira.com.github.consultorio.model.enums.ConsultaStatus;
import main.java.higorpalmeira.com.github.consultorio.model.enums.PacienteSexo;
import main.java.higorpalmeira.com.github.consultorio.model.enums.Status;
import main.java.higorpalmeira.com.github.consultorio.service.ConsultaServiceImpl;
import main.java.higorpalmeira.com.github.consultorio.service.EnderecoServiceImpl;
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
    
    static EnderecoController enderecoController;
    
    public static void main(String[] args) {
        
        pacienteController = new PacienteController(new PacienteServiceImpl(DAOFactory.criarPacienteDAO()));
        especialidadeController = new EspecialidadeController(new EspecialidadeServiceImpl(DAOFactory.criarEspecialidadeDAO()));
        medicoController = new MedicoController(new MedicoServiceImpl(DAOFactory.criarMedicoDAO()));
        consultaController = new ConsultaController(new ConsultaServiceImpl(DAOFactory.criarConsultaDAO()));
        
        enderecoController = new EnderecoController(new EnderecoServiceImpl(DAOFactory.criarEnderecoDAO()));
        
        
        menuEndereco();
        menuEspecialidade();
        menuMedico();
        menuPaciente();
        menuConsulta();
        
    }
    
    private static void menuEndereco() {
        boolean running = true;
        
        while(running) {
            System.out.println("===\tMENU ENDEREÇO\t===");
            System.out.println("[ 1 ] Criar Endereço \n[ 2 ] Atualizar Endereço \n[ 3 ] Excluir Endereço \n[ 4 ] Lista Endereços \n[ 9 ] Sair");
            int opcao = scanner.nextInt();
            
            switch(opcao) {
                case 1 -> criarEndereco();
                    
                case 2 -> atualizarEndereco();
                    
                case 3 -> excluirEndereco();
                    
                case 4 -> listarEndereco();
                    
                case 9 -> running = false;
                    
                default -> System.out.println("Opção inva'lida!");
            }
        }
    }
    
    private static void criarEndereco() {
        String rua, numero, bairro, cidade, estado, cep;
        
        scanner.nextLine();
        
        System.out.println("===\tCriar Endereço\t===");
        
        System.out.println("Informe o número do CEP: ");
        cep = scanner.nextLine().trim();
        
        System.out.println("Informe a rua: ");
        rua = scanner.nextLine().trim();
        
        System.out.println("Informe o número: ");
        numero = scanner.nextLine().trim();
        
        System.out.println("Informe o bairro: ");
        bairro = scanner.nextLine().trim();
        
        System.out.println("Informe a cidade: ");
        cidade = scanner.nextLine().trim();
        
        System.out.println("Informe o estado: ");
        estado = scanner.nextLine();
        
        enderecoController.criarEndereco(rua, numero, bairro, cidade, estado, cep);
        
    }
    
    private static void atualizarEndereco() {
        int id;
        String rua, numero, bairro, cidade, estado, cep;
        
        scanner.nextLine();
        
        System.out.println("===\tCriar Endereço\t===");
        
        System.out.println("Informe o ID: ");
        id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Informe o número do CEP: ");
        cep = scanner.nextLine().trim();
        
        System.out.println("Informe a rua: ");
        rua = scanner.nextLine().trim();
        
        System.out.println("Informe o número: ");
        numero = scanner.nextLine().trim();
        
        System.out.println("Informe o bairro: ");
        bairro = scanner.nextLine().trim();
        
        System.out.println("Informe a cidade: ");
        cidade = scanner.nextLine().trim();
        
        System.out.println("Informe o estado: ");
        estado = scanner.nextLine();
        
        enderecoController.atualizarEndereco(id, rua, numero, bairro, cidade, estado, cep);
        
    }
    
    private static void excluirEndereco() {
        int id;
        
        System.out.println("Informe o ID do endereço a ser deletado: ");
        id = scanner.nextInt();
        
        enderecoController.deletarEndereco(id);
        
    }
    
    private static void listarEndereco() {
        System.out.println("Deseja listar (1) TODOS ou (2) ESPECÍFICO? ");
        if (scanner.nextInt() == 1) {
            enderecoController.listarTodosEnderecos();
            
        } else {
            System.out.println("Informe o ID do endereço para ser listado: ");
            int id = scanner.nextInt();
            enderecoController.buscarEnderecoPorId(id);
            
        }
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
        String observacoes;
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
        scanner.nextLine();
        
        dataHorario = LocalDateTime.of(ano, mes, dia, hora, minuto);
        
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
        
        consultaController.criarConsulta(idMedico, idPaciente, dataHorario, observacoes);
        
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
        scanner.nextLine();
        
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
        
        int i;
        
        scanner.nextLine();
        
        System.out.println("===\tListar Consultas\t===");
        System.out.println("Selecione a opção de listagem de consultas: ");
        System.out.println("""
                           [ 1 ] Todas 
                           [ 2 ] Por ID da consulta 
                           [ 3 ] Por Status da consulta
                           [ 4 ] Por Data e Horário da consulta
                           [ 5 ] Por ID do Médico 
                           [ 6 ] Por CRM do Médico
                           [ 7 ] Por ID da Especialidade do Médico
                           [ 8 ] Por Status do Médico
                           [ 9 ] Por ID do Paciente
                           [ 10 ] Por Status do Paciente
                           [ 11 ] Por Sexo do Paciente
                           [ 12 ] Cancelar
                           """);
        int opcao = scanner.nextInt();
        
        scanner.nextLine();
        
        switch(opcao) {
            case 1:
                consultaController.listarTodasConsultas();
                break;
                
            case 2:
                System.out.println("Informe o ID da consulta para ser listada: ");
                int id = scanner.nextInt();
                consultaController.buscarConsultaPorId(id);
                break;
                
            case 3:
                System.out.println("Informe o ID do status da consulta para ser listada: ");
                i = 0;;
                for (ConsultaStatus status : ConsultaStatus.values()) {
                    System.out.printf("[ %d ] %s\n", i, status.getDescricao());
                    i++;
                }
                i = scanner.nextInt();
                consultaController.buscarConsultaPorStatus( ConsultaStatus.values()[i].getDescricao() );
                break;
                
            case 4:
                System.out.println("Informe a data e hora da consulta para ser listada: ");
                
                System.out.println("- Dia: ");
                int dia = scanner.nextInt();
                System.out.println("- Mês: ");
                int mes = scanner.nextInt();
                System.out.println("- Ano: ");
                int ano = scanner.nextInt();
                
                System.out.println("- Horas: ");
                int horas = scanner.nextInt();
                System.out.println("- Minutos: ");
                int minutos = scanner.nextInt();
                
                LocalDateTime dataHora = LocalDateTime.of(ano, mes, dia, horas, minutos);
                
                consultaController.buscarConsultaPorDataHora(dataHora);
                
                break;
                
            case 5:
                System.out.println("Informe o ID do médico para listar suas consultas: ");
                medicoController.listarTodosMedicos();
                i = scanner.nextInt();
                consultaController.buscarConsultaPorIdMedico(i);
                break;
                
            case 6:
                System.out.println("Informe o CRM do médico para listar suas consultas: ");
                String crm = scanner.nextLine().trim().toUpperCase();
                consultaController.buscarConsultaPorCrmMedico(crm);
                break;
                
            case 7:
                System.out.println("Informe o ID da especialidade para listar consultas relacionadas: ");
                i = scanner.nextInt();
                consultaController.buscarConsultaPorIdEspecialidadeMedico(i);
                break;
                
            case 8:
                System.out.println("Informe o status do médico para listar as consultas: ");
                i = 0;
                for(Status status : Status.values()) {
                    System.out.printf("[ %d ] %s\n", i, status.getDescricao());
                    i++;
                }
                i = scanner.nextInt();
                consultaController.buscarConsultaPorStatusMedico( Status.values()[i].getDescricao() );
                break;
                
            case 9:
                System.out.println("Informe o ID do paciente para listar suas consultas: ");
                pacienteController.listarTodosPacientes();
                i = scanner.nextInt();
                consultaController.buscarConsultaPorIdPaciente(i);
                break;
             
            case 10:
                System.out.println("Informe o status do paciente para listar as consultas: ");
                i = 0;
                for(Status status : Status.values()) {
                    System.out.printf("[ %d ] %s\n", i, status.getDescricao());
                    i++;
                }
                i = scanner.nextInt();
                consultaController.buscarConsultaPorStatusPaciente( Status.values()[i].getDescricao() );
                break;
                
            case 11:
                System.out.println("Informe o sexo para listar consultas: ");
                i = 0;
                for(PacienteSexo sexo : PacienteSexo.values()) {
                    System.out.printf("[ %d ] %s\n", i, sexo.getDescricao());
                    i++;
                }
                i = scanner.nextInt();
                consultaController.buscarConsultaPorSexoPaciente( PacienteSexo.values()[i].getDescricao() );
                break;
                
            case 12:
                System.out.println("Cancelando listagem de consultas!");
                break;
                
            default:
                System.out.println("Opção inválida!");
                break;
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
        String nome, crm, status, telefone, email;
        int id, idEspecialidade;
        
        System.out.println("===\tAtualizar Médico\t===");
        
        System.out.println("Informe o ID do médico: ");
        id = scanner.nextInt();
        scanner.next();
        
        System.out.println("Informe o nome do médico: ");
        nome = scanner.nextLine();
        
        System.out.println("Informe o CRM do médico: ");
        crm = scanner.nextLine();
        
        System.out.println("Informe o status do médico: ");
        status = scanner.nextLine();
        
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
        
        medicoController.atualizarMedico(id, nome, crm, idEspecialidade, status, telefone, email);
        
    }
    
    private static void excluirMedico() {
        int id;
        
        System.out.println("===\tExcluir Médico\t===");
        
        System.out.println("Informe o ID do médico a ser deletado: ");
        id = scanner.nextInt();
        
        medicoController.deletarMedico(id);
        
    }
    
    private static void listarMedicos() {
        
        int i;
        
        scanner.nextLine();
        
        System.out.println("===\tListar Médicos\t===");
        System.out.println("Selecione a opção de listagem de médicos: ");
        System.out.println("[ 1 ] Todos \n[ 2 ] Por ID \n[ 3 ] Por CRM \n[ 4 ] Por Telefone \n[ 5 ] Por Status \n[ 6 ] Por Especialidade \n[ 9 ] Cancelar");
        int opcao = scanner.nextInt();
        
        scanner.nextLine();
        
        switch(opcao) {
            case 1 -> medicoController.listarTodosMedicos();
                
            case 2 -> {
                System.out.println("Informe o ID do médico para ser listado: ");
                int id = scanner.nextInt();
                medicoController.buscarMedicoPorId(id);
            }
                
            case 3 -> {
                System.out.println("Informe o CRM do médico para ser listado: ");
                String crm = scanner.nextLine().trim().toUpperCase();
                medicoController.buscarMedicoPorCrm(crm);
            }
                
            case 4 -> {
                System.out.println("Informe o Telefone do paciente para ser listado: ");
                String telefone = scanner.nextLine().trim();
                medicoController.buscarMedicoPorTelefone(telefone);
            }
                
            case 5 -> {
                System.out.println("Informe o Status do médico para ser listado: ");
                i = 0;
                for (Status status : Status.values()) {
                    System.out.printf("[ %d ] %s\n", i, status.getDescricao());
                    i++;
                }
                i = scanner.nextInt();
                medicoController.buscarMedicoPorStatus( Status.values()[i].getDescricao() );
            }
                
            case 6 -> {
                System.out.println("Informe o ID da especialidade do médico para ser listado: ");
                especialidadeController.listarTodasEspecialidades();
                i = scanner.nextInt();
                medicoController.buscarMedicoPorEspecialidade( i );
            }
                
            case 9 -> System.out.println("Cancelando listagem!");
                
            default -> System.out.println("Opção inválida!");
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
        String nome, cpf, telefone, email, sexo;
        LocalDate dataNascimento;
        int idEndereco;
        
        scanner.nextLine();
        
        System.out.println("===\tCriar Paciente\t===");
        
        System.out.println("Informe o nome do paciente (max: 255): ");
        nome = scanner.nextLine();
        
        System.out.println("Informe o CPF do paciente (###.###.###-##): ");
        cpf = scanner.nextLine();
        
        System.out.println("Informe o sexo do paciente: ");
        sexo = scanner.nextLine().trim().toLowerCase();
        
        System.out.println("Informe a data de nascimento do paciente: ");
        System.out.println("- Dia: ");
        int dia = scanner.nextInt();
        System.out.println("- Mês: ");
        int mes = scanner.nextInt();
        System.out.println("- Ano: ");
        int ano = scanner.nextInt();
        dataNascimento = LocalDate.of(ano, mes, dia);
        scanner.nextLine();
        
        System.out.println("Informe o telefone do paciente: ");
        telefone = scanner.nextLine().trim();
        
        System.out.println("Informe o email do paciente: ");
        email = scanner.nextLine().trim();
        
        System.out.println("---\tEndereço do Paciente\t---");
        
        do {
            enderecoController.listarTodosEnderecos();
            System.out.println("Informe o id do endereco do paciente (se não encontrar digite -1): ");
            idEndereco = scanner.nextInt();

            if (idEndereco == -1) {
                criarEndereco();
            }
        } while(idEndereco == -1);
        
        pacienteController.criarPaciente(nome, cpf, dataNascimento, sexo, telefone, email, idEndereco);
        
    }
    
    private static void atualizarPaciente() {
        String nome, cpf, telefone, email, sexo, status;
        LocalDate dataNascimento;
        int id, idEndereco;
        
        System.out.println("===\tAtualizar Paciente\t===");
        
        System.out.println("Informe o ID do paciente: ");
        id = scanner.nextInt();
        scanner.next();
        
        System.out.println("Informe o nome do paciente (max: 255): ");
        nome = scanner.nextLine();
        
        System.out.println("Informe o CPF do paciente (###.###.###-##): ");
        cpf = scanner.nextLine();
        
        System.out.println("Informe o sexo do paciente: ");
        sexo = scanner.nextLine().trim().toLowerCase();
        
        System.out.println("Informe o status do paciente: ");
        status = scanner.nextLine().trim().toLowerCase();
        
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
        
        do {
            enderecoController.listarTodosEnderecos();
            System.out.println("Informe o id do endereco do paciente (se não encontrar digite -1): ");
            idEndereco = scanner.nextInt();

            if (idEndereco == -1) {
                criarEndereco();
            }
        } while(idEndereco == -1);
        
        pacienteController.atualizarPaciente(id, nome, cpf, dataNascimento, sexo, status, telefone, email, idEndereco);
        
    }
    
    private static void excluirPaciente() {
        int id;
        
        System.out.println("===\tExcluir Paciente\t===");
        
        System.out.println("Informe o ID do paciente a ser deletado: ");
        id = scanner.nextInt();
        
        pacienteController.deletarPaciente(id);
    }
    
    private static void listarPacientes() {
        int i;
        
        scanner.nextLine();
        
        System.out.println("===\tListar Pacientes\t===");
        System.out.println("Selecione a opção de listagem de pacientes: ");
        System.out.println("[ 1 ] Todos \n[ 2 ] Por ID \n[ 3 ] Por CPF \n[ 4 ] Por Telefone \n[ 5 ] Por Sexo \n[ 6 ] Por Status \n[ 9 ] Cancelar");
        int opcao = scanner.nextInt();
        
        scanner.nextLine();
        
        switch(opcao) {
            case 1 -> pacienteController.listarTodosPacientes();
                
            case 2 -> {
                System.out.println("Informe o ID do paciente para ser listado: ");
                int id = scanner.nextInt();
                pacienteController.buscarPacientePorId(id);
            }
                
            case 3 -> {
                System.out.println("Informe o CPF do paciente para ser listado: ");
                String cpf = scanner.nextLine().trim();
                pacienteController.buscarPacientePorCpf(cpf);
            }
                
            case 4 -> {
                System.out.println("Informe o Telefone do paciente para ser listado: ");
                String telefone = scanner.nextLine().trim();
                pacienteController.buscarPacientePorTelefone(telefone);
            }
                
            case 5 -> {
                System.out.println("Informe o Sexo do paciente para ser listado: ");
                i = 0;
                for (PacienteSexo sexo : PacienteSexo.values()) {
                    System.out.printf("[ %d ] %s\n", i, sexo.getDescricao());
                    i++;
                }
                i = scanner.nextInt();
                pacienteController.buscarPacientePorSexo( PacienteSexo.values()[i].getDescricao() );
            }
                
            case 6 -> {
                System.out.println("Informe o Status do paciente para ser listado: ");
                i = 0;
                for (Status status : Status.values()) {
                    System.out.printf("[ %d ] %s\n", i, status.getDescricao());
                    i++;
                }
                i = scanner.nextInt();
                pacienteController.buscarPacientePorStatus( Status.values()[i].getDescricao() );
            }
                
            case 9 -> System.out.println("Cancelando listagem!");
                
            default -> System.out.println("Opção inválida!");
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
