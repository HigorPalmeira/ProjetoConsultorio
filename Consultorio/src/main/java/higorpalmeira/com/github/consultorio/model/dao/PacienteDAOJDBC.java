/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.dao;

import java.sql.*;
import java.util.ArrayList;
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
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("UPDATE paciente SET ")
                .append("nome = ?, ")
                .append("cpf = ?, ")
                .append("data_nascimento = ?, ")
                .append("telefone = ?, ")
                .append("email = ?, ")
                .append("WHERE id = ?");
        String update = sqlBuilder.toString();
        
        int line = 0;
        try {
            
            line = DAOGenerico.executarComando(update, paciente.getNome(),
                                                        paciente.getCpf(),
                                                        paciente.getDataNascimento(),
                                                        paciente.getTelefone(),
                                                        paciente.getEmail(),
                                                        paciente.getId());
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return line;
    }

    @Override
    public int delete(int id) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("DELETE FROM paciente ")
                .append("WHERE id = ?");
        String delete = sqlBuilder.toString();
        
        int line = 0;
        line = DAOGenerico.executarComando(delete, id);
        return line;
    }

    @Override
    public List<Paciente> selectAll() {
         ResultSet rset;
         String select = "SELECT * FROM paciente ORDER BY id ASC";
         List<Paciente> listaPacientes = new ArrayList<>();
         try {
             
             rset = DAOGenerico.executarConsulta(select);
             while(rset.next()) {
                 Paciente paciente = new Paciente();
                 paciente.setId( rset.getInt("id") );
                 paciente.setNome( rset.getString("nome") );
                 paciente.setDataNascimento( rset.getDate("data_nascimento").toLocalDate() );
                 paciente.setCpf( rset.getString("cpf") );
                 paciente.setTelefone( rset.getString("telefone") );
                 paciente.setEmail( rset.getString("email") );
                 
                 listaPacientes.add(paciente);
             }
             
         } catch(Exception e) {
             e.printStackTrace();
         }
         
         return listaPacientes;
    }

    @Override
    public Paciente selectId(int id) {
        ResultSet rset;
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("SELECT * FROM paciente ")
                .append("WHERE id = ?");
        String select = sqlBuilder.toString();
        Paciente paciente = new Paciente();
        try {
            
            rset = DAOGenerico.executarConsulta(select, id);
            while(rset.next()) {
                paciente.setId( rset.getInt("id") );
                paciente.setNome( rset.getString("nome") );
                paciente.setDataNascimento( rset.getDate("data_nascimento").toLocalDate() );
                paciente.setCpf( rset.getString("cpf") );
                paciente.setTelefone( rset.getString("telefone") );
                paciente.setEmail( rset.getString("email") );
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return paciente;
    }
    
}
