package com.br.praticatestesunitarios.calculadora;

public class ExecutaCalculadora {
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        System.out.println("Resultado: " + calculadora.soma(10.5f, 5.5f));
    }
}
