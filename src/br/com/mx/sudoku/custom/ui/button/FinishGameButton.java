package br.com.mx.sudoku.custom.ui.button;

import java.awt.event.ActionListener;
import javax.swing.JButton;

public class FinishGameButton extends JButton {

    public FinishGameButton(ActionListener actionListener){
        this.setText("Concluir");
        this.addActionListener(actionListener);
    }
}
