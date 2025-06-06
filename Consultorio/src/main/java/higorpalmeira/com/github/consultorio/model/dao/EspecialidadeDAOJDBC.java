/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Especialidade;

/**
 *
 * @author higor
 */
public class EspecialidadeDAOJDBC implements EspecialidadeDAO {

    @Override
    public int insert(Especialidade especialidade) {
        
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("INSERT INTO especialidade(descricao) ")
                .append("VALUES (?)");
        
        String insert = sqlBuilder.toString();
        int line = 0;
        try {
            line = DAOGenerico.executarComando(insert, especialidade.getDescricao());
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return line;
    }

    @Override
    public int update(Especialidade especialidade) {
        
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("UPDATE especialidade SET ")
                .append("descricao = ? ")
                .append("WHERE id = ?");
        
        String update = sqlBuilder.toString();
        
        int line = 0;
        try {
            line = DAOGenerico.executarComando(update, especialidade.getDescricao(),
                                                        especialidade.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return line;
        
    }

    @Override
    public int delete(int id) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException {
        
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("DELETE FROM especialidade ")
                .append("WHERE id = ?");
        
        String delete = sqlBuilder.toString();
        int line = 0;
        line = DAOGenerico.executarComando(delete, id);
        return line;
        
    }

    @Override
    public List<Especialidade> selectAll() {
        
        ResultSet rset;
        String select = "SELECT * FROM especialidade ORDER BY id ASC";
        List<Especialidade> listaEspecialidade = new ArrayList<>();
        try {
            rset = DAOGenerico.executarConsulta(select);
            while(rset.next()) {
                Especialidade especialidade = new Especialidade();
                especialidade.setId(rset.getInt("id"));
                especialidade.setDescricao(rset.getString("descricao"));
                listaEspecialidade.add(especialidade);
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return listaEspecialidade;
        
    }

    @Override
    public Especialidade selectId(int id) {
        
        ResultSet rset;
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("SELECT * FROM especialidade ")
                .append("WHERE id = ?");
        String select = sqlBuilder.toString();
        
        Especialidade especialidade = new Especialidade();
        try {
            rset = DAOGenerico.executarConsulta(select, id);
            
            while(rset.next()) {
                especialidade.setId(rset.getInt("id"));
                especialidade.setDescricao(rset.getString("descricao"));
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return especialidade;
        
    }
    
    
    
}
