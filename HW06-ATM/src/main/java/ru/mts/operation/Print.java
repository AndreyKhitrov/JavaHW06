package ru.mts.operation;

import ru.mts.objects.ATM;
import ru.mts.objects.Cell;
import ru.mts.objects.Nominals;

public class Print implements OperInterface {
    @Override
    public ATM action(ATM atm) throws Exception {
        System.out.println("== Print ATM =========================================================");
        for (int i: new Nominals().getNominals()){
            Cell cell = atm.getCellATM(i);
            System.out.println(cell.toString());
        }
        return null;
    }
}
