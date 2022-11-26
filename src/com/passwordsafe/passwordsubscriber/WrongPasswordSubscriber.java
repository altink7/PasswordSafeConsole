package com.passwordsafe.passwordsubscriber;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class WrongPasswordSubscriber implements ISubscriber{
    record SelectionState(Integer menu, Date timeStamp) {}

    private LinkedList<SelectionState> selectionState = new LinkedList<>();

    @Override
    public void selection(int menu, boolean passwordCheck) {
        if(menu==1&& passwordCheck) {
            this.selectionState.add(new SelectionState(menu, new Date()));
        }
        if(menu==1&& selectionState.size()>0) {
            System.out.print("\nWrong Password States[Time]:");

            System.out.print("\n--------");
            for (SelectionState i : selectionState) {
                System.out.printf("\nTime: %s\n--------\n", new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss").format(i.timeStamp));
            }
        }

    }
}
