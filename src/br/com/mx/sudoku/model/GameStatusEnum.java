package br.com.mx.sudoku.model;

public enum GameStatusEnum {

    NON_STARTED("nao inciado"),
    INCOMPLETE("incompleto"),
    COMPLETE("completo");
    
    private String label;
    
    GameStatusEnum(final String label) {

            this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
