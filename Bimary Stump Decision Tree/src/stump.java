import java.util.HashMap;

public class stump {


    public HashMap<Integer,Integer> mymap;
    int index;
    public stump(int p)
    {
        mymap=new HashMap<>();
        index=p;
    }
    int calc(ulti p)
    {
         return mymap.get(p.attributes[index]);
    }
}
