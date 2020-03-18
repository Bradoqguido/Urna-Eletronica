package br.edu.urna.controls;

import br.edu.urna.models.Categoria;

import javax.swing.*;
import java.util.ArrayList;

public class Urna {

    private ArrayList<Categoria> categoriasList = new ArrayList<>();
    private ArrayList<Categoria> empatadosList = new ArrayList<>();
    private String categoria = "";
    private int votosNulos = 0, votosBrancos = 0, numEleitores = 0, countVotos = 0;
    private Categoria vencedor = new Categoria();

    public Urna() {}

    public Urna(String categoria, ArrayList<Categoria> categoriasList, int numEleitores) {
        this.categoriasList = categoriasList;
        this.categoria = categoria;
        this.numEleitores = numEleitores;
    }

    public void realizarVotacao() {
        coletarInformacoes();
        verificarVencedor();
        verificarEmpate();

        if (empatadosList.size() > 0) {
            exibirResultadoEmpate();
        } else {
            exibirResultado();
        }

    }

    private void coletarInformacoes() {
        int codigo = 0;

        try {
            while (codigo != 007 || countVotos == numEleitores) {
                StringBuilder strb = new StringBuilder();
                strb.append("Qual " + categoria + " deseja votar?").append("\n");
                strb.append(listarCandidatos(false));
                strb.append("Codigo: 0 | Voto Branco").append("\n");

                String x = JOptionPane.showInputDialog(null, strb.toString());

                codigo = Integer.parseInt(x);
                armazenarVotos(codigo);
                countVotos++;

                if (codigo == 007 || countVotos == numEleitores) {
                    break;
                }
            }

        } catch (Error e) {
            JOptionPane.showMessageDialog(null, "Digite apenas o numero do candidato");
            coletarInformacoes();
        }
    }

    private void armazenarVotos(int codigoCandidato) {
        boolean codigoExiste = false;

        if (codigoCandidato != 007) {
            for (Categoria mc : this.categoriasList) {
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
        for (Categoria mc : this.categoriasList) {
            if (mc.getCategoria().equals((categoria))) {
                if (mc.getVotos() > vencedor.getVotos()) {
                    vencedor = new Categoria(mc);
                }
            }
        }
    }
    private void verificarEmpate() {
        for (Categoria mc : this.categoriasList) {
            if (mc.getCategoria().equals((categoria))) {
                if (mc.getVotos() == vencedor.getVotos() && !(mc.getNome().equals(vencedor.getNome()))) {
                    empatadosList.add(mc);
                }
            }
        }
    }
    private void exibirResultadoEmpate() {
        StringBuilder strb = new StringBuilder();
        strb.append("Houve um empate").append("\n\n");
        strb.append("A quantidade de votos nulos é ").append(votosNulos).append("\n");
        strb.append("A quantidade de votos brancos é ").append(votosBrancos).append("\n\n");

        for (Categoria mc : this.empatadosList) {
            if (mc.getCategoria().equals((categoria))) {
                strb.append("Nome: ").append(mc.getNome()).append(" | Votos: ").append(mc.getVotos()).append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, strb.toString());
    }

    private void exibirResultado() {
        StringBuilder strb = new StringBuilder();
        strb.append("O vencedor da eleição é: ").append(vencedor.getNome()).append("\n\n");
        strb.append("A quantidade de votos nulos é ").append(votosNulos).append("\n");
        strb.append("A quantidade de votos brancos é ").append(votosBrancos).append("\n\n");
        strb.append(listarCandidatos(true));
        JOptionPane.showMessageDialog(null, strb.toString());
    }

    private String listarCandidatos(boolean exibirvotos) {
        StringBuilder strb = new StringBuilder();
        for (Categoria mc : this.categoriasList) {
            if (mc.getCategoria().equals((categoria))) {
                if (exibirvotos) {
                    strb.append("Nome: ").append(mc.getNome()).append(" | Votos: ").append(mc.getVotos()).append("\n");
                } else {
                    strb.append("Codigo: ").append(mc.getCodigo()).append(" | Nome: ").append(mc.getNome()).append("\n");
                }
            }
        }
        return strb.toString();
    }
}
