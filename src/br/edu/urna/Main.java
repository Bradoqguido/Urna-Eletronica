package br.edu.urna;

import br.edu.urna.components.ControleForm;
import br.edu.urna.controls.Controle;

import java.util.Scanner;

public class Main {

    private static Scanner aux = new Scanner(System.in);
    private static Controle controller = new Controle();

    public static void main(String[] args) {
        new ControleForm();
        // Desenvolvido por Jeferson Eduardo Guido
        // Curso de Engenharia de software na FAG
    }
}
