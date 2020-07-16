package ru.mts.objects;

public class ATM {
    private static Cell[] cell = new Cell[6];

    public ATM(Cell[] cell) {
        this.cell = cell;
    }

    public ATM() {
        cell[0] = new Cell(100, 0);
        cell[1] = new Cell(200, 0);
        cell[2] = new Cell(500, 0);
        cell[3] = new Cell(1000, 0);
        cell[4] = new Cell(2000, 0);
        cell[5] = new Cell(5000, 0);
    }

    public static long amount() {
        long amountAll = 0;
        for (Cell i : cell) {
            amountAll = amountAll + i.getAmount();
        }
        return amountAll;
    }

    public static long amountMIN() {
        long amountMIN = 0;
        for (Cell i : cell) {
            if (amountMIN == 0 & i.getAmount() != 0) {
                return (long) i.getNominal();
            }
        }
        return amountMIN;
    }

    public Cell[] getATMCell() {
        return cell;
    }

    public int getCellATM(int nominal) {
        for (Cell i : cell) {
            if (i.getNominal() == nominal) {
                return i.getCount();
            }
        }
        return -1;
    }

    public boolean setCellATM(int nominal, int count) {
        for (Cell i : cell) {
            if (i.getNominal() == nominal & (i.getCount() + count) <= i.getCount_Max()) {
                i.setCount(i.getCount() + count);
                return true;
            }
        }
        return false;
    }

    public static boolean amountToGive(long amount) {
        long amountTime = amount;
        int countTime = 0;
        if (ATM.amountFind(amount, cell) == false) return false;
        for (int i = cell.length - 1; i >= 0; i--) {
            countTime = (int) (amountTime / cell[i].getNominal());
            if (countTime > cell[i].getCount()) {
                countTime = cell[i].getCount();
            }
            amountTime = amountTime - countTime * cell[i].getNominal();
            cell[i].setCount(cell[i].getCount() - countTime);

        }
        return true;


    }

    private static boolean amountFind(long amount, Cell[] cell) {
        if (amount > ATM.amount() | amount < ATM.amountMIN() | amount % amountMIN() != 0) {
            return false;
        }
        long amountTime = amount;
        int countTime = 0;
        for (int i = cell.length - 1; i >= 0; i--) {
            countTime = (int) (amountTime / cell[i].getNominal());
            if (countTime > cell[i].getCount()) countTime = cell[i].getCount();
            amountTime = amountTime - countTime * cell[i].getNominal();
        }
        if (amountTime != 0) {
            return false;
        }
        return true;
    }

    public static void load() {
        for (Cell i : cell) {
            i.setCount_Max();
        }
    }

    public int getCountCellATM() {
        return cell.length;
    }
}