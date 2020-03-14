package br.edu.urna.controls;

import br.edu.urna.models.Categoria;

import java.util.ArrayList;
import java.util.Scanner;

public class Urna {

    private ArrayList<Categoria> categoriasList = new ArrayList<>();
    private ArrayList<Categoria> empatadosList = new ArrayList<>();
    private String categoria = "";
    private int votosNulos = 0, votosBrancos = 0, numEleitores = 0, countVotos = 0;
    private Categoria vencedor = new Categoria();

    private Scanner aux = new Scanner(System.in);

    public Urna() {}

    public Urna(String categoria, ArrayList<Categoria> categoriasList, int numEleitores) {
        this.categoriasList = categoriasList;
        this.categoria = categoria;
        this.numEleitores = numEleitores;
    }

    public void realizarVotacao() {
        System.out.println();
        coletarInformacoes();
        verificarVencedor();


        if (empatadosList.size() > 0) {
            exibirResultadoEmpate();
        } else {
            exibirResultado();
        }

    }

    private void coletarInformacoes() {
        Scanner entrada = new Scanner(System.in);
        int codigo = 0;

        try {

            while (codigo != 007 || countVotos == numEleitores) {
                System.out.println("Qual " + categoria + " deseja votar?");
                listarCandidatos(false);
                System.out.println("Codigo: 0 | Voto Branco");
                codigo = entrada.nextInt();

                armazenarVotos(codigo);
                countVotos++;

                if (codigo == 007 || countVotos == numEleitores) {
                    break;
                }
            }

        } catch (Error e) {
            System.out.println("Digite apenas o numero do candidato");
            coletarInformacoes();
        }
    }

    private void armazenarVotos(int codigoCandidato) {
        boolean codigoExiste = false;

        if (codigoCandidato != 007) {
            for (int i = 0; i < this.categoriasList.size(); i++) {
                Categoria mc = this.categoriasList.get(i);
                if (mc.getCategoria().equals((categoria))) {
                    if (mc.getCodigo() == codigoCandidato) {
                        mc.updateVoto();
                        codigoExiste = true;
                    }
                }
            }
        }

        if (codigoCandidato == 0) {
            votosBrancos++;
            codigoExiste = true;
        }

        if (!codigoExiste) {
            this.votosNulos++;
        }
    }

    private void verificarVencedor() {

        for (int i = 0; i < this.categoriasList.size(); i++) {
            Categoria mc = this.categoriasList.get(i);
            if (mc.getCategoria().equals((categoria))) {
                if (mc.getVotos() > vencedor.getVotos()) {
                    vencedor = new Categoria(mc);
                }
            }
        }

        verificarEmpate();
    }
    private void verificarEmpate() {
        for (int i = 0; i < this.categoriasList.size(); i++) {
            Categoria mc = this.categoriasList.get(i);
            if (mc.getCategoria().equals((categoria))) {
                if (mc.getVotos() == vencedor.getVotos()) {
                    empatadosList.add(mc);
                }
            }
        }
    }
    private void exibirResultadoEmpate() {
        System.out.println();
        System.out.println("Houve um empate");
        System.out.println("A quantidade de votos nulos é " + votosNulos);
        System.out.println("A quantidade de votos brancos é " + votosBrancos);

        for (int i = 0; i < this.empatadosList.size(); i++) {
            Categoria mc = this.empatadosList.get(i);
            if (mc.getCategoria().equals((categoria))) {
                System.out.println("Nome: " + mc.getNome() + " | Votos: " + mc.getVotos());
            }
        }
    }

    private void exibirResultado() {
        System.out.println();
        System.out.println("O vencedor da eleição é: " + vencedor.getNome());
        System.out.println("A quantidade de votos nulos é " + votosNulos);
        System.out.println("A quantidade de votos brancos é " + votosBrancos);
        listarCandidatos(true);
    }

    private void listarCandidatos(boolean exibirvotos) {
        for (int i = 0; i < this.categoriasList.size(); i++) {
            Categoria mc = this.categoriasList.get(i);
            if (mc.getCategoria().equals((categoria))) {
                if (exibirvotos) {
                    System.out.println("Nome: " + mc.getNome() + " | Votos: " + mc.getVotos());
                } else {
                    System.out.println("Codigo: " + mc.getCodigo() + " | Nome: " + mc.getNome());
                }
            }
        }
        System.out.println();
    }
}
