package at.altin.passwordsafe.passwordsubscriber;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Date;

import static java.lang.System.out;

/***
 * WrongPasswordSubscriber
 * implements ISubscriber
 * it is a subscriber for the reset password
 */

public class PasswordResetSubscriber implements ISubscriber {
    record SelectionState(Integer menu, Date timeStamp) {}

    /** jedes Child hat eine eigene Liste <br>
     * damit wir die Zeitpunkte speichern können und getrennt von anderen Subscribern Statistiken ausgeben können <br>
     */
    private final LinkedList<SelectionState> selectionState = new LinkedList<>();

    @Override
    public void selection(int menu, boolean passwordCheck) {
        if(menu == 6 && passwordCheck) {
            this.selectionState.add(new SelectionState(menu, new Date()));
        }

        if(menu == 6 && selectionState.size()>0) {
            out.print("\nPassword reseted States[Time]:");
            out.print("\n--------");
            selectionState.forEach(i -> out.printf("\nTime: %s\n--------\n", new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss").format(i.timeStamp)));
        }

    }
}
