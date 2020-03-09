package br.edu.urna.controls;

import br.edu.urna.models.Categoria;
import java.util.ArrayList;
import java.util.Scanner;

public class Controle {

    private ArrayList<Categoria> categoriasList = new ArrayList<Categoria>();

    private Scanner aux = new Scanner(System.in);

    public Controle () {}

    public void menuConfig() {
        System.out.println("Menu de configuracoes, escolha uma opcao:");
        System.out.println("1 - Visualizar categorias e carregar candidatos pre configurados");
        System.out.println("2 - Visualizar categorias e inserir 5 novos candidatos");
        System.out.println("3 - Buscar os candidatos por categoria");
        System.out.println("4 - Iniciar a votacao de UMA categoria");
        // System.out.println("5 - Iniciar a votacao de TODAS as categorias");
        System.out.println("6 - DELETAR TODAS as categorias e candidatos");
        // System.out.println("7 - DELETAR um candidato de uma categoria especifica");
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

                case 6:
                    categoriasList = new ArrayList<Categoria>();
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

        menuConfig();
    }

    public void verCategorias() {
        System.out.println("Digite o numero da categoria para selecionar ela:");
        System.out.println("1 - Filme,\n2 - Música,\n3 - Escritor,\n4 - Autor,\n0 - Retornar ao menu principal");
    }

    public void inserirCandidatosNaCategoriaSelecionada() {
        verCategorias();
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

    private void preConfigCandidatos() {
        System.out.println("Carregando os candidatos da categoria: " + "Filme");
        for (int i = 0; i < 5; i++) {
            categoriasList.add(new Categoria("Filme", i, ("Velozes e Furiosos " + i)));
        }

        System.out.println("Carregando os candidatos da categoria: " + "Autor");
        for (int i = 0; i < 5; i++) {
            categoriasList.add(new Categoria("Autor", i, ("Autor simbolico " + i)));
        }

        System.out.println("Carregando os candidatos da categoria: " + "Música");
        for (int i = 1; i < 6; i++) {
            categoriasList.add(new Categoria("Música", i, (i + "ª sinfonia de beethoven")));
        }

        System.out.println("Carregando os candidatos da categoria: " + "Escritor");
        for (int i = 0; i < 5; i++) {
            categoriasList.add(new Categoria("Escritor", i, ("Escritor simbolico " + i)));
        }

        menuConfig();

    }

    public void buscarCategoria() {
        verCategorias();
        String categoria = selecionarCategoria();
        mostrarDadosDeCategoria(categoria);
        buscarCategoria();
    }

    private void mostrarDadosDeCategoria(String categoria) {
        for (int i = 0; i < this.categoriasList.size(); i++) {
            Categoria mc = this.categoriasList.get(i);
            if (mc.getCategoria().equals((categoria))) {
                System.out.println("Codigo: " + mc.getCodigo() + " | Nome: " + mc.getNome());
            }
        }
    }

    public void iniciarVotacao() {
        verCategorias();
        String categoria = selecionarCategoria();


        System.out.println("Iniciando votacao para: " + categoria);
        new Urna(categoria, categoriasList);

        System.out.println("Voltando ao menu principal");
        menuConfig();
    }

    public String selecionarCategoria() {
        String categoria = "";

        try {
            int codigo = this.aux.nextInt();
            switch (codigo) {
                case 0:
                    System.out.println("Retornando ao menu principal!");
                    menuConfig();
                    break;
                case 1:
                    System.out.println("Categoria selecionada: Filme");
                    categoria = "Filme";
                    break;
                case 2:
                    System.out.println("Categoria selecionada: Música");
                    categoria = "Música";
                    break;
                case 3:
                    System.out.println("Categoria selecionada: Escritor");
                    categoria = "Escritor";
                    break;
                case 4:
                    System.out.println("Categoria selecionada: Autor");
                    categoria = "Autor";
                    break;
                default:
                    System.out.println("Nenhuma categoria selecionada, retornando ao menu principal!");
                    menuConfig();
                    break;
            }

        } catch (Error e) {
            System.out.println("\nDigite apenas numeros!!!");
            System.out.println("Voltando a menu principal");
            menuConfig();
        }

        return categoria;
    }
}