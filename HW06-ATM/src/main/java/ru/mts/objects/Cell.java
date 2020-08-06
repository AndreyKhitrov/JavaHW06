package ru.mts.objects;

import ru.mts.exception.NotCountException;

public class Cell implements CellInterface {
    private int nominal;
    private final int COUNT_MAX = 1000 ;
    private int count;

    public Cell(int nominal) {
        this.nominal =  nominal;
        this.count = COUNT_MAX;
    }
    public Cell(int nominal, int count) {
        this.nominal = nominal;
        this.count = count;
    }
    @Override
    public int getNominal() {        return nominal;    }
    @Override
    public int getCount() {        return count;    }
    @Override
    public void setCount(int count) throws Exception {
        if (count < 0||count> COUNT_MAX)
        { throw new NotCountException( "Count error " + count);}
        this.count = count;    }
    @Override
    public void setCountMax() {        this.count = COUNT_MAX;    }
    @Override
    public int getCount_Max() { return COUNT_MAX;    }

    @Override
    public String toString() {
        return "Cell{" + "nominal = " + nominal + ", count = " + count + ", amount = " + nominal*count + '}';
    }


}

