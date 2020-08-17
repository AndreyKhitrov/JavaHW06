package ru.mts.operation;

import ru.mts.exception.NotAmountException;
import ru.mts.exception.NotCountException;
import ru.mts.objects.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Create implements OperInterface {
    private static Scanner in = new Scanner(System.in);
    private static ATM atm;
    private static List<Integer> nominals = new Nominals().getNominals();

    @Override
    public ATM action(ATM atm) throws Exception {
        this.atm = atm;
        int counts = new Create().enterCounts();
        ATM atm_time = new Create().setCellsATM(counts);
        return atm_time;
    }

    private static int enterCounts(){
        System.out.println("Enter a constant number of banknotes for all cells: ");
        int counts = in.nextInt();
        return counts;
    }

    private ATM setCellsATM(int counts) throws Exception {
        System.out.println("===== setCellsATM" + counts + ":=========================================================");
        if (counts == 0) {
            for (int i : nominals) {
                int countsEnter = 0;
                Cell cell = atm.getCellATM(i);
                System.out.println("Enter then count nominal: " + cell.getNominal() + "  ( max  " + (cell.getCount_Max() - cell.getCount()) + " ): ");
                countsEnter = in.nextInt();
                if (!atm.setCellATM(cell.getNominal(), countsEnter))
                    throw new NotCountException(" Not countsEnter set AMT!");
            }
        } else {
            for (int i : nominals) {
                if (!atm.setCellATM(i, counts)) throw new NotCountException(" Not counts set AMT!");
            }
        }

        if (atm.getAmountAll() == 0) {
            System.out.println("Set COUNT_MAX ATM");
            if (!atm.setATMMax()) throw new NotAmountException(" Not amount COUNT_MAX ATM!");
        }
        System.out.println("Amount ATM: " + atm.getAmountAll());
        if (atm.getAmountAll() == 0) {
            throw new NotAmountException(" Not amount ATM!");
        }
        return atm;
    }

}
