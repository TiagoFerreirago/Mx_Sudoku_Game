import br.com.mx.sudoku.model.Board;
import br.com.mx.sudoku.util.BoardTemplate;
import br.com.mx.sudoku.util.Space;
import java.util.*;

public class Main {

    private final static Scanner scanner = new Scanner(System.in);
    private final static int BOARD_LIMIT = 9;
    private static Board board;

    public static void main(String[] args) throws Exception {
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

// Continue adicionando todas as posições...

startGame(positions);  // Inicia o jogo automaticamente


        int option;
        while (true) {
            System.out.println("\nSelecione uma das opções a seguir:");
            System.out.println("1 - Iniciar um novo Jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - Limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            option = scanner.nextInt();

            switch (option) {
                case 1 -> startGame(positions);
                case 2 -> inputNumber();
                case 3 -> removeNumber();
                case 4 -> showCurrentGame();
                case 5 -> showGameStatus();
                case 6 -> clearGame();
                case 7 -> finishGame();
                case 8 -> {
                    System.out.println("Saindo...");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida, selecione uma das opções do menu.");
            }
        }
    }

    private static void startGame(Map<String, String> positions) {
        if (board != null) {
            System.out.println("O jogo já foi iniciado.");
            return;
        }

        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                String key = i + "," + j;
                if (!positions.containsKey(key)) {
                    positions.put(key, "0,false"); // Define como 0 e não fixo caso não exista
                }
                String[] parts = positions.get(key).split(",");
                int expected = Integer.parseInt(parts[0]);
                boolean fixed = Boolean.parseBoolean(parts[1]);
                spaces.get(i).add(new Space(expected, fixed));
            }
        }

        board = new Board(spaces);
        System.out.println("O jogo está pronto para começar!");
    }

    private static void inputNumber() {
        if (!validateStartGame()) return;

        System.out.println("Informe a coluna (0-8): ");
        int col = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha (0-8): ");
        int row = runUntilGetValidNumber(0, 8);
        System.out.printf("Informe o número para a posição [%d,%d]:\n", col, row);
        int value = runUntilGetValidNumber(1, 9);

        if (!board.changeValue(col, row, value)) {
            System.out.printf("A posição [%d,%d] tem um valor fixo.\n", col, row);
        }
    }

    private static void removeNumber() {
        if (!validateStartGame()) return;

        System.out.println("Informe a coluna (0-8): ");
        int col = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha (0-8): ");
        int row = runUntilGetValidNumber(0, 8);

        if (!board.clearValue(col, row)) {
            System.out.printf("A posição [%d,%d] tem um valor fixo.\n", col, row);
        }
    }

    private static void showCurrentGame() {
        if (!validateStartGame()) return;

        Object[] args = new Object[81];
        int argPos  = 0;

        for (int i = 0; i < BOARD_LIMIT; i++) {
            for (var col : board.getSpaces()) {
                args[argPos ++] = " " + ((Objects.isNull(col.get(i).getActual())) ? " " : col.get(i).getActual());
            }
        }

        System.out.println("\nSeu jogo está assim:");
        System.out.printf(BoardTemplate.BOARD_TEMPLATE + "\n", args);
    }

    private static void showGameStatus() {
        if (!validateStartGame()) return;

        System.out.printf("Status do jogo: %s\n", board.getStatus().getLabel());
        System.out.println(board.hasErrors() ? "O jogo contém erros!" : "O jogo não contém erros.");
    }

    private static void clearGame() {
        if (!validateStartGame()) return;

        System.out.println("Tem certeza que deseja reiniciar o jogo? (sim/não)");
        String confirm;
        do {
            confirm = scanner.next();
            if (!confirm.equalsIgnoreCase("sim") && !confirm.equalsIgnoreCase("não")) {
                System.out.println("Por favor, informe 'sim' ou 'não'.");
            }
        } while (!confirm.equalsIgnoreCase("sim") && !confirm.equalsIgnoreCase("não"));

        if (confirm.equalsIgnoreCase("sim")) {
            board.reset();
            System.out.println("O jogo foi reiniciado.");
        }
    }

    private static void finishGame() {
        if (!validateStartGame()) return;

        if (board.gameIsFinished()) {
            System.out.println("Parabéns! Você venceu o desafio!");
            showCurrentGame();
            board = null;
        } else if (board.hasErrors()) {
            System.out.println("O jogo contém erros. Verifique e corrija.");
        } else {
            System.out.println("Ainda existem espaços a serem preenchidos.");
        }
    }

    private static int runUntilGetValidNumber(final int min, final int max) {
        int number;
        do {
            number = scanner.nextInt();
            if (number < min || number > max) {
                System.out.printf("Número inválido. Informe um valor entre %d e %d:\n", min, max);
            }
        } while (number < min || number > max);
        return number;
    }

    private static boolean validateStartGame() {
        if (board == null) {
            System.out.println("O jogo ainda não foi iniciado.");
            return false;
        }
        System.out.println("|| Jogo Iniciado ||");
        return true;
    }
}
