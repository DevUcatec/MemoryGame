package Models;

public class Player {
    final String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        this.score += 5;
    }
}
