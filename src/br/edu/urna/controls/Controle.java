package br.edu.urna.controls;

import br.edu.urna.components.ControleForm;
import br.edu.urna.models.Categoria;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Controle {

    private ArrayList<Categoria> categoriasList = new ArrayList<>();

    private Scanner aux = new Scanner(System.in);

    public Controle () {}

    public void menuConfig() {

        new ControleForm();

        System.out.println();
        System.out.println("Menu de configuracoes, escolha uma opção:");
        System.out.println("1 - carregar candidatos pre configurados para todas as categorias");
        // System.out.println("2 - Visualizar categorias e inserir 5 novos candidatos");
        System.out.println("2 - Buscar os candidatos por categoria");
        System.out.println("3 - Iniciar a votacao de UMA categoria");
        System.out.println("4 - DELETAR TODAS as categorias e candidatos");
        // System.out.println("6 - DELETAR um candidato de uma categoria especifica");
        System.out.println("0 - Sair do sistema");

        acoesMenu();
    }

    private void acoesMenu() {
        try {
            int opcao = aux.nextInt();

            switch (opcao) {
                case 0:
                    System.out.println("Saindo do sistema!");
                    System.exit(1);
                    break;
                case 1:
                    preConfigCandidatos();
                    break;
                case 2:
                    inserirCandidatosNaCategoriaSelecionada();
                    break;
                case 3:
                    buscarCategoria();
                    break;
                case 4:
                    iniciarVotacao();
                    break;
                case 5:
                    categoriasList.clear();
                    break;
                default:
                    System.out.println("Digite apenas os numeros, das opcoes");
                    menuConfig();
                    break;
            }

        } catch (Error e) {
            System.out.println("Nenhuma opcao selecionada, selecione uma ao menos!");
            System.out.println("Lembre-se de usar apenas numeros!");
            menuConfig();
        }
    }

    public int verCategorias() {
        try {
            String codigo = JOptionPane.showInputDialog(null,
                ("Digite o numero da categoria para selecionar ela:\n1 - Filme,\n2 - Música,\n3 - Escritor,\n4 - Autor,\n0 - Retornar ao menu principal"));
            return Integer.parseInt(codigo);
        } catch (Error e) {
            JOptionPane.showMessageDialog(null, "Digite apenas os números das op\u00e7ões!\nTente novamente!");
            verCategorias();
        }
        return 0;
    }

    private void inserirCandidatosNaCategoriaSelecionada() {
        String categoria = selecionarCategoria();

        if (categoria.equals("Filme")) {
            inserirCandidatos(categoria);
        }
        if (categoria.equals("Música")) {
            inserirCandidatos(categoria);
        }
        if (categoria.equals("Escritor")) {
            inserirCandidatos(categoria);
        }
        if (categoria.equals("Autor")) {
            inserirCandidatos(categoria);
        }

        inserirCandidatosNaCategoriaSelecionada();
    }

    private void inserirCandidatos(String categoria) {
        System.out.println("Insira dos candidatos para a categoria: " + categoria);
        for (int i = 1; i < 6; i++) {
            System.out.println("Digite o nome do candidato: ");
            String nome = this.aux.nextLine();

            System.out.println("Digite o codigo do candidato: ");
            int codigo = this.aux.nextInt();

            categoriasList.add(new Categoria(categoria, codigo, nome));
        }
    }

    public void preConfigCandidatos() {

        for (int i = 1; i <= 5; i++) {
            categoriasList.add(new Categoria("Filme", i, ("Velozes e Furiosos " + i)));
        }

        for (int i = 1; i <= 5; i++) {
            categoriasList.add(new Categoria("Autor", i, ("Autor simbolico " + i)));
        }

        for (int i = 1; i <= 5; i++) {
            categoriasList.add(new Categoria("Música", i, (i + "ª sinfonia de beethoven")));
        }

        for (int i = 1; i <= 5; i++) {
            categoriasList.add(new Categoria("Escritor", i, ("Escritor simbolico " + i)));
        }

        JOptionPane.showMessageDialog(null, ("Candidatos das categorias: Filme, Autor, Escritores e Música\nCarregados!"));
    }

    public void buscarCategoria() {
        String categoria = selecionarCategoria();
        if (!categoria.equals("")) {
            mostrarDadosDeCategoria(categoria);
        }
    }

    private void mostrarDadosDeCategoria(String categoria) {
        StringBuilder msg = new StringBuilder();
        for (Categoria mc : this.categoriasList) {
            if (mc.getCategoria().equals((categoria))) {
                msg.append("Codigo: ").append(mc.getCodigo()).append(" | Nome: ").append(mc.getNome()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, "Categoria selecionada: " + categoria + "\n" + msg.toString());
    }

    public void iniciarVotacao() {
        String categoria = selecionarCategoria();

        if (!categoria.equals("")) {
            int numEleitores = getNumeroDeEleitores();

            if (numEleitores <= 0) {
                JOptionPane.showMessageDialog(null, ("Numero de eleitores igual ou menor que 0\nRetornando ao menu principal!"));
            } else {
                JOptionPane.showMessageDialog(null, ("Iniciando votacao para: " + categoria));
                Urna urna = new Urna(categoria, categoriasList, numEleitores);
                urna.realizarVotacao();

                JOptionPane.showMessageDialog(null, ("Votacao encerrada!\nVoltando ao menu principal"));
            }
        }
    }

    public int getNumeroDeEleitores() {
        try {
            String str = JOptionPane.showInputDialog(null, ("Digite o numero de eleitores que você estima para esta eleição\nOu digite 0 para voltar ao menu principal"));
            int num = Integer.parseInt(str);
            return num;
        } catch (Error e) {
            JOptionPane.showMessageDialog(null, ("Digite apenas numeros!!!\nTente novamente!"));
            getNumeroDeEleitores();
        }
        return 0;
    }

    public String selecionarCategoria() {
        String categoria = "";

        try {
            int codigo = verCategorias();
            switch (codigo) {
                case 0:
                    // retorna ao menu principal
                    break;
                case 1:
                    categoria = "Filme";
                    break;
                case 2:
                    categoria = "Música";
                    break;
                case 3:
                    categoria = "Escritor";
                    break;
                case 4:
                    categoria = "Autor";
                    break;
                default:
                    JOptionPane.showMessageDialog(null, ("Nenhuma categoria selecionada, retornando ao menu principal!"));
                    break;
            }

        } catch (Error e) {
            JOptionPane.showMessageDialog(null, ("Digite apenas os números das op\u00e7ões!\nVoltando ao menu principal!"));
        }

        return categoria;
    }
}