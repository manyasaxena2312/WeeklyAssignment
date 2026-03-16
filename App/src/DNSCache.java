
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DNSCache {
    class DNSEntry {
        String domain;
        String ipAddress;
        long timestamp;
        long expiryTime;

        DNSEntry(String domain, String ipAddress, long timestamp, long ttl) {
            this.domain = domain;
            this.ipAddress = ipAddress;
            this.timestamp = timestamp;
            this.expiryTime = timestamp + ttl * 1000;
        }
    }

    private HashMap<String, DNSEntry> cache = new HashMap<>();
    private int cacheSize = 100;

    public void resolve(String domain) {
        long currentTime = System.currentTimeMillis();

        // Remove expired entries
        Iterator<Map.Entry<String, DNSEntry>> iterator = cache.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, DNSEntry> entry = iterator.next();
            if (entry.getValue().expiryTime < currentTime) {
                iterator.remove();
            }
        }

        if (cache.containsKey(domain)) {
            System.out.println("Cache HIT → " + cache.get(domain).ipAddress);
        } else {
            // Simulate upstream query
            String ip = "172.217.14." + (int)(Math.random() * 255);
            DNSEntry newEntry = new DNSEntry(domain, ip, currentTime, 300);
            cache.put(domain, newEntry);
            System.out.println("Cache MISS → Query upstream → " + ip);
        }
    }

    public static void main(String[] args) {
        DNSCache cache = new DNSCache();
        cache.resolve("google.com");
        cache.resolve("google.com");
        // simulate passage of time...
    }
}
