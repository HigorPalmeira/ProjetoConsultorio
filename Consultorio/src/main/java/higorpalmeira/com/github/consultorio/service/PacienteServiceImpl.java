/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.service;

import java.util.Date;
import java.util.List;
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
    public boolean criarPaciente(String nome, String cpf, Date dataNascimento, 
                                    String telefone, String email) {
        
        // verificar nome
        if (nome == null || nome.trim().isBlank() || nome.length() > 255) return false;
        
        // verificar cpf
        if ( ! Validator.isCpf(cpf) ) return false;
        
        // verificar data de nascimento
        if ( dataNascimento == null ) return false;
        
        
        
    }

    @Override
    public boolean atualizarPaciente(int id, String nome, String cpf, Date dataNascimento, String telefone, String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deletarPaciente(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Paciente> listarTodosPaciente() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Paciente buscarPacientePorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
