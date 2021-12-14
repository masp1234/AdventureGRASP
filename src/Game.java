public class Game {
    UserInterface ui;
    Controller controller;

    public Game() {
        controller = new Controller();
        ui = new UserInterface(controller);
    }
    public void run() {
        ui.run();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}