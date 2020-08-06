package ru.mts.operation;

import ru.mts.exception.*;
import ru.mts.objects.*;
import java.util.Scanner;

public class Update implements OperInterface {
    private static Scanner in = new Scanner(System.in);
    @Override
    public ATMInterface action(ATMInterface atm) throws Exception {
        System.out.println("== Update ATM ==============================================================");
        System.out.println("Amount min: " + ((ATM) atm).amountMIN() + "   Amount max: " + ((ATM) atm).amount());
        System.out.println("Enter then amount: ");
        long amount = in.nextLong();
        int[] countsNominal = new int[atm.getCountCellATM()];
        int x = 0;
        for( CellInterface i: atm.getATMCell() ) {
            countsNominal[x] = atm.getCellATM(i.getNominal());
            x++;
        }
        if (((ATM) atm).amountToGive(amount)) {
            System.out.println( "Issued amount: " + amount );
        }
        else throw new NotAmountException(" Not amount to give ATM!");
        int k = 0;
        for(CellInterface i: atm.getATMCell() ){
            if ((countsNominal[k] - i.getCount()) * i.getNominal() != 0)
                System.out.println( "Nominal: " + i.getNominal() +
                        " count: " + (countsNominal[k] - i.getCount()) +
                        " amount: " + (countsNominal[k] - i.getCount()) * i.getNominal() );
            k++;
        }
        return atm;
    }
}
