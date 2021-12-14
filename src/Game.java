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
        /* i stedet for først at oprette en ny instans af Game og derefter kalde metoden på objektet
        Det nedenstående gør præcis det samme, men kan skrives på en linje.    */
       new Game().run();
    }
}