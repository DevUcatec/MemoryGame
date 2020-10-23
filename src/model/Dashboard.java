package model;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Dashboard {
    private Set<Integer> numbers;
    private Box[][] dashboard;

    private int rowsSize;
    private int columnsSize;

    public Dashboard() throws IOException {
        this.rowsSize = 2;
        this.columnsSize = 2;
        this.numbers = new HashSet<Integer>();
        this.dashboard = new Box[this.rowsSize][this.columnsSize];
        fillNumbers();
        fillDashboard();
    }

    private void fillNumbers() {
        int totalNumber = (this.rowsSize * this.columnsSize) / 2;
        while (this.numbers.size() < totalNumber) {
            numbers.add(getRandomNumber(30));
        }
    }

    private void fillDashboard() throws IOException {
        for (int i : this.numbers) {
            boolean addedFirst = false;
            boolean addedSecond = false;

            while (!addedFirst) {
                int row = getRandomNumber(this.rowsSize);
                int column = getRandomNumber(this.columnsSize);
                addedFirst = putBox(i, row, column);
            }

            while (!addedSecond) {
                int row = getRandomNumber(this.rowsSize);
                int column = getRandomNumber(this.columnsSize);
                addedSecond = putBox(i, row, column);
            }
        }
    }

    private boolean putBox(int i, int row, int column) throws IOException {
        boolean positioned = false;
        if (row < this.rowsSize && column < this.columnsSize) {
            if (this.dashboard[row][column] == null) {
                Box box = new Box(i, row, column);
                this.dashboard[row][column] = box;
                positioned = true;
            }
        }
        return positioned;
    }


    public void expandBoard() {
        if (isPair(this.columnsSize)) {
            if (this.rowsSize == this.columnsSize) {
                this.columnsSize++;
            } else {
                this.rowsSize = columnsSize;
            }
        } else {
            this.rowsSize = columnsSize;
            this.columnsSize++;
        }
    }

    private boolean isPair(int number) {
        return number % 2 == 0;
    }

    private int getRandomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    public void printN() {
        for (int i : numbers) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }

    public void printD() {
        for (int i = 0; i < this.rowsSize; i++) {
            for (int j = 0; j < this.columnsSize; j++) {
                System.out.print("[" + dashboard[i][j].getImage() + dashboard[i][j].getRowPosition() + dashboard[i][j].getColumnPosition() + "]");
            }
            System.out.println("");
        }
    }

    private boolean compareBoxes(Box firstBox, Box secondBox) {
        if (firstBox.getNumber() == secondBox.getNumber()) {
            firstBox.setPaired(true);
            secondBox.setPaired(true);
            return true;
        }
        return false;
    }

}
