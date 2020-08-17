package ru.mts.objects;

public interface ATM {
    String name = null;
    public String getName();
    public int getCountCellATM(int nominal);
    public long getAmountCellATM (int nominal);
    public Cell getCellATM (int nominal);
    public int getSizeATM();
    public  boolean setCellATM (int nominal, int count) throws Exception;
    public boolean setATMMax();
    public boolean amountToGive(long amount) throws Exception;
    public long amountFind(long amount);
    private void setAmounts() {};
    public long getAmountAll();
    public long getAmountMIN();
}
