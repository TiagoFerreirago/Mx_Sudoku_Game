package br.com.mx.sudoku.service;

import java.util.Map;

import br.com.mx.sudoku.model.Board;
import br.com.mx.sudoku.model.GameStatusEnum;
import br.com.mx.sudoku.util.Space;

import java.util.ArrayList;
import java.util.List;

public class BoardService {

    
    private final static int BOARD_LIMIT = 9;
    private Board board;

    public BoardService(Map<String, String>gameConfig){

        this.board = new Board(initBoard(gameConfig));
    }

    public List<List<Space>> getSpaces(){
        return  board.getSpaces();
    }

    public void reset(){
        board.reset();
    }

    public boolean hasErrors(){
        return board.hasErrors();
    }

    public GameStatusEnum getStatus(){
        return board.getStatus();
    }

    public boolean gameIsFinished(){
        return board.gameIsFinished();
    }

    private List<List<Space>> initBoard(Map<String, String>gameConfig){
        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                String key = i + "," + j;
                if (!gameConfig.containsKey(key)) {
                    gameConfig.put(key, "0,false"); // Define como 0 e não fixo caso não exista
                }
                String[] parts = gameConfig.get(key).split(",");
                int expected = Integer.parseInt(parts[0]);
                boolean fixed = Boolean.parseBoolean(parts[1]);
                spaces.get(i).add(new Space(expected, fixed));
            }
        }
        return spaces;
    }

}
