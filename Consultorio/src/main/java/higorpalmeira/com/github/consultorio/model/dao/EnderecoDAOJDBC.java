/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Endereco;

/**
 *
 * @author higor
 */
public class EnderecoDAOJDBC implements EnderecoDAO {

    @Override
    public int insert(Endereco endereco) {
        
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("INSERT INTO endereco (rua, numero, bairro, cidade, estado, cep) ")
                .append("VALUES (?, ?, ?, ?, ?, ?)");
        String insert = sqlBuilder.toString();
        
        int line = 0;
        try {
            
            line = DAOGenerico.executarComando(insert, endereco.getRua(),
                    endereco.getNumero(),
                    endereco.getBairro(),
                    endereco.getCidade(),
                    endereco.getEstado(),
                    endereco.getCep());
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return line;
        
    }

    @Override
    public int update(Endereco endereco) {
        
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("UPDATE endereco SET ")
                .append("rua = ?, ")
                .append("numero = ?, ")
                .append("bairro = ?, ")
                .append("cidade = ?, ")
                .append("estado = ?, ")
                .append("cep = ? ")
                .append("WHERE id = ?");
        String update = sqlBuilder.toString();
        
        int line = 0;
        try {
            
            line = DAOGenerico.executarComando(update, endereco.getRua(),
                    endereco.getNumero(),
                    endereco.getBairro(),
                    endereco.getCidade(),
                    endereco.getEstado(),
                    endereco.getCep(),
                    endereco.getId());
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return line;
        
    }

    @Override
    public int delete(int id) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException {
        
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("DELETE FROM endereco ")
                .append("WHERE id = ?");
        String delete = sqlBuilder.toString();
        
        int line = 0;
        line = DAOGenerico.executarComando(delete, id);
        return line;
        
    }

    @Override
    public List<Endereco> selectAll() {
        
        ResultSet rset;
        String select = "SELECT * FROM endereco ORDER BY id ASC";
        List<Endereco> listaEnderecos = new ArrayList<>();
        
        try {
            
            rset = DAOGenerico.executarConsulta(select);
            while(rset.next()) {
                Endereco endereco = new Endereco( 
                                    rset.getString("rua"),
                                    rset.getString("numero"),
                                    rset.getString("bairro"),
                                    rset.getString("cidade"),
                                    rset.getString("estado"),
                                    rset.getString("cep")
                );
                endereco.setId( rset.getInt("id") );
                
                listaEnderecos.add(endereco);
            }
            
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return listaEnderecos;
        
    }

    @Override
    public Endereco selectById(int id) {
        
        ResultSet rset;
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("SELECT * FROM endereco ")
                .append("WHERE id = ?");
        String select = sqlBuilder.toString();
        
        Endereco endereco = null;
        
        try {
            
            rset = DAOGenerico.executarConsulta(select, id);
            while(rset.next()) {
                endereco = new Endereco(
                                    rset.getString("rua"),
                                    rset.getString("numero"),
                                    rset.getString("bairro"),
                                    rset.getString("cidade"),
                                    rset.getString("estado"),
                                    rset.getString("cep")
                );
                endereco.setId( rset.getInt("id") );
            }
            
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return endereco;
        
    }
    
    
}
