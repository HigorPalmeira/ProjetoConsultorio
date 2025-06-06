/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.dao;

import java.sql.*;
import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Paciente;

/**
 *
 * @author higor
 */
public class PacienteDAOJDBC implements PacienteDAO{

    @Override
    public int insert(Paciente paciente) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("INSERT INTO paciente (nome, cpf, data_nascimento, telefone, email) ")
                .append("VALUES (?, ?, ?, ?, ?)");
        String insert = sqlBuilder.toString();
        int line = 0;
        try {
            line = DAOGenerico.executarComando(insert, paciente.getNome(),
                                                        paciente.getCpf(),
                                                        paciente.getDataNascimento(),
                                                        paciente.getTelefone(),
                                                        paciente.getEmail());
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return line;
    }

    @Override
    public int update(Paciente paciente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int id) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Paciente> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Paciente selectId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
