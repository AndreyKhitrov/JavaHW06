package ru.mts.operation;

import ru.mts.exception.NotAmountException;
import ru.mts.exception.NotCountException;
import ru.mts.objects.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Create implements OperInterface {
    private static Scanner in = new Scanner(System.in);
    private static ArrayList<Integer> cellNominal = new ArrayList<>();

    @Override
    public ATMInterface action(ATMInterface atm) throws Exception {
        int counts = new Create().setCellsNominal();
        ATMInterface atm_time = new Create().setCellsATM( counts, "ATM_One");
        return atm_time;
    }

    public enum Nominal {
        n100 (100), n200 (200), n500(500), n1000(1000), n2000(2000), n5000(5000);
        private final int index;
        Nominal(int index) {  this.index = index;  }
        public int index() {  return index;  }
    }

    private static int setCellsNominal() {
        System.out.println("Enter a constant number of banknotes for all cells: ");
        int counts = in.nextInt();
        System.out.println("======= Creat ATM =======================================================");
        if(counts != 0) {
            for (Nominal nominal : Nominal.values()) {
                if (!cellNominal.contains(nominal.index) & nominal.index != 0) {
                    cellNominal.add(nominal.index);
                }
            }
        }

        if (cellNominal.size()==0) {
            System.out.println("Enter the number of banknote denominations: ");
            int countCell = in.nextInt();
            int indexCount = 0;
            while (indexCount < countCell ){
                System.out.println("Enter the denomination of the banknote (" + (indexCount+1) + " out of " + countCell +" ): ");
                int nominalCell = in.nextInt();
                while(cellNominal.contains(nominalCell)||nominalCell<=0||nominalCell>999999)
                {
                    System.out.println("Enter a different denomination of the banknote. (There is already one!): ");
                    nominalCell = in.nextInt();
                }
                cellNominal.add(nominalCell);
                indexCount++;
            }
        }
        Collections.sort(cellNominal);
        return counts;
    }

    private ATMInterface setCellsATM( int counts, String name) throws Exception {
        System.out.println("===== setCellsATM" + counts + ":=========================================================" );
        ATMInterface atm = new ATM(cellNominal,name);
        if (counts == 0) {
            for (CellInterface i : atm.getATMCell()) {
                int countsEnter = 0;
                System.out.println("Enter then count nominal: " + i.getNominal() + "  ( max  " + (i.getCount_Max() - i.getCount()) + " ): ");
                countsEnter = in.nextInt();
                if (!atm.setCellATM(i.getNominal(), countsEnter)) throw new NotCountException(" Not countsEnter set AMT!");
            }
        }
        else
        {
            for (CellInterface i : atm.getATMCell()) {
                if (!atm.setCellATM(i.getNominal(), counts)) throw new NotCountException(" Not counts set AMT!");
            }
        }

        if (((ATM) atm).amount() == 0) {
            System.out.println("Set COUNT_MAX ATM");
            if (!((ATM) atm).setATMMax())  throw new NotAmountException(" Not amount COUNT_MAX ATM!");
        }
        System.out.println("Amount ATM: " + ((ATM) atm).amount() );
        if (((ATM) atm).amount() == 0) {
            throw new NotAmountException(" Not amount ATM!");
        }
        return atm;
    }
}
