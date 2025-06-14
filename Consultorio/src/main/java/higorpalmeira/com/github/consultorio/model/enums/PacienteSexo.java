/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.enums;

/**
 *
 * @author higor
 */
public enum PacienteSexo {
    
    FEMININO("FEMININO"),
    MASCULINO("MASCULINO"),
    NAODEFINIDO("NÃO DEFINIDO");
    
    private final String descricao;
    
    PacienteSexo(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    
    public static PacienteSexo fromDescricao(String descricao) {
        for (PacienteSexo sexo : PacienteSexo.values()) {
            if (sexo.descricao.equalsIgnoreCase(descricao)) {
                return sexo;
            }
        }
        
        throw new IllegalArgumentException("Sexo do paciente inválido: " + descricao);
    }
    
}
