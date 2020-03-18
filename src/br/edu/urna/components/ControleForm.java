package br.edu.urna.components;

import br.edu.urna.controls.Controle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleForm extends JFrame {

    private JPanel painelPrincipal;
    private JPanel painelDeOpcoes;
    private JButton carregarCandidatosButton;
    private JButton buscarCandidatoButton;
    private JButton iniciarVotacaoButton;
    private JButton sairButton;
    private Controle controle = new Controle();

    public ControleForm() {
        inicializarComponents();
        listeners();
    }

    private void inicializarComponents() {

        getContentPane().setLayout(new BorderLayout(0, 0));

        painelPrincipal = new JPanel();
        getContentPane().add(painelPrincipal, BorderLayout.CENTER);
        painelPrincipal.setLayout(new BorderLayout(0, 0));

        iniciarVotacaoButton  = new JButton("Iniciar vota\u00e7\u00e3o");
        painelPrincipal.add(iniciarVotacaoButton, BorderLayout.NORTH);

        carregarCandidatosButton = new JButton("Carregar candidatos");
        painelPrincipal.add(carregarCandidatosButton, BorderLayout.EAST);

        buscarCandidatoButton = new JButton("Buscar candidato");
        painelPrincipal.add(buscarCandidatoButton, BorderLayout.WEST);

        sairButton = new JButton("Sair");
        painelPrincipal.add(sairButton, BorderLayout.SOUTH);

        setSize(300, 130);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void listeners() {

        carregarCandidatosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controle.preConfigCandidatos();
                carregarCandidatosButton.setVisible(false);
            }
        });

        buscarCandidatoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controle.buscarCategoria();
            }
        });

        iniciarVotacaoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                controle.iniciarVotacao();
                setVisible(true);
            }
        });

        sairButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
