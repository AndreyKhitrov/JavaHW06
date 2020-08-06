package ru.mts.operation;

import ru.mts.objects.ATMInterface;
import ru.mts.objects.CellInterface;

public class Print implements OperInterface {
    @Override
    public ATMInterface action(ATMInterface atm) throws Exception {
        System.out.println("== Print ATM =========================================================");
        for (CellInterface c: atm.getATMCell()){
            System.out.println(c.toString());
        }
        return null;
    }
}
