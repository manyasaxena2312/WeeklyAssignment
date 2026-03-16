import java.util.*;

class TokenBucket {

    int tokens;
    int maxTokens;
    double refillRate;
    long lastRefillTime;

    TokenBucket(int maxTokens, double refillRate) {
        this.maxTokens = maxTokens;
        this.refillRate = refillRate;
        this.tokens = maxTokens;
        this.lastRefillTime = System.currentTimeMillis();
    }

    synchronized boolean allowRequest() {

        refill();

        if(tokens > 0){
            tokens--;
            return true;
        }
        return false;
    }

    void refill(){

        long now = System.currentTimeMillis();
        double tokensToAdd =
                (now - lastRefillTime)/1000.0 * refillRate;

        tokens = Math.min(maxTokens,
                tokens + (int)tokensToAdd);

        lastRefillTime = now;
    }
}

public class RateLimiter {

    static HashMap<String, TokenBucket> clients = new HashMap<>();

    static boolean checkRateLimit(String clientId){

        clients.putIfAbsent(clientId,
                new TokenBucket(1000, 1000.0/3600));

        return clients.get(clientId).allowRequest();
    }

    public static void main(String[] args) {

        for(int i=0;i<5;i++){
            System.out.println(
                    checkRateLimit("abc123")
                            ? "Allowed"
                            : "Denied"
            );
        }
    }
}