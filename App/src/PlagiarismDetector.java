import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PlagiarismDetector {

    private HashMap<String, Set<String>> nGramMap = new HashMap<>();

    public void addDocument(String docId, String[] words, int n) {

        for (int i = 0; i <= words.length - n; i++) {

            StringBuilder ngram = new StringBuilder();

            for (int j = 0; j < n; j++) {
                ngram.append(words[i + j]).append(" ");
            }

            String key = ngram.toString().trim();

            nGramMap.putIfAbsent(key, new HashSet<>());
            nGramMap.get(key).add(docId);
        }
    }

    public void analyzeDocuments() {

        System.out.println("Common phrases between documents:\n");

        for (String phrase : nGramMap.keySet()) {

            Set<String> docs = nGramMap.get(phrase);

            if (docs.size() > 1) {
                System.out.println(
                        "\"" + phrase + "\" found in: " + docs
                );
            }
        }
    }

    public static void main(String[] args) {

        PlagiarismDetector detector = new PlagiarismDetector();

        String doc1 = "This is a sample document for testing plagiarism detection";
        String doc2 = "This sample document is useful for plagiarism testing";

        String[] words1 = doc1.toLowerCase().split(" ");
        String[] words2 = doc2.toLowerCase().split(" ");

        int n = 2;

        detector.addDocument("Doc1", words1, n);
        detector.addDocument("Doc2", words2, n);

        detector.analyzeDocuments();
    }
}