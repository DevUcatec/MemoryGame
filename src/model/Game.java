package model;

import java.io.IOException;

public class Game {
    private int level;
    private boolean levelCompleted;
    private Player player;
    private Dashboard dashboard;
    private int time;

    public Game(Player player) throws IOException {
        this.level = 0;
        this.levelCompleted = false;
        this.player = player;
        this.dashboard = new Dashboard();
        this.time = 5;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }
}
