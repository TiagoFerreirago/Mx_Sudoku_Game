package br.com.mx.sudoku.custom.ui.button;

import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ResetButton extends JButton{


    public ResetButton(ActionListener actionListener){

        this.setText("Reniciar jogo");
        this.addActionListener(actionListener);
    }
}
