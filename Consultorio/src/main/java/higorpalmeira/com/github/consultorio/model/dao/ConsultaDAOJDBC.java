/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Consulta;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Especialidade;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Medico;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Paciente;

/**
 *
 * @author higor
 */
public class ConsultaDAOJDBC implements ConsultaDAO {

    @Override
    public int insert(Consulta consulta) {
        
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("INSERT INTO consulta(id_medico, id_paciente, data_hora, observacoes, status) ")
                .append("VALUES (?, ?, ?, ?, ?)");
        String insert = sqlBuilder.toString();
        
        int line = 0;
        try {
            
            line = DAOGenerico.executarComando(insert, consulta.getMedico().getId(),
                                                        consulta.getPaciente().getId(),
                                                        Timestamp.valueOf(consulta.getDataHorario()),
                                                        consulta.getObservacoes(), 
                                                        consulta.getStatus());
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return line;
    }

    @Override
    public int update(Consulta consulta) {
        
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("UPDATE medico SET ")
                .append("id_medico = ?, ")
                .append("id_paciente = ?, ")
                .append("data_hora = ?, ")
                .append("observacoes = ?, ")
                .append("status = ?, ")
                .append("WHERE id = ?");
        String update = sqlBuilder.toString();
        
        int line = 0;
        try {
            
            line = DAOGenerico.executarComando(update, consulta.getMedico().getId(),
                    consulta.getPaciente().getId(),
                    Timestamp.valueOf(consulta.getDataHorario()),
                    consulta.getObservacoes(),
                    consulta.getStatus(),
                    consulta.getId());
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return line;
        
    }

    @Override
    public int delete(int id) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException {
        
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("DELETE FROM medico ")
                .append("WHERE id = ?");
        String delete = sqlBuilder.toString();
        
        int line = 0;
        line = DAOGenerico.executarComando(delete, id);
        return line;
    }

    @Override
    public List<Consulta> selectAll() {
        ResultSet rset;
        String select = "SELECT * FROM consulta_paciente_medico ORDER BY id_consulta ASC";
        List<Consulta> listaConsultas = new ArrayList<>();
        
        try {
            
            rset = DAOGenerico.executarConsulta(select);
            while(rset.next()) {
                Consulta consulta = new Consulta();
                consulta.setId( rset.getInt("id_consulta") );
                
                Medico medico = new Medico();
                medico.setId( rset.getInt("id_medico") );
                medico.setNome( rset.getString("nome_medico") );
                medico.setCrm( rset.getString("crm_medico") );
                
                Especialidade especialidade = new Especialidade();
                especialidade.setId( rset.getInt("id_especialidade") );
                especialidade.setDescricao( rset.getString("descricao_especialidade") );
                
                medico.setEspecialidade(especialidade);
                medico.setTelefone( rset.getString("telefone_medico") );
                medico.setEmail( rset.getString("email_medico") );
                
                consulta.setMedico(medico);
                
                Paciente paciente = new Paciente();
                paciente.setId( rset.getInt("id_paciente") );
                paciente.setNome( rset.getString("nome_paciente") );
                paciente.setCpf( rset.getString("cpf_paciente") );
                paciente.setDataNascimento( rset.getDate("data_nascimento_paciente").toLocalDate() );
                paciente.setTelefone( rset.getString("telefone_paciente") );
                paciente.setEmail( rset.getString("email_paciente") );
                
                consulta.setPaciente(paciente);
                consulta.setDataHorario( rset.getTimestamp("data_hora_consulta").toLocalDateTime() );
                consulta.setObservacoes( rset.getString("observacoes_consulta") );
                consulta.setStatus( rset.getString("status_consulta") );
                
                listaConsultas.add(consulta);
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return listaConsultas;
    }

    @Override
    public Consulta selectId(int id) {
        ResultSet rset;
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("SELECT * FROM consulta_paciente_medico ")
                .append("WHERE id = ?");
        String select = sqlBuilder.toString();
        
        Consulta consulta = new Consulta();
        try {
            
            rset = DAOGenerico.executarConsulta(select, id);
            while(rset.next()) {
                consulta.setId( rset.getInt("id_consulta") );
                
                Medico medico = new Medico();
                medico.setId( rset.getInt("id_medico") );
                medico.setNome( rset.getString("nome_medico") );
                medico.setCrm( rset.getString("crm_medico") );
                
                Especialidade especialidade = new Especialidade();
                especialidade.setId( rset.getInt("id_especialidade") );
                especialidade.setDescricao( rset.getString("descricao_especialidade") );
                
                medico.setEspecialidade(especialidade);
                medico.setTelefone( rset.getString("telefone_medico") );
                medico.setEmail( rset.getString("email_medico") );
                
                consulta.setMedico(medico);
                
                Paciente paciente = new Paciente();
                paciente.setId( rset.getInt("id_paciente") );
                paciente.setNome( rset.getString("nome_paciente") );
                paciente.setCpf( rset.getString("cpf_paciente") );
                paciente.setDataNascimento( rset.getDate("data_nascimento_paciente").toLocalDate() );
                paciente.setTelefone( rset.getString("telefone_paciente") );
                paciente.setEmail( rset.getString("email_paciente") );
                
                consulta.setPaciente(paciente);
                consulta.setDataHorario( rset.getTimestamp("data_hora_consulta").toLocalDateTime() );
                consulta.setObservacoes( rset.getString("observacoes_consulta") );
                consulta.setStatus( rset.getString("status_consulta") );
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return consulta;
        
    }
    
    
    
}
