package br.edu.urna.controls;

import br.edu.urna.models.Categoria;

import java.util.ArrayList;
import java.util.Scanner;

public class Urna {

    private ArrayList<Categoria> categoriasList = new ArrayList<>();
    private String categoria = "";
    private int votosNulos = 0;
    private Categoria vencedor;

    private Scanner aux = new Scanner(System.in);

    public Urna() {}

    public Urna(String categoria, ArrayList<Categoria> categoriasList) {
        this.categoriasList = categoriasList;
        this.categoria = categoria;
    }

    // votar na categoria selecionada
    // computar votos para a categoria selecionada
    // indicar empate, vencedor e votos nulos
}
