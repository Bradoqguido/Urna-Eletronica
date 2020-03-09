package br.edu.urna.models;

public class Categoria {

    private String categoria = "";
    private String nome = "";
    private int codigo = 0;
    private int voto = 0;

    public Categoria() {}

    public Categoria(String categoria, int codigo, String nome) {
        this.categoria = categoria;
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void updateVoto() {
        this.voto++;
    }

    public int getVotos() {
        return this.voto;
    }

}