package ru.mts;

import ru.mts.exception.NotCountException;
import ru.mts.objects.*;
import ru.mts.operation.*;

public class Main  {
    private static ATMInterface atm;
    public static void main(String[] args) throws Exception, NotCountException {
        System.out.println("Start");
        try {
            new Main().go(atm, new Create());
            new Main().go(atm, new Update());
            new Main().go(atm, new Print());
        } catch (Exception e){
            System.out.println( e );
        } finally {
            System.out.println("Amount ATM: " + ((ATM) atm).amount() );
            System.out.println("End");
        }
    }

    private void go(ATMInterface atm, OperInterface operation) throws Exception {
        this.atm = operation.action(atm);
    }
}
