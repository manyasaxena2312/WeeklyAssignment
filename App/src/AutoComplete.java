import java.util.*;

class TrieNode {

    HashMap<Character, TrieNode> children = new HashMap<>();
    boolean isEnd;
    int frequency;
}

public class AutoComplete {

    static TrieNode root = new TrieNode();

    static void insert(String word){

        TrieNode node = root;

        for(char c: word.toCharArray()){
            node.children.putIfAbsent(c,new TrieNode());
            node = node.children.get(c);
        }

        node.isEnd = true;
        node.frequency++;
    }

    static void dfs(TrieNode node, String prefix,
                    PriorityQueue<String> pq){

        if(node.isEnd)
            pq.add(prefix);

        for(char c: node.children.keySet()){
            dfs(node.children.get(c),
                    prefix + c, pq);
        }
    }

    static List<String> search(String prefix){

        TrieNode node = root;

        for(char c: prefix.toCharArray()){
            if(!node.children.containsKey(c))
                return new ArrayList<>();
            node = node.children.get(c);
        }

        PriorityQueue<String> pq =
                new PriorityQueue<>();

        dfs(node,prefix,pq);

        List<String> result = new ArrayList<>();

        for(int i=0;i<10 && !pq.isEmpty();i++)
            result.add(pq.poll());

        return result;
    }

    public static void main(String[] args) {

        insert("java");
        insert("javascript");
        insert("java tutorial");

        System.out.println(search("jav"));
    }
}