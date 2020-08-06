package ru.mts.objects;

import java.util.ArrayList;

public class ATM implements ATMInterface {
    private static CellInterface[] cell;
    private static String name;

    public ATM( ArrayList<Integer> nominal, String name){
        cell = new Cell[nominal.size()];
        int index = 0;
        for (int i : nominal){
            cell[index] = new Cell(i,0);
            index++;
        }
        this.name = name;
    }
    //public ATM(CellInterface[] cell) {       this.cell = cell;   }


    public static String getName() {  return name;    }

    @Override
    public CellInterface[] getATMCell (){ return cell;}

    @Override
    public int getCellATM (int nominal){
        for(CellInterface i: cell){
            if ( i.getNominal() == nominal ){
                return i.getCount() ;
            }
        }
        return -1;
    }

    @Override
    public int getCountCellATM (){ return cell.length;}

    @Override
    public boolean setCellATM (int nominal, int count) throws Exception{
        for(CellInterface i: cell){
            if ( i.getNominal() == nominal & (i.getCount() + count) <= i.getCount_Max() ){
                i.setCount(i.getCount() + count);
                return true;
            }
        }
        return false;
    }

    public static boolean setATMMax(){
        for (CellInterface i : cell) {  i.setCountMax();   }
        return true;
    }

    public static boolean amountToGive(long amount) throws Exception {
        long amountTime = amount;
        int countTime = 0;
        if(ATM.amountFind(amount, cell) == false) return false ;
        for ( int i = cell.length-1; i >= 0; i--)
        {
            countTime = (int) (amountTime / cell[i].getNominal());
            if( countTime > cell[i].getCount()){    countTime = cell[i].getCount();            }
            amountTime = amountTime - countTime*cell[i].getNominal();
            cell[i].setCount( cell[i].getCount() - countTime );
        }
        return true;
    }

    private static boolean amountFind(long amount, CellInterface[] cell){
        if (amount > ATM.amount()|amount < ATM.amountMIN()|amount % amountMIN() != 0) {   return false;}
        long amountTime = amount;
        int countTime = 0;
        for ( int i = cell.length-1; i >= 0; i--)
        {
            countTime = (int) (amountTime / cell[i].getNominal());
            if( countTime > cell[i].getCount())     countTime = cell[i].getCount();
            amountTime = amountTime - countTime*cell[i].getNominal();
        }
        if (amountTime != 0) {   return false;}
        return true;
    }

    public static long amount(){
        long amountAll = 0;
        for (CellInterface i : cell) {    amountAll = amountAll + (long) i.getNominal()*i.getCount();        }
        return amountAll;
    }

    public static long amountMIN(){
        long amountMin = 0;
        for (CellInterface i : cell) {
            if( amountMin == 0 & (long) i.getNominal()*i.getNominal() != 0) {      return (long) i.getNominal();            }
        }
        return amountMin;
    }

}

