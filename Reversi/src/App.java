public class App {

    private Game game;
    private Menu menu;
    public App() {
        menu = new Menu();
    }
    public void startApp() {
        int maxScore = 0;
        while (true) {
            game = menu.processMainMenu();
            int score = game.processGame();
            if (score > maxScore) {
                maxScore = score;
                menu.updateLeaderBoard(maxScore);
            }
        }
    }
}
