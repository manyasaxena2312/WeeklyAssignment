import java.util.*;

class Transaction {

    int id;
    int amount;
    String merchant;
    String time;

    Transaction(int id,int amount,String merchant,String time){
        this.id=id;
        this.amount=amount;
        this.merchant=merchant;
        this.time=time;
    }
}

public class FraudDetection {

    static List<int[]> findTwoSum(
            List<Transaction> list, int target){

        HashMap<Integer, Transaction> map = new HashMap<>();

        List<int[]> result = new ArrayList<>();

        for(Transaction t: list){

            int complement = target - t.amount;

            if(map.containsKey(complement)){
                result.add(new int[]{
                        map.get(complement).id,
                        t.id
                });
            }

            map.put(t.amount, t);
        }

        return result;
    }

    public static void main(String[] args) {

        List<Transaction> list = new ArrayList<>();

        list.add(new Transaction(1,500,"A","10:00"));
        list.add(new Transaction(2,300,"B","10:15"));
        list.add(new Transaction(3,200,"C","10:30"));

        List<int[]> pairs = findTwoSum(list,500);

        for(int[] p: pairs)
            System.out.println(p[0]+" "+p[1]);
    }
}