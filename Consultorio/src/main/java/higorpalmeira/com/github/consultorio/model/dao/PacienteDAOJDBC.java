/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Endereco;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Paciente;
import main.java.higorpalmeira.com.github.consultorio.model.enums.PacienteSexo;
import main.java.higorpalmeira.com.github.consultorio.model.enums.Status;

/**
 *
 * @author higor
 */
public class PacienteDAOJDBC implements PacienteDAO {

    @Override
    public int insert(Paciente paciente) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("INSERT INTO paciente (nome, cpf, data_nascimento, sexo, telefone, email, id_endereco) ")
                .append("VALUES (?, ?, ?, ?, ?, ?, ?)");
        String insert = sqlBuilder.toString();
        int line = 0;
        try {
            line = DAOGenerico.executarComando(insert, paciente.getNome(),
                    paciente.getCpf(),
                    Date.valueOf( paciente.getDataNascimento() ),
                    paciente.getSexo().getDescricao(),
                    paciente.getTelefone(),
                    paciente.getEmail(),
                    paciente.getEndereco().getId());
        } catch (Exception e) {
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
                .append("sexo = ?, ")
                .append("status = ?, ")
                .append("telefone = ?, ")
                .append("email = ?, ")
                .append("id_endereco = ? ")
                .append("WHERE id = ?");
        String update = sqlBuilder.toString();

        int line = 0;
        try {

            line = DAOGenerico.executarComando(update, paciente.getNome(),
                    paciente.getCpf(),
                    Date.valueOf( paciente.getDataNascimento() ),
                    paciente.getSexo().getDescricao(),
                    paciente.getStatus().getDescricao(),
                    paciente.getTelefone(),
                    paciente.getEmail(),
                    paciente.getEndereco().getId(),
                    paciente.getId());

        } catch (Exception e) {
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
        String select = "SELECT * FROM paciente_detalhado ORDER BY id_paciente ASC";
        List<Paciente> listaPacientes = new ArrayList<>();
        try {

            rset = DAOGenerico.executarConsulta(select);
            while (rset.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rset.getInt("id_paciente"));
                paciente.setNome(rset.getString("nome_paciente"));
                paciente.setDataNascimento(rset.getDate("data_nascimento_paciente").toLocalDate());
                paciente.setCpf(rset.getString("cpf_paciente"));
                paciente.setTelefone(rset.getString("telefone_paciente"));
                paciente.setEmail(rset.getString("email_paciente"));
                paciente.setSexo( PacienteSexo.fromDescricao( rset.getString("sexo_paciente") ) );
                paciente.setStatus(Status.fromDescricao( rset.getString("status_paciente") ) );

                Endereco endereco = new Endereco(rset.getString("rua_endereco"),
                        rset.getString("numero_endereco"),
                        rset.getString("bairro_endereco"),
                        rset.getString("cidade_endereco"),
                        rset.getString("estado_endereco"),
                        rset.getString("cep_endereco"));
                endereco.setId(rset.getInt("id_endereco"));
                paciente.setEndereco(endereco);

                listaPacientes.add(paciente);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaPacientes;
    }

    @Override
    public Paciente selectId(int id) {
        ResultSet rset;
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("SELECT * FROM paciente_detalhado ")
                .append("WHERE id_paciente = ?");
        String select = sqlBuilder.toString();
        Paciente paciente = new Paciente();
        try {

            rset = DAOGenerico.executarConsulta(select, id);
            while (rset.next()) {
                paciente.setId(rset.getInt("id_paciente"));
                paciente.setNome(rset.getString("nome_paciente"));
                paciente.setDataNascimento(rset.getDate("data_nascimento_paciente").toLocalDate());
                paciente.setCpf(rset.getString("cpf_paciente"));
                paciente.setTelefone(rset.getString("telefone_paciente"));
                paciente.setEmail(rset.getString("email_paciente"));
                paciente.setSexo( PacienteSexo.fromDescricao( rset.getString("sexo_paciente") ) );
                paciente.setStatus(Status.fromDescricao( rset.getString("status_paciente") ) );

                Endereco endereco = new Endereco(rset.getString("rua_endereco"),
                        rset.getString("numero_endereco"),
                        rset.getString("bairro_endereco"),
                        rset.getString("cidade_endereco"),
                        rset.getString("estado_endereco"),
                        rset.getString("cep_endereco"));
                endereco.setId(rset.getInt("id_endereco"));
                paciente.setEndereco(endereco);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return paciente;
    }

}
