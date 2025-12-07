package JavaBattleSim;

import java.util.ArrayList;

public class Checker {
    public static boolean checkIfInteractive(ArrayList<FightEntity> fighters) {
        for (FightEntity fighter : fighters) {
            if (fighter instanceof Monster) {
                return false;
            }
        }
        return true;
    }
    public static boolean checkIfAllAlive(ArrayList<FightEntity> fighters){
        for (FightEntity fighter:fighters){
            if (!fighter.isAlive()){
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Monster> getNonInteractives(ArrayList<FightEntity> fighters) {
        ArrayList<Monster> monsters = new ArrayList<>();
        for (FightEntity fighter : fighters) {
            if (fighter instanceof Monster) {
                monsters.add((Monster) fighter);
            }
        }
        if (!monsters.isEmpty()) {
            return monsters;
        } else {
            return null;
        }
    }

    public static boolean isInteractive(FightEntity fighter) {
        if (fighter instanceof Monster) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isThereWinner(ArrayList<FightEntity> fighters) {
        int counter = 0;
        for (FightEntity fighter : fighters) {
            if (fighter.infight) {
                counter++;
            }
        }
        return counter == 1;
    }

    public static FightEntity returnWinner(ArrayList<FightEntity> fighters) {
        if (isThereWinner(fighters)) {
            for (FightEntity fighter : fighters) {
                if (fighter.infight) {
                    return fighter;
                }
            }
        }
        return null;
    }
}
