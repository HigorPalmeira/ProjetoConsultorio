/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.higorpalmeira.com.github.consultorio.model.dao.DAOFactory;
import main.java.higorpalmeira.com.github.consultorio.model.dao.PacienteDAO;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Endereco;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Paciente;
import main.java.higorpalmeira.com.github.consultorio.model.enums.PacienteSexo;
import main.java.higorpalmeira.com.github.consultorio.model.enums.Status;
import main.java.higorpalmeira.com.github.consultorio.util.validator.Validator;

/**
 *
 * @author higor
 */
public class PacienteServiceImpl implements IPacienteService {
    
    private final String STATUS_DEFAULT = "ativo";
    private final List<String> SEXO = Arrays.asList(
            "masculino", "feminino"
    );
    
    private final PacienteDAO pacienteDAO;
    
    public PacienteServiceImpl(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    @Override
    public boolean criarPaciente(String nome, String cpf, LocalDate dataNascimento, String sexo, String telefone, String email, int idEndereco) {
        
        // verificar nome
        if (nome == null || nome.trim().isBlank() || nome.trim().length() > 255) {
            return false;
        }
        
        // verificar cpf
        if ( ! Validator.isCpf(cpf) ) {
            return false;
        }
        
        // verificar data de nascimento
        if ( ! Validator.isDataNascimento(dataNascimento) ) {
            return false;
        }
        
        // verificar email
        if ( ! Validator.isEmail(email) ) {
            return false;
        }
        
        // verificar telefone
        if (telefone == null || telefone.isBlank() || telefone.length() > 20) {
            return false;
        }
        
        PacienteSexo enumSexo = PacienteSexo.fromDescricao(sexo.trim().toUpperCase());
        
        Paciente paciente = new Paciente();
        paciente.setNome(nome);
        paciente.setCpf(cpf);
        paciente.setDataNascimento(dataNascimento);
        paciente.setSexo(enumSexo);
        paciente.setStatus(Status.fromDescricao( STATUS_DEFAULT ) );
        paciente.setTelefone(telefone);
        paciente.setEmail(email);
        
        Endereco endereco = new EnderecoServiceImpl( DAOFactory.criarEnderecoDAO() ).buscarEnderecoPorId(idEndereco);
        paciente.setEndereco(endereco);
        
        return pacienteDAO.insert(paciente) > 0;
    }

    @Override
    public boolean atualizarPaciente(int id, String nome, String cpf, LocalDate dataNascimento, String sexo, String status, String telefone, String email, int idEndereco) {
        
        // verificar nome
        if (nome == null || nome.trim().isBlank() || nome.trim().length() > 255) return false;
        
        // verificar cpf
        if ( ! Validator.isCpf(cpf) ) return false;
        
        // verificar data de nascimento
        if ( ! Validator.isDataNascimento(dataNascimento) ) return false;
        
        // verificar email
        if ( ! Validator.isEmail(email) ) return false;
        
        // verificar telefone
        if (telefone == null || telefone.trim().isBlank() || telefone.trim().length() > 20) return false;
        
        PacienteSexo enumSexo = PacienteSexo.fromDescricao(sexo.trim().toUpperCase());
        
        Paciente paciente = new Paciente();
        paciente.setNome(nome);
        paciente.setCpf(cpf);
        paciente.setDataNascimento(dataNascimento);
        paciente.setSexo(enumSexo);
        paciente.setStatus(Status.fromDescricao( status.trim().toUpperCase() ) );
        paciente.setTelefone(telefone);
        paciente.setEmail(email);
        
        Endereco endereco = new EnderecoServiceImpl( DAOFactory.criarEnderecoDAO() ).buscarEnderecoPorId(idEndereco);
        paciente.setEndereco(endereco);
        
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

    @Override
    public Paciente buscarPacientePorCpf(String cpf) {
        
        // verificar cpf
        if ( ! Validator.isCpf(cpf) ) return null;
        
        return pacienteDAO.selectCpf(cpf);
        
    }

    @Override
    public List<Paciente> buscarPacientePorTelefone(String telefone) {
        
        List<Paciente> listaPacientes = new ArrayList<>();
        // verificar telefone
        if (telefone == null || telefone.trim().isBlank() || telefone.trim().length() > 20) return listaPacientes;
        
        return pacienteDAO.selectTelefone(telefone);
        
    }

    @Override
    public List<Paciente> buscarPacientePorSexo(String sexo) {
        
        PacienteSexo enumSexo = PacienteSexo.fromDescricao(sexo.trim().toUpperCase());
        
        return pacienteDAO.selectSexo(enumSexo);
    }

    @Override
    public List<Paciente> buscarPacientePorStatus(String status) {
        
        Status enumStatus = Status.fromDescricao( status.trim().toUpperCase() );
        
        return pacienteDAO.selectStatus(enumStatus);
        
    }
    
    
}
