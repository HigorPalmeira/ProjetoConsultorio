/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Consulta;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Endereco;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Especialidade;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Medico;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Paciente;
import main.java.higorpalmeira.com.github.consultorio.model.enums.ConsultaStatus;
import main.java.higorpalmeira.com.github.consultorio.model.enums.PacienteSexo;
import main.java.higorpalmeira.com.github.consultorio.model.enums.Status;

/**
 *
 * @author higor
 */
public class ConsultaDAOJDBC implements ConsultaDAO {

    @Override
    public int insert(Consulta consulta) {
        
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("INSERT INTO consulta(id_medico, id_paciente, data_hora, observacoes) ")
                .append("VALUES (?, ?, ?, ?)");
        String insert = sqlBuilder.toString();
        
        int line = 0;
        try {
            
            line = DAOGenerico.executarComando(insert, consulta.getMedico().getId(),
                                                        consulta.getPaciente().getId(),
                                                        Timestamp.valueOf(consulta.getDataHorario()),
                                                        consulta.getObservacoes());
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return line;
    }

    @Override
    public int update(Consulta consulta) {
        
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("UPDATE consulta SET ")
                .append("id_medico = ?, ")
                .append("id_paciente = ?, ")
                .append("data_hora = ?, ")
                .append("observacoes = ?, ")
                .append("status = ? ")
                .append("WHERE id = ?");
        String update = sqlBuilder.toString();
        
        int line = 0;
        try {
            
            line = DAOGenerico.executarComando(update, consulta.getMedico().getId(),
                    consulta.getPaciente().getId(),
                    Timestamp.valueOf(consulta.getDataHorario()),
                    consulta.getObservacoes(),
                    consulta.getStatus().getDescricao(),
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
        String select = "SELECT * FROM consulta_detalhada ORDER BY id_consulta ASC";
        List<Consulta> listaConsultas = new ArrayList<>();
        
        try {
            
            rset = DAOGenerico.executarConsulta(select);
            while(rset.next()) {
                Consulta consulta = new Consulta();
                consulta.setId( rset.getInt("id_consulta") );
                consulta.setDataHorario( rset.getTimestamp("data_hora_consulta").toLocalDateTime() );
                consulta.setObservacoes( rset.getString("observacoes_consulta") );
                consulta.setStatus( ConsultaStatus.fromDescricao( rset.getString("status_consulta") ) );
                
                Medico medico = new Medico();
                medico.setId( rset.getInt("id_medico") );
                medico.setNome( rset.getString("nome_medico") );
                medico.setCrm( rset.getString("crm_medico") );
                medico.setStatus(Status.fromDescricao( rset.getString("status_medico") ));
                medico.setTelefone( rset.getString("telefone_medico") );
                medico.setEmail( rset.getString("email_medico") );
                
                Especialidade especialidade = new Especialidade();
                especialidade.setId( rset.getInt("id_especialidade") );
                especialidade.setDescricao( rset.getString("descricao_especialidade") );
                medico.setEspecialidade(especialidade);
                
                Endereco edrMedico = new Endereco(rset.getString("rua_endereco_medico"), rset.getString("numero_endereco_medico"), rset.getString("bairro_endereco_medico"), rset.getString("cidade_endereco_medico"), rset.getString("estado_endereco_medico"), rset.getString("cep_endereco_medico"));
                medico.setEndereco(edrMedico);
                
                consulta.setMedico(medico);
                
                Paciente paciente = new Paciente();
                paciente.setId( rset.getInt("id_paciente") );
                paciente.setNome( rset.getString("nome_paciente") );
                paciente.setCpf( rset.getString("cpf_paciente") );
                paciente.setDataNascimento( rset.getDate("data_nascimento_paciente").toLocalDate() );
                paciente.setSexo( PacienteSexo.fromDescricao( rset.getString("sexo_paciente") ) );
                paciente.setStatus(Status.fromDescricao( rset.getString("status_paciente") ));
                paciente.setTelefone( rset.getString("telefone_paciente") );
                paciente.setEmail( rset.getString("email_paciente") );
                
                Endereco edrPaciente = new Endereco(rset.getString("rua_endereco_paciente"), rset.getString("numero_endereco_paciente"), rset.getString("bairro_endereco_paciente"), rset.getString("cidade_endereco_paciente"), rset.getString("estado_endereco_paciente"), rset.getString("cep_endereco_paciente"));
                edrPaciente.setId( rset.getInt("id_endereco_paciente") );
                paciente.setEndereco(edrPaciente);
                
                consulta.setPaciente(paciente);
                
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
                .append("SELECT * FROM consulta_detalhada ")
                .append("WHERE id_consulta = ?");
        String select = sqlBuilder.toString();
        
        Consulta consulta = new Consulta();
        try {
            
            rset = DAOGenerico.executarConsulta(select, id);
            while(rset.next()) {
                consulta.setId( rset.getInt("id_consulta") );
                consulta.setDataHorario( rset.getTimestamp("data_hora_consulta").toLocalDateTime() );
                consulta.setObservacoes( rset.getString("observacoes_consulta") );
                consulta.setStatus( ConsultaStatus.fromDescricao( rset.getString("status_consulta") ) );
                
                Medico medico = new Medico();
                medico.setId( rset.getInt("id_medico") );
                medico.setNome( rset.getString("nome_medico") );
                medico.setCrm( rset.getString("crm_medico") );
                medico.setStatus(Status.fromDescricao( rset.getString("status_medico") ));
                medico.setTelefone( rset.getString("telefone_medico") );
                medico.setEmail( rset.getString("email_medico") );
                
                Especialidade especialidade = new Especialidade();
                especialidade.setId( rset.getInt("id_especialidade") );
                especialidade.setDescricao( rset.getString("descricao_especialidade") );
                medico.setEspecialidade(especialidade);
                
                Endereco edrMedico = new Endereco(rset.getString("rua_endereco_medico"), rset.getString("numero_endereco_medico"), rset.getString("bairro_endereco_medico"), rset.getString("cidade_endereco_medico"), rset.getString("estado_endereco_medico"), rset.getString("cep_endereco_medico"));
                medico.setEndereco(edrMedico);
                
                consulta.setMedico(medico);
                
                Paciente paciente = new Paciente();
                paciente.setId( rset.getInt("id_paciente") );
                paciente.setNome( rset.getString("nome_paciente") );
                paciente.setCpf( rset.getString("cpf_paciente") );
                paciente.setDataNascimento( rset.getDate("data_nascimento_paciente").toLocalDate() );
                paciente.setSexo( PacienteSexo.fromDescricao( rset.getString("sexo_paciente") ) );
                paciente.setStatus(Status.fromDescricao( rset.getString("status_paciente") ));
                paciente.setTelefone( rset.getString("telefone_paciente") );
                paciente.setEmail( rset.getString("email_paciente") );
                
                Endereco edrPaciente = new Endereco(rset.getString("rua_endereco_paciente"), rset.getString("numero_endereco_paciente"), rset.getString("bairro_endereco_paciente"), rset.getString("cidade_endereco_paciente"), rset.getString("estado_endereco_paciente"), rset.getString("cep_endereco_paciente"));
                edrPaciente.setId( rset.getInt("id_endereco_paciente") );
                paciente.setEndereco(edrPaciente);
                
                consulta.setPaciente(paciente);
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return consulta;
        
    }
    
    
    
}
