package ru.mts;

import ru.mts.exception.NotCountException;
import ru.mts.objects.*;
import ru.mts.operation.*;

public class Main  {
    private static ATM atm;
    public static void main(String[] args) throws Exception, NotCountException {
        System.out.println("Start");
        try {
            new Main().go(new ATMImpl("ATM1"), new Create());
            new Main().go(atm, new Update());
            new Main().go(atm, new Print());
        } catch (Exception e){
            System.out.println( e );
        } finally {
               System.out.println("End");
        }
    }

    private void go(ATM atm, OperInterface operation) throws Exception {
        this.atm = operation.action(atm);
    }
}
