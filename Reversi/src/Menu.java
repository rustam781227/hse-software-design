import java.util.Scanner;

import static java.lang.System.exit;

public class Menu {
    static final String[] MAIN_MENU = {
            "1 - Start game",
            "2 - Change settings",
            "3 - View results",
            "4 - Exit"
    };
    static final String[] SETTINGS_MENU = {
            "1 - Select side",
            "2 - Select difficulty",
            "3 - Select game mode",
            "4 - Return"
    };
    static final String[] MODE_MENU = {
            "1 - Player vs Computer",
            "2 - Player bs Player",
            "3 - Return"
    };
    static final String[] SIDE_MENU = {
            "1 - Black",
            "2 - White",
            "3 - Return"
    };
    static final String[] DIFFICULTY_MENU = {
            "1 - Newbie",
            "2 - Professional",
            "3 - Return"
    };
    Scanner scanner;
    Character side;
    int difficulty;
    int mode;
    int highScore;

    public Menu() {
        scanner = new Scanner(System.in);
        side = 'b';
        difficulty = 1;
        mode = 1;
    }

    public Game processMainMenu() {
        displayTab(MAIN_MENU);
        int option = getOption(4);
        switch (option) {
            case 2 -> processSettings();
            case 3 -> processResults();
            case 4 -> exit(0);
        }
        return new Game('b', difficulty, mode);
    }

    private int getOption(int to) {
        int option = 0;
        if (scanner.hasNextInt()) {
            option = scanner.nextInt();
        } else {
            scanner.next();
        }
        while (option < 1 || option > to) {
            System.out.print("\nIncorrect input! Try again: ");
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
            } else {
                scanner.next();
            }
        }
        return option;
    }

    private void processResults() {
        System.out.println(highScore);
        System.out.println("1 - Return");
        getOption(1);
        processMainMenu();
    }

    private void processSettings() {
        displayTab(SETTINGS_MENU);
        int option = getOption(4);
        switch (option) {
            case 1 -> processSideMenu();
            case 2 -> processDifficultyMenu();
            case 3 -> processModeMenu();
            case 4 -> processMainMenu();
        }
    }

    private void processModeMenu() {
        displayTab(MODE_MENU);
        int option = getOption(3);
        mode = option == 2 ? 2 : 1;
        processMainMenu();
    }

    private void processDifficultyMenu() {
        displayTab(DIFFICULTY_MENU);
        difficulty = getOption(3);
        processMainMenu();
    }

    private void processSideMenu() {
        displayTab(SIDE_MENU);
        side = getOption(3) == 1 ? 'b' : 'w';
        processMainMenu();
    }

    private void displayTab(String[] options) {
        for (String option : options) {
            System.out.println(option);
        }
        System.out.print("Choose your option: ");
    }

    public void updateLeaderBoard(int maxScore) {
        highScore = maxScore;
    }
}
