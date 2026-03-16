import java.util.*;

class PageEvent {
    String url;
    String userId;
    String source;

    PageEvent(String url, String userId, String source) {
        this.url = url;
        this.userId = userId;
        this.source = source;
    }
}

public class RealTimeAnalytics {

    static HashMap<String, Integer> pageViews = new HashMap<>();
    static HashMap<String, HashSet<String>> uniqueVisitors = new HashMap<>();
    static HashMap<String, Integer> trafficSources = new HashMap<>();

    public static void processEvent(PageEvent event) {

        pageViews.put(event.url,
                pageViews.getOrDefault(event.url, 0) + 1);

        uniqueVisitors.putIfAbsent(event.url, new HashSet<>());
        uniqueVisitors.get(event.url).add(event.userId);

        trafficSources.put(event.source,
                trafficSources.getOrDefault(event.source, 0) + 1);
    }

    public static void getDashboard() {

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a,b)->b.getValue()-a.getValue());

        pq.addAll(pageViews.entrySet());

        System.out.println("Top Pages:");

        for(int i=1;i<=10 && !pq.isEmpty();i++){
            var e = pq.poll();
            System.out.println(i+". "+e.getKey()+
                    " - "+e.getValue()+
                    " views ("+
                    uniqueVisitors.get(e.getKey()).size()+
                    " unique)");
        }

        System.out.println("\nTraffic Sources:");
        for(String s: trafficSources.keySet()){
            System.out.println(s+" : "+trafficSources.get(s));
        }
    }

    public static void main(String[] args) {

        processEvent(new PageEvent("/article/breaking-news","user1","google"));
        processEvent(new PageEvent("/article/breaking-news","user2","facebook"));
        processEvent(new PageEvent("/sports/championship","user1","direct"));

        getDashboard();
    }
}