/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.enums;

/**
 *
 * @author higor
 */
public enum Status {
    
    ATIVO("ATIVO"),
    INATIVO("INATIVO");
    
    private final String descricao;
    
    Status(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    
    public static Status fromDescricao(String descricao) {
        for (Status status : Status.values()) {
            if (status.descricao.equalsIgnoreCase(descricao)) {
                return status;
            }
        }
        
        throw new IllegalArgumentException("Status do paciente inválido: " + descricao);
    }
    
}
