package club.banyuan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Team {

    /*
    替换相同位置的球员，从替补位置，放置到首发位置
    */
    public static void replace(List<Player> substitutes, List<Player> starters) {
        Iterator<Player> iterator = substitutes.iterator();
        for (int i = 0; i < substitutes.size(); i++) {
            for (int j = 0; j < starters.size(); j++) {
                if (substitutes.get(i).getLocation().equals(starters.get(j).getLocation())) {
                    Player player = substitutes.get(i);
                    substitutes.set(i, starters.get(j));
                    starters.set(j, player);
                    //substitutes.get(i) = substitutes.get(j);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Player> substitutes = new ArrayList<>();
        List<Player> starters = new ArrayList<>();
    }


}
