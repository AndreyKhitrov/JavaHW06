package ru.mts.objects;

import java.util.ArrayList;
import java.util.List;

public class ATMImpl implements ATM {
    private static List<Cell> cells = new ArrayList<Cell>();
    private static String name;
    private List<Integer> nominals = new Nominals().getNominals();
    private long amountAll;
    private long amountMIN;

    public ATMImpl(String name) {
        this.name = name;
        for (int i : nominals) cells.add(new CellImpl(i, 0));
        this.amountAll = 0;
        this.amountMIN = 0;
    }
    //public ATM(CellInterface[] cell) {       this.cell = cell;   }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCountCellATM(int nominal) {
        for (Cell i : cells) {
            if (i.getNominal() == nominal) {
                return i.getCount();
            }
        }
        return -1;
    }

    @Override
    public long getAmountCellATM(int nominal) {
        for (Cell i : cells) {
            if (i.getNominal() == nominal) {
                return (long) i.getCount() * i.getNominal();
            }
        }
        return -1;
    }

    @Override
    public Cell getCellATM(int nominal) {
        for (Cell i : cells) {
            if (i.getNominal() == nominal) {
                return i;
            }
        }
        return null;
    }

    @Override
    public int getSizeATM() {
        return cells.size();
    }

    @Override
    public boolean setCellATM(int nominal, int count) throws Exception {
        for (Cell i : cells) {
            if (i.getNominal() == nominal & (i.getCount() + count) <= i.getCount_Max()) {
                i.setCount(i.getCount() + count);
                setAmounts();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setATMMax() {
        for (Cell i : cells) {
            i.setCountMax();
        }
        setAmounts();
        return true;
    }

    @Override
    public boolean amountToGive(long amount) throws Exception {
        long amountTime = amount;
        int countTime = 0;
        if (amountFind(amount) != 0) return false;
        for (int i = cells.size() - 1; i >= 0; i--) {
            Cell cell = cells.get(i);
            countTime = (int) (amountTime / cell.getNominal());
            if (countTime > cell.getCount()) {
                countTime = cell.getCount();
            }
            amountTime = amountTime - countTime * cell.getNominal();
            cell.setCount(cell.getCount() - countTime);
        }
        setAmounts();
        return true;
    }

    @Override
    public long amountFind(long amount) {
        if (amount > getAmountAll() | amount < getAmountMIN() | amount % getAmountMIN() != 0) {
            return amount;
        }
        long amountTime = amount;
        int countTime = 0;
        for (int i = cells.size() - 1; i >= 0; i--) {
            Cell cell = cells.get(i);
            countTime = (int) (amountTime / cell.getNominal());
            if (countTime > cell.getCount()) countTime = cell.getCount();
            amountTime = amountTime - countTime * cell.getNominal();
        }
        return amountTime;
    }

    private void setAmounts() {
        amountAll = 0;
        amountMIN = 0;
        for (Cell i : cells) {
            if (amountMIN == 0 & i.getCount() != 0) amountMIN = (long) i.getNominal();
            amountAll = amountAll + (long) i.getNominal() * i.getCount();
        }
    }

    @Override
    public long getAmountMIN() {
        return amountMIN;
    }

    @Override
    public long getAmountAll() {
        return amountAll;
    }
}

