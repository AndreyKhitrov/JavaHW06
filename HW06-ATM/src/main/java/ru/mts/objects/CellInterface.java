package ru.mts.objects;

public interface CellInterface {
    public int getNominal() ;
    public int getCount();
    public void setCount(int count) throws Exception;
    public void setCountMax() ;
    public int getCount_Max() ;
}
