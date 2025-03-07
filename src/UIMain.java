import java.util.HashMap;
import java.util.Map;

import br.com.mx.sudoku.custom.ui.screen.MainScreen;

public class UIMain {

    public static void main(String[] args) {
        Map<String, String> positions = new HashMap<>();
        positions.put("0,0", "4,false"); positions.put("1,0", "7,false"); positions.put("2,0", "9,true");
        positions.put("3,0", "5,false"); positions.put("4,0", "8,true"); positions.put("5,0", "6,true");
        positions.put("6,0", "2,true"); positions.put("7,0", "3,false"); positions.put("8,0", "1,false");

        positions.put("0,1", "1,false"); positions.put("1,1", "3,true"); positions.put("2,1", "5,false");
        positions.put("3,1", "4,false"); positions.put("4,1", "7,true"); positions.put("5,1", "2,false");
        positions.put("6,1", "8,false"); positions.put("7,1", "9,true"); positions.put("8,1", "6,true");

        positions.put("0,2", "2,false"); positions.put("1,2", "6,true"); positions.put("2,2", "8,false");
        positions.put("3,2", "9,false"); positions.put("4,2", "1,true"); positions.put("5,2", "3,false");
        positions.put("6,2", "7,false"); positions.put("7,2", "4,false"); positions.put("8,2", "5,true");
    
        var mainsScreen = new MainScreen(positions);
        mainsScreen.buildMainScreen();
    }
}
