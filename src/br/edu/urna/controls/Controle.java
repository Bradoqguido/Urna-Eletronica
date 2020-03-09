package br.edu.urna.controls;

import br.edu.urna.models.Categoria;
import java.util.ArrayList;
import java.util.Scanner;

public class Controle {

    private ArrayList<Categoria> categoriasList = new ArrayList<>();

    private Scanner aux = new Scanner(System.in);

    public Controle () {}

    public void menuConfig() {
        System.out.println("Menu de configuracoes, escolha uma opcao:");
        System.out.println("1 - Visualizar categorias e carregar candidatos pre configurados");
        System.out.println("2 - Visualizar categorias e inserir novos candidatos");
        System.out.println("3 - Buscar os candidatos por categoria");
        System.out.println("4 - Iniciar a votacao de UMA categoria");
        // System.out.println("5 - Iniciar a votacao de TODAS as categorias");
        System.out.println("0 - Sair do sistema");

        try {
            int opcao = aux.nextInt();

            switch (opcao) {
                case 0:
                    System.out.println("Saindo do sistema!");
                    System.exit(1);
                    break;
                case 1:
                    preConfigCandidatos("Filme");
                    preConfigCandidatos("Música");
                    preConfigCandidatos("Escritor");
                    preConfigCandidatos("Autor");
                    break;
                case 2:
                    selecionarCategoria();
                    break;
                case 3:
                    buscarCategoria();
                    break;
                case 4:
                    iniciarVotacao();
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

    public void verCategorias() {
        System.out.println("Digite o numero da categoria que deseja fazer a votacao:");
        System.out.println("1 - Filme,\n2 -  Música,\n3 - Escritor,\n4 - Autor,\n0 - Retornar ao menu principal");
    }

    public void selecionarCategoria() {
        verCategorias();

        try {
            int codigo = this.aux.nextInt();

            switch (codigo) {
                case 0:
                    System.out.println("Retornando ao menu principal!");
                    menuConfig();
                    break;
                case 1:
                    System.out.println("Categoria selecionada: Filme");
                    inserirCandidatos("Filme");
                    break;
                case 2:
                    System.out.println("Categoria selecionada: Música");
                    inserirCandidatos("Música");
                    break;
                case 3:
                    System.out.println("Categoria selecionada: Escritor");
                    inserirCandidatos("Escritor");
                    break;
                case 4:
                    System.out.println("Categoria selecionada: Autor");
                    inserirCandidatos("Autor");
                    break;
                default:
                    System.out.println("Nenhuma categoria selecionada, retornando ao menu principal!");
                    menuConfig();
                    break;
            }

            selecionarCategoria();

        } catch (Error e) {
            System.out.println("\nDigite apenas numeros!!!");
            selecionarCategoria();
        }

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

    private void preConfigCandidatos(String categoria) {
        System.out.println("Carregando os candidatos da categoria: " + categoria);
        switch (categoria) {
            case "Filme":
                for (int i = 1; i < 6; i++) {
                    categoriasList.add(new Categoria(categoria, i, ("Velozes e Furiosos " + i)));
                }
                break;
            case "Música":
                for (int i = 1; i < 6; i++) {
                    categoriasList.add(new Categoria(categoria, i, (i + "ª sinfonia de beethoven")));
                }
                break;
            case "Escritor":
                for (int i = 1; i < 6; i++) {
                    categoriasList.add(new Categoria(categoria, i, ("Escritor simbolico " + i)));
                }
                break;
            case "Autor":
                for (int i = 0; i < this.categoriasList.size(); i++) {
                    categoriasList.add(new Categoria(categoria, i, ("Autor simbolico " + i)));
                }
                break;
            default:
                System.out.println("Nenhuma categoria selecionada, saindo do sistema!");
                System.exit(0);
                break;
        }
        menuConfig();
    }

    public void buscarCategoria() {
        verCategorias();

        try {
            int codigo = this.aux.nextInt();

            switch (codigo) {
                case 0:
                    System.out.println("Retornando ao menu principal!");
                    menuConfig();
                    break;
                case 1:
                    System.out.println("Categoria selecionada: Filme");
                    mostrarDadosDeCategoria("Filme");
                    break;
                case 2:
                    System.out.println("Categoria selecionada: Música");
                    mostrarDadosDeCategoria("Música");
                    break;
                case 3:
                    System.out.println("Categoria selecionada: Escritor");
                    mostrarDadosDeCategoria("Escritor");
                    break;
                case 4:
                    System.out.println("Categoria selecionada: Autor");
                    mostrarDadosDeCategoria("Autor");
                    break;
                default:
                    System.out.println("Nenhuma categoria selecionada, retornando ao menu principal!");
                    menuConfig();
                    break;
            }

            buscarCategoria();

        } catch (Error e) {
            System.out.println("\nDigite apenas numeros!!!");
            this.selecionarCategoria();
        }

    }

    private void mostrarDadosDeCategoria(String categoria) {
        for (int i = 0; i < this.categoriasList.size(); i++) {
            Categoria mc = this.categoriasList.get(i);
            System.out.println(mc.getCodigo() + " - " + mc.getNome());
        }
    }

    public void iniciarVotacao() {
        verCategorias();

        try {
            int codigo = this.aux.nextInt();
            String categoria = "";
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

            System.out.println("Iniciando votacao para: " + categoria);
            new Urna(categoria, categoriasList);

        } catch (Error e) {
            System.out.println("\nDigite apenas numeros!!!");
            iniciarVotacao();
        }
    }
}