package JavaBattleSim;

import java.util.ArrayList;

public class  BackPack<T> extends ArrayList<T>  {
public T getItem(Item item){
    for (T other:this){
        if (other.equals(item)){
            return other;
        }
    }
    return null;
}

}
