/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Especialidade;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Medico;

/**
 *
 * @author higor
 */
public class MedicoDAOJDBC implements MedicoDAO {

    @Override
    public int insert(Medico medico) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("INSERT INTO medico (nome, crm, id_especialidade, telefone, email) ")
                .append("VALUES (?, ?, ?, ?, ?)");
        String insert = sqlBuilder.toString();
        
        int line = 0;
        try {
            
            line = DAOGenerico.executarComando(insert, medico.getNome(),
                                                        medico.getCrm(),
                                                        medico.getEspecialidade().getId(),
                                                        medico.getTelefone(),
                                                        medico.getEmail());
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return line;
    }

    @Override
    public int update(Medico medico) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("UPDATE medico SET ")
                .append("nome = ?, ")
                .append("crm = ?, ")
                .append("id_especialidade = ?, ")
                .append("telefone = ?, ")
                .append("email = ?, ")
                .append("WHERE id = ?");
        String update = sqlBuilder.toString();
        
        int line = 0;
        try {
            
            line = DAOGenerico.executarComando(update, medico.getNome(),
                                                        medico.getCrm(),
                                                        medico.getEspecialidade().getId(),
                                                        medico.getTelefone(),
                                                        medico.getEmail(),
                                                        medico.getId());
        
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
    public List<Medico> selectAll() {
        ResultSet rset;
        String select = "SELECT * FROM medico_especialidade ORDER BY id_medico ASC";
        List<Medico> listaMedico = new ArrayList<>();
        try {
            rset = DAOGenerico.executarConsulta(select);
            while(rset.next()) {
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
                
                listaMedico.add(medico);
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return listaMedico;
        
    }

    @Override
    public Medico selectId(int id) {
        ResultSet rset;
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("SELECT * FROM medico_especialidade ")
                .append("WHERE id = ?");
        String select = sqlBuilder.toString();
        
        Medico medico = new Medico();
        Especialidade especialidade = new Especialidade();
        
        try {
            rset = DAOGenerico.executarConsulta(select);
            while(rset.next()) {
                medico.setId( rset.getInt("id_medico") );
                medico.setNome( rset.getString("nome_medico") );
                medico.setCrm( rset.getString("crm_medico") );

                especialidade.setId( rset.getInt("id_especialidade") );
                especialidade.setDescricao( rset.getString("descricao_especialidade") );
                medico.setEspecialidade(especialidade);
                
                medico.setTelefone( rset.getString("telefone_medico") );
                medico.setEmail( rset.getString("email_medico") );
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return medico;
    }
    
}
