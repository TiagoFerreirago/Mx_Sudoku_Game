package br.com.mx.sudoku.custom.ui.panel;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import br.com.mx.sudoku.custom.ui.input.NumberText;
import java.awt.Color;

public class SudokuSector extends JPanel {

    public SudokuSector (List<NumberText> textFields){

        var dimension = new Dimension(170, 170);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setBorder(new LineBorder(Color.black,2,true));
        this.setVisible(true);
        textFields.forEach(this::add);
    }
}
