/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.higorpalmeira.com.github.consultorio.model.dao.PacienteDAO;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Paciente;
import main.java.higorpalmeira.com.github.consultorio.util.validator.Validator;

/**
 *
 * @author higor
 */
public class PacienteServiceImpl implements IPacienteService {
    
    private final PacienteDAO pacienteDAO;
    
    public PacienteServiceImpl(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    @Override
    public boolean criarPaciente(String nome, String cpf, LocalDate dataNascimento, 
                                    String telefone, String email) {
        
        // verificar nome
        if (nome == null || nome.trim().isBlank() || nome.trim().length() > 255) {
            System.out.println("]:Nome inva'lido!");
            return false;
        }
        
        // verificar cpf
        if ( ! Validator.isCpf(cpf) ) {
            System.out.println("]:CPF inva'lido!");
            return false;
        }
        
        // verificar data de nascimento
        if ( ! Validator.isDataNascimento(dataNascimento) ) {
            System.out.println("]:Data de Nascimento inva'lida!");
            return false;
        }
        
        // verificar email
        if ( ! Validator.isEmail(email) ) {
            System.out.println("]:Email inva'lido!");
            return false;
        }
        
        // verificar telefone
        if (telefone == null || telefone.isBlank() || telefone.length() > 20) {
            System.out.println("]:Telefone inva'lido!");
            return false;
        }
        
        Paciente paciente = new Paciente();
        paciente.setNome(nome);
        paciente.setCpf(cpf);
        paciente.setDataNascimento(dataNascimento);
        paciente.setTelefone(telefone);
        paciente.setEmail(email);
        
        return pacienteDAO.insert(paciente) > 0;
    }

    @Override
    public boolean atualizarPaciente(int id, String nome, String cpf, 
                                    LocalDate dataNascimento, String telefone, 
                                    String email) {
        
        // verificar nome
        if (nome == null || nome.trim().isBlank() || nome.trim().length() > 255) return false;
        
        // verificar cpf
        if ( ! Validator.isCpf(cpf) ) return false;
        
        // verificar data de nascimento
        if ( ! Validator.isDataNascimento(dataNascimento) ) return false;
        
        // verificar email
        if ( ! Validator.isEmail(email) ) return false;
        
        // verificar telefone
        if (telefone == null || telefone.trim().isBlank() || nome.trim().length() > 20) return false;
        
        Paciente paciente = new Paciente();
        paciente.setNome(nome);
        paciente.setCpf(cpf);
        paciente.setDataNascimento(dataNascimento);
        paciente.setTelefone(telefone);
        paciente.setEmail(email);
        
        return pacienteDAO.update(paciente) > 0;
    }

    @Override
    public boolean deletarPaciente(int id) {
        
        if (id < 0) return false;
        
        boolean deletado = false;
        try {
            deletado = pacienteDAO.delete(id) > 0;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PacienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return deletado;
    }

    @Override
    public List<Paciente> listarTodosPaciente() {
        
        return pacienteDAO.selectAll();
        
    }

    @Override
    public Paciente buscarPacientePorId(int id) {
        
        if (id < 0) return null;
        
        return pacienteDAO.selectId(id);
    }
    
    
}
