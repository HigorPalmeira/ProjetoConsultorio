/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.dao;

import com.mysql.cj.jdbc.CallableStatement;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

/**
 *
 * @author higor
 */
public class DAOGenerico {
    
    private static final String CONFIG_FILE = "/main/resources/config/database.properties";
    
    public static Connection getConexao() throws SQLException, ClassNotFoundException {
        
        Connection conexao = null;
        Properties props = new Properties();
        
        try (InputStream input = DAOGenerico.class.getResourceAsStream(CONFIG_FILE)) {
            
            if (input == null) {
                System.err.println("Erro: Arquivo de configuração " + CONFIG_FILE + " não encontrado.");
                return null;
            }
            
            props.load(input);
            
            String URL = props.getProperty("db.url");
            String USUARIO = props.getProperty("db.username");
            String SENHA = props.getProperty("db.password");
            String DRIVER = props.getProperty("db.driver");
            
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de configuração: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            if (e.getSQLState().equals("28000")) {
                System.err.println("Usuário ou senha incorretos.");
            }
        }

        return conexao;
    }
    
    public static int executarComando(String query, Object... params) throws SQLException, ClassNotFoundException {
        PreparedStatement sql = (PreparedStatement)  getConexao().prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            sql.setObject(i+1,params[i]);
        }
        int result = sql.executeUpdate();
        sql.close();
        return result;
    }
     
    public static ResultSet executarConsulta(String query, Object... params) throws SQLException, ClassNotFoundException {
        PreparedStatement sql = (PreparedStatement)  getConexao().prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            sql.setObject(i+1,params[i]);
        }
        return sql.executeQuery();
    }
    
    public static int executarStoredProcedure(String query, Object... params) throws SQLException, ClassNotFoundException {
        CallableStatement sql = (CallableStatement) getConexao().prepareCall(query);
        
        int i;
        for (i = 0; i < params.length; i++) {
            sql.setObject(i+1, params[i]);
        }
        sql.registerOutParameter(i+1, Types.INTEGER);
        
        sql.execute();
        
        return sql.getInt(i+1);
    }
}
