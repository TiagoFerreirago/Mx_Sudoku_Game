package br.com.mx.sudoku.custom.ui.screen;

import br.com.mx.sudoku.custom.ui.button.CheckGameStatusButton;
import br.com.mx.sudoku.custom.ui.button.FinishGameButton;
import br.com.mx.sudoku.custom.ui.button.ResetButton;
import br.com.mx.sudoku.custom.ui.frame.MainFrame;
import br.com.mx.sudoku.custom.ui.input.NumberText;
import br.com.mx.sudoku.custom.ui.panel.MainPanel;
import br.com.mx.sudoku.custom.ui.panel.SudokuSector;
import br.com.mx.sudoku.service.BoardService;
import br.com.mx.sudoku.service.NotifierService;
import br.com.mx.sudoku.util.Space;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class MainScreen {

    private final static Dimension dimension = new Dimension(600, 600);

    private  BoardService boardService;
    private  NotifierService notifierService;

    private  JButton checkGameStatusButton;
    private  JButton finishGameButton;
    private  JButton resetButton;

    public MainScreen(Map<String, String> gameConfig){
        this.boardService = new BoardService(gameConfig);
        this.notifierService = new NotifierService();
    }

    public void buildMainScreen(){

        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension, mainPanel);

        for(int r = 0; r < 9; r +=3){
            var endRow = r + 2;
            for(int c = 0; c < 9; c =+ 3){
                var endCol = c + 2;
                var spaces = getSpacesFromSector(boardService.getSpaces(), c , endCol, r, endRow);
                JPanel sector = generateSection(spaces);
                mainPanel.add(sector);
            }
        }
        addResetButton(mainPanel);
        addCheckGameStatusButton(mainPanel);
        addFinishGameButton(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private List<Space> getSpacesFromSector(final List<List<Space>> spaces,final int initCol, final int endCol,
    final int initRow, final int endRow) {
        
        List<Space> spaceSector = new ArrayList<>();
        for(int r = initRow; r <= endRow; r++){
            for(int c = initCol; c <= endCol; c++){
                spaceSector.add(spaces.get(c).get(r));
            }
        }
        return spaceSector;
    }

    private JPanel generateSection(List<Space> spaces) {

        List<NumberText> fields = new ArrayList<>(spaces.stream().map(NumberText::new).toList());
        fields.forEach(t -> notifierService.subscribe(CLEAR_SPACE, t));
        return new SudokuSector(fields);

    }

    private void addResetButton(JPanel mainPanel) {

        resetButton = new ResetButton(e -> {
            var dialogResult = showConfirmDialog(null, "Dessa reniciar o game?","Limpar o game",YES_NO_OPTION, QUESTION_MESSAGE);

            if(dialogResult == 0){
                boardService.reset();
                notifierService.notify(CLEAR_SPACE);
            }
        });
        mainPanel.add(resetButton);
    }

    private void addCheckGameStatusButton(JPanel mainPanel) {

        checkGameStatusButton = new CheckGameStatusButton(e -> {
            var hasErrors = boardService.hasErrors();
            var gameStatus = boardService.getStatus();
            var message = switch (gameStatus){
                case NON_STARTED -> "O jogo não foi iniciado";
                case INCOMPLETE -> "O jogo está imcompleto";
                case COMPLETE -> "O jogo está completo";

            };
            message += hasErrors ? " e contém erros" : " e não contém erros";
            showMessageDialog(null, message);
        });
        mainPanel.add(MainScreen.this.checkGameStatusButton);

    }

    private void addFinishGameButton(JPanel mainPanel) {
        
        finishGameButton = new FinishGameButton( e -> {
            if(boardService.gameIsFinished()){
                showMessageDialog(null, "Parabéns você concluiu o jogo");
                resetButton.setEnabled(false);
                checkGameStatusButton.setEnabled(false);
                finishGameButton.setEnabled(false);
            }
            else{
                var message = "Seu game tem alguma inconsistência, ajuste e tente novamente";
                showMessageDialog(null, message);
            }
        });
        mainPanel.add(finishGameButton);
    }
}
