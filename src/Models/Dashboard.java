package Models;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Dashboard {
    final Set<Integer> numbers;
    private Box[][] boxes;
    private int rowsSize;
    private int columnsSize;
    private int missing;

    public Dashboard() throws IOException {
        this.rowsSize = 1;
        this.columnsSize = 2;
        this.numbers = new HashSet<>();
        this.boxes = new Box[this.rowsSize][this.columnsSize];
        fillNumbers();
        fillDashboard();
    }

    public Box[][] getBoxes() {
        return boxes;
    }

    public int getRowsSize() {
        return rowsSize;
    }

    public int getColumnsSize() {
        return columnsSize;
    }

    public int getMissing() {
        return missing;
    }

    public boolean compareBoxes(Box firstBox, Box secondBox) {
        if (firstBox.getNumber() == secondBox.getNumber()) {
            firstBox.setPaired(true);
            secondBox.setPaired(true);
            this.missing--;
            return true;
        }
        return false;
    }

    public void expandDashboard() throws IOException {
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
        this.numbers.clear();
        this.boxes = new Box[this.rowsSize][this.columnsSize];
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
        this.missing = (this.rowsSize * this.columnsSize) / 2;
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
            if (this.boxes[row][column] == null) {
                Box box = new Box(i, row, column);
                this.boxes[row][column] = box;
                positioned = true;
            }
        }
        return positioned;
    }


    private boolean isPair(int number) {
        return number % 2 == 0;
    }

    private int getRandomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }
}
