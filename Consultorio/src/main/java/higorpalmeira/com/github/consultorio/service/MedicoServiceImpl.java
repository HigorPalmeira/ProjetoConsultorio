/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.higorpalmeira.com.github.consultorio.model.dao.DAOFactory;
import main.java.higorpalmeira.com.github.consultorio.model.dao.MedicoDAO;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Especialidade;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Medico;
import main.java.higorpalmeira.com.github.consultorio.model.enums.Status;
import main.java.higorpalmeira.com.github.consultorio.util.validator.Validator;

/**
 *
 * @author higor
 */
public class MedicoServiceImpl implements IMedicoService {
    
    private final MedicoDAO medicoDAO;
    
    public MedicoServiceImpl(MedicoDAO medicoDAO) {
        this.medicoDAO = medicoDAO;
    }
    
    @Override
    public boolean loginMedico(String usuario, String senha) {
        
        if (usuario == null || usuario.trim().isBlank() || usuario.trim().length() > 255) {
            return false;
        }
        
        if ( ! Validator.isCrm(senha)) {
            return false;
        }
        
        Medico medico = this.medicoDAO.selectCrm(senha);
        
        return !(medico == null 
                || ( medico.getId() == 0 && medico.getCrm() == null ) 
                || !medico.getEmail().equals(usuario));
        
    }

    @Override
    public boolean criarMedico(String nome, String crm, int idEspecialidade, String telefone, String email) {
        
        // verificar nome
        if (nome == null || nome.trim().isBlank() || nome.trim().length() > 255) {
            return false;
        }
        
        if ( ! Validator.isCrm(crm) ) {
            return false;
        }
        
        // verificar telefone
        if (telefone == null || telefone.isBlank() || telefone.length() > 20) {
            return false;
        }
        
        // verificar email
        if ( ! Validator.isEmail(email) ) {
            return false;
        }
        
        Especialidade especialidade = new EspecialidadeServiceImpl(DAOFactory.criarEspecialidadeDAO()).buscarEspecialidadePorId(idEspecialidade);
        
        if (especialidade == null || (especialidade.getId() == 0 && especialidade.getDescricao() == null)) {
            return false;
        }
        
        Medico medico = new Medico();
        medico.setNome(nome);
        medico.setCrm(crm);
        medico.setEspecialidade(especialidade);
        medico.setTelefone(telefone);
        medico.setEmail(email);
        
        return this.medicoDAO.insert(medico) > 0;
        
    }

    @Override
    public boolean atualizarMedico(int id, String nome, String crm, int idEspecialidade, String status, String telefone, String email) {
        
        if (id < 0) {
            return false;
        }
        
        // verificar nome
        if (nome == null || nome.trim().isBlank() || nome.trim().length() > 255) {
            return false;
        }
        
        if ( ! Validator.isCrm(crm) ) {
            return false;
        }
        
        // verificar telefone
        if (telefone == null || telefone.isBlank() || telefone.length() > 20) {
            return false;
        }
        
        // verificar email
        if ( ! Validator.isEmail(email) ) {
            return false;
        }
        
        Especialidade especialidade = new EspecialidadeServiceImpl(DAOFactory.criarEspecialidadeDAO())
                                                                .buscarEspecialidadePorId(idEspecialidade);
        
        if (especialidade == null || (especialidade.getId() == 0 && especialidade.getDescricao() == null)) {
            return false;
        }
        
        Status enumStatus = Status.fromDescricao(status.trim().toUpperCase());
        
        Medico medico = new Medico();
        medico.setId(id);
        medico.setNome(nome);
        medico.setCrm(crm);
        medico.setEspecialidade(especialidade);
        medico.setTelefone(telefone);
        medico.setEmail(email);
        medico.setStatus(enumStatus);
        
        return medicoDAO.update(medico) > 0;
        
    }

    @Override
    public boolean deletarMedico(int id) {
        
        if (id < 0) {
            return false;
        }
        
        boolean deletado = false;
        
        try {
            deletado = medicoDAO.delete(id) > 0;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MedicoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return deletado;
        
    }

    @Override
    public List<Medico> listarTodosMedicos() {
        
        return medicoDAO.selectAll();
        
    }

    @Override
    public Medico buscarMedicoPorId(int id) {
        
        return medicoDAO.selectId(id);
        
    }

    @Override
    public Medico buscarMedicoPorCrm(String crm) {
        
        Medico medico = new Medico();
        
        if ( ! Validator.isCrm(crm) ) {
            return medico;
        }
        
        medico = medicoDAO.selectCrm(crm);
        
        return medico;
        
    }

    @Override
    public List<Medico> buscarMedicoPorEspecialidade(int idEspecialidade) {
        
        List<Medico> listaMedicos = new ArrayList<>();
        
        if (idEspecialidade < 0) return listaMedicos;
        
        listaMedicos = medicoDAO.selectEspecialidade(idEspecialidade);
        
        return listaMedicos;
        
    }

    @Override
    public List<Medico> buscarMedicoPorStatus(String status) {
        
        Status enumStatus = Status.fromDescricao(status.trim().toUpperCase());
        
        return medicoDAO.selectStatus(enumStatus);
        
    }

    @Override
    public List<Medico> buscarMedicoPorTelefone(String telefone) {
        
        List<Medico> listaMedicos = new ArrayList<>();
        
        // verificar telefone
        if (telefone == null || telefone.isBlank() || telefone.length() > 20) {
            return listaMedicos;
        }
        
        listaMedicos = medicoDAO.selectTelefone(telefone.trim());
        
        return listaMedicos;
        
    }
    
    
    
}
