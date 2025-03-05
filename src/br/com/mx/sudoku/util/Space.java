package br.com.mx.sudoku.util;

public class Space {
   
    private Integer actual;

    private final int expected;

    private final boolean fixed;

   

    public boolean isFixed() {
        return fixed;
    }

    public Space(int expected, boolean fixed) {
        this.expected = expected;
        this.fixed = fixed;

        if(fixed){
           actual = expected;
        }
    }

    public Integer getActual() {
        return actual;
    }

    public void setActual(Integer actual) {
        if(fixed)return;
        this.actual = actual;
    }

    public void cleanSpace(){
        setActual(null);
    }

    public int getExpected() {
        return expected;
    }


    
    
}
