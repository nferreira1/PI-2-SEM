/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.sp.padoka.util;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author nathan.ferreira
 */
public class VerificaTelaAberta {

    // FUNÇÃO QUE VERIFICA SE A TELA ESTÁ ABERTA, CASO TRUE, DESABILITA O BOTÃO DA TELA PRINCIPAL
    public void verificaTelaAberta(JFrame tela, JFrame objetoAtual, JButton botao, boolean centraliza) {
        if (!tela.isVisible()) {

            tela.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    botao.setEnabled(true);
                }
            });

            // VERIFICA SE ESTÁ "LOGADO", CASO FALSE, FECHA TODAS AS TELAS ABERTAS
            objetoAtual.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    tela.dispose();
                }
            });

            tela.setVisible(true);
            if (centraliza == true) {
                // COLOCA O JFRAME NO CENTRO DA TELA
                tela.setLocationRelativeTo(null);
            } else {
                // COLOCA O JFRAME AO LADO DIREITO DA TELA PRINCIPAL
                tela.setLocation(objetoAtual.getX() + objetoAtual.getWidth(), objetoAtual.getY());
            }
            botao.setEnabled(false);
        }
    }
}
