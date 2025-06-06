/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.util.validator;

/**
 *
 * @author higor
 */
public class Validator implements Validatable {

    @Override
    public boolean isCpf(String cpf) {
        if (cpf.trim().isBlank() || (cpf.trim().length() != 14 && cpf.trim().length() != 11)) return false;
        
        if (cpf.contains(".") || cpf.contains("-")) {
            cpf = cpf.replace(".", "").replace("-", "");
        }
        
        
        char[] arrCpf = cpf.toCharArray();
        int primeiroDigito = 0;
        int j = 10;
        
        for (int i=0; i<9; i++) {
            primeiroDigito += Character.getNumericValue(arrCpf[i]) * j--;
        }
        primeiroDigito = (primeiroDigito * 10) % 11;
        
        if (primeiroDigito != Character.getNumericValue(arrCpf[9])) return false;
        
        int segundoDigito = 0;
        j = 11;
        for (int i=0; i<10; i++) {
            segundoDigito += Character.getNumericValue(arrCpf[i]) * j--;
        }
        segundoDigito = (segundoDigito * 10) % 11;
        
        if (segundoDigito != Character.getNumericValue(arrCpf[10])) return false;
        
        char charInicial = arrCpf[0];
        for (int i=1; i<arrCpf.length; i++) {
            if (charInicial != arrCpf[i]) return true;
        }
        
        return false;
    }

    @Override
    public boolean isEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
