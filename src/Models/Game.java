package Models;

import java.io.IOException;

public class Game {
    final Player player;
    final Dashboard dashboard;
    private int level;
    private int time;
    private boolean levelCompleted;

    public Game(Player player) throws IOException {
        this.level = 1;
        this.levelCompleted = false;
        this.player = player;
        this.dashboard = new Dashboard();
        this.time = dashboard.getMissing() * 10;
    }

    public void advanceNextLevel() throws IOException {
        level++;
        dashboard.expandDashboard();
    }

    public boolean validateMove(Box firstBox, Box secondBox) {
        if (dashboard.compareBoxes(firstBox, secondBox)) {
            player.increaseScore();
            return true;
        }
        return false;
    }

    public int getLevel() {
        return level;
    }

    public boolean isLevelCompleted() {
        return levelCompleted;
    }

    public void setLevelCompleted(boolean levelCompleted) {
        this.levelCompleted = levelCompleted;
    }

    public Player getPlayer() {
        return player;
    }


    public Dashboard getDashboard() {
        return dashboard;
    }


    public int getTime() {
        return time;
    }

}
