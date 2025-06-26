/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entity;

import java.time.LocalDateTime;
import model.enums.NivelAcesso;

/**
 *
 * @author higor
 */
public class Usuario {
    private int id;
    private String login;
    private String senha;
    private LocalDateTime dataCriacao;
    private NivelAcesso nivelAcesso;
    
    public Usuario() {}
    
    public Usuario(int id, String login, String senha, LocalDateTime dataCriacao, NivelAcesso nivelAcesso) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.dataCriacao = dataCriacao;
        this.nivelAcesso = nivelAcesso;
    }
    
    public Usuario(int id, String login, LocalDateTime dataCriacao, NivelAcesso nivelAcesso) {
        this.id = id;
        this.login = login;
        this.dataCriacao = dataCriacao;
        this.nivelAcesso = nivelAcesso;
    }
    
    public Usuario(int id, String login, String senha, NivelAcesso nivelAcesso) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }
    
    public Usuario(String login, String senha, NivelAcesso nivelAcesso) {
        this.login = login;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public NivelAcesso getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(NivelAcesso nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    @Override
    public String toString() {
        
        String txt = """
                     ===\tUsuario\t===
                     ID: """ + id + "\n"
                    + "Login: " + login + "\n"
                    + "Senha: " + senha + "\n"
                    + "Data de Criação: " + dataCriacao + "\n"
                    + "Nível de Acesso: " + nivelAcesso.getDescricao() + "\n"
                    + "===\t---\t===\n";
        
        return txt;
    }
    
}
