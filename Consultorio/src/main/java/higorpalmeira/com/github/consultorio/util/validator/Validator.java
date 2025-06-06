/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author higor
 */
public class Validator {
    
    private static final String EMAIL_REGEX = 
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isCpf(final String cpf) 
    {
        if (cpf.trim().isBlank() || (cpf.trim().length() != 14 && cpf.trim().length() != 11)) return false;
        
        String cpfAlter = cpf;
        if (cpf.contains(".") || cpf.contains("-")) {
            cpfAlter = cpf.replace(".", "").replace("-", "");
        }
        
        
        char[] arrCpf = cpfAlter.toCharArray();
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

    public static boolean isEmail(final String email) {
        
        if (email == null) return false;
        
        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
        
    }
    
}
