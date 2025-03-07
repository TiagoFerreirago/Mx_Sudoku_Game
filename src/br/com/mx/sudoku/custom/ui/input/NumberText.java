package br.com.mx.sudoku.custom.ui.input;

import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import br.com.mx.sudoku.service.EventEnum;
import br.com.mx.sudoku.service.EventListener;
import br.com.mx.sudoku.util.Space;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.event.DocumentEvent;


public class NumberText extends JTextField implements  EventListener{


    private Space space;

    public NumberText(Space space){

        this.space = space;
        var dimension = new Dimension(50, 50);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setVisible(true);
        this.setFont(new Font("Arial", Font.PLAIN, 20));
        this.setHorizontalAlignment(CENTER);
        this.setDocument(new NumberTextLimit());
        this.setEnabled(!space.isFixed());
        if(space.isFixed()){
            this.setText(space.getActual().toString());
        }
        this.getDocument().addDocumentListener(new DocumentListener() {
            
            
            @Override
            public void insertUpdate(DocumentEvent e) {

                    changeSpace();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

                    changeSpace();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

                    changeSpace();
            }

            private void changeSpace(){
                if (getText().isEmpty()){
                    space.cleanSpace();
                    return;
                }
                space.setActual(Integer.parseInt(getText()));
            }
            
        });
    }

    @Override
    public void update(EventEnum eventType) {
       
        if(eventType.equals(EventEnum.CLEAR_SPACE) && (this.isEnabled())){
            this.setText("");
        }
    }
}


