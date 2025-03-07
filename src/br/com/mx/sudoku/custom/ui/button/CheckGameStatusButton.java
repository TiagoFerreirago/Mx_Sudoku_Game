package br.com.mx.sudoku.custom.ui.button;

import java.awt.event.ActionListener;
import javax.swing.JButton;

public class CheckGameStatusButton extends JButton{

    public CheckGameStatusButton(ActionListener actionListener){

        this.setText("Verificar jogo");
        this.addActionListener(actionListener);
    }
}
