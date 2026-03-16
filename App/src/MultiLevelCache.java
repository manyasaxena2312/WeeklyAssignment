import java.util.*;

class LRUCache<K,V> extends LinkedHashMap<K,V>{

    int capacity;

    LRUCache(int capacity){
        super(capacity,0.75f,true);
        this.capacity = capacity;
    }

    protected boolean removeEldestEntry(
            Map.Entry<K,V> eldest){

        return size() > capacity;
    }
}

public class MultiLevelCache {

    static LRUCache<String,String> L1 =
            new LRUCache<>(10000);

    static HashMap<String,String> L2 =
            new HashMap<>();

    static HashMap<String,String> database =
            new HashMap<>();

    static String getVideo(String id){

        if(L1.containsKey(id)){
            System.out.println("L1 HIT");
            return L1.get(id);
        }

        if(L2.containsKey(id)){
            System.out.println("L2 HIT");

            String data = L2.get(id);
            L1.put(id,data);
            return data;
        }

        System.out.println("L3 DATABASE HIT");

        String data = database.get(id);

        if(data!=null)
            L2.put(id,data);

        return data;
    }

    public static void main(String[] args) {

        database.put("video1","Movie Data");

        getVideo("video1");
        getVideo("video1");
    }
}
