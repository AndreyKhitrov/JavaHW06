package ru.mts.operation;

import ru.mts.exception.*;
import ru.mts.objects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Update implements OperInterface {
    private static Scanner in = new Scanner(System.in);
    private static List<Integer> nominals = new Nominals().getNominals();
    @Override
    public ATM action(ATM atm) throws Exception {
        System.out.println("== Update ATM ==============================================================");
        System.out.println("Amount min: " + atm.getAmountMIN() + "   Amount max: " + atm.getAmountAll());
        System.out.println("Enter then amount: ");
        long amount = in.nextLong();
        List<Integer> countsNominal = new ArrayList<Integer>();
        for( int i: nominals ) {
            countsNominal.add(atm.getCountCellATM(i));
        }
        if (atm.amountToGive(amount)) {
            System.out.println( "Issued amount: " + amount );
        }
        else throw new NotAmountException(" Not amount to give ATM!");
        for(int  i: nominals ){
            if (countsNominal.get(nominals.indexOf(i)) - atm.getCountCellATM(i) != 0)
                System.out.println( "Nominal: " + i +
                        " count: " + (countsNominal.get(nominals.indexOf(i)) - atm.getCountCellATM(i)) +
                        " amount: " + (countsNominal.get(nominals.indexOf(i)) - atm.getCountCellATM(i))* i);
        }
        return atm;
    }
}
