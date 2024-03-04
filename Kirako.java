import java.util.ArrayList;
import java.util.List;

enum Negyzet { A, B, C }



public class Kirako {

    public static void main(String[] args) {
        Kirako k = new Kirako();
        System.out.println("Eredeti állapot:");
        System.out.println("Bal: " + k.bal);
        System.out.println("Jobb: " + k.jobb);

        ArrayList<Kirako> possibleMoves = k.getPossibleMoves(); // Lehetséges lépések lekérdezése
        System.out.println("\nLehetséges lépések:");
        for (Kirako move : possibleMoves) {
            System.out.println("Bal: " + move.bal + " Jobb: " + move.jobb);
        }

        System.out.println("\nCélállapot?");
        System.out.println(k.isSolution()); // Célállapot ellenőrzése
    }

    ArrayList<Negyzet> bal; // Alulról felfelé
    ArrayList<Negyzet> jobb; // Alulról felfelé

    // Konstruktor alapállapothoz
    public Kirako() {
        bal = new ArrayList<>();
        jobb = new ArrayList<>();
        bal.add(Negyzet.A); // Kezdőállapotban az A és C négyzetek a bal oldalon vannak
        bal.add(Negyzet.C);
        jobb.add(Negyzet.B); // A B négyzet a jobb oldalon van
    }


    public Kirako(ArrayList<Negyzet> bal, ArrayList<Negyzet> jobb) {
        this.bal = new ArrayList<>(bal);
        this.jobb = new ArrayList<>(jobb);
    }


    // Konstruktor az állapotokhoz
    public Kirako(Kirako k, boolean balrol, boolean alulra) {
        this.bal = new ArrayList<>(k.bal);
        this.jobb = new ArrayList<>(k.jobb);

        // Ha az elemet a baloldalról kell áttenni a jobboldalra
        if (balrol) {
            Negyzet elem = this.bal.remove(this.bal.size() - 1); // Az utolsó elemet kivesszük
            if (alulra) {
                this.jobb.add(elem); // Ha alulra kell rakni, akkor az új elemet az aljára tesszük
            } else {
                this.jobb.add(0, elem); // Ha felülre kell rakni, akkor az új elemet a tetejére tesszük
            }
        } else { // Ha az elemet a jobboldalról kell áttenni a baloldalra
            Negyzet elem = this.jobb.remove(this.jobb.size() - 1); // Az utolsó elemet kivesszük
            if (alulra) {
                this.bal.add(elem); // Ha alulra kell rakni, akkor az új elemet az aljára tesszük
            } else {
                this.bal.add(0, elem); // Ha felülre kell rakni, akkor az új elemet a tetejére tesszük
            }
        }
    }

    // célállapot ellenőrzés
    boolean isSolution() {
        return bal.isEmpty() && jobb.size() == 3; // A bal oldalon nincs négyzet, a jobb oldalon pedig pont 3 van
    }

    // Lehetséges lépések az aktuális állapot szerint!
    ArrayList<Kirako> getPossibleMoves() {
        List<Kirako> moves = new ArrayList<>();

        // Ha van négyzet a baloldalon, lehet áthelyezni a jobboldalra
        if (!bal.isEmpty()) {
            Kirako ujKirako = new Kirako(this, true, true);
            moves.add(ujKirako);
        }

        // Ha van négyzet a jobboldalon és van hely a baloldalon, lehet áthelyezni a baloldalra
        if (!jobb.isEmpty() && bal.size() < 2) {
            Kirako ujKirako = new Kirako(this, false, false);
            moves.add(ujKirako);
        }

        // Ha van négyzet a jobboldalon és nincs négyzet a baloldalon, lehet áthelyezni a baloldalra alulra
        if (!jobb.isEmpty() && bal.isEmpty()) {
            Kirako ujKirako = new Kirako(this, false, true);
            moves.add(ujKirako);
        }

        return (ArrayList<Kirako>) moves;
    }
}