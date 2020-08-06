package ru.mts.objects;

public interface ATMInterface {
    String name = null;
    public CellInterface[] getATMCell ();
    public int getCellATM (int nominal);
    public  boolean setCellATM (int nominal, int count) throws Exception;
    public int getCountCellATM();
    public static boolean setATMMax() {return false;}
    public static  boolean amountToGive(long amount) {return false;}
    private static boolean amountFind(long amount, CellInterface[] cell) {return false;}
    public static long amount() {return 0;}
    public static long amountMIN() {return 0;}
    public static String getName() {return name;};
}
