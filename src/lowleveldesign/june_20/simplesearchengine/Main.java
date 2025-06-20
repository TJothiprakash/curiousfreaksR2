package lowleveldesign.june_20.simplesearchengine;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        SearchEngine engine = new SearchEngine();

        System.out.println("🔍 Welcome to MiniSearch CLI");
        while (true) {
            System.out.print("\n[1] Add Document\n[2] Search\n[3] Exit\nChoose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter file path: ");
                    String path = scanner.nextLine();
                    engine.addDocument(path);
                    break;

                case "2":
                    System.out.print("Enter search query: ");
                    String query = scanner.nextLine();
                    List<Result> results = engine.search(query);

                    if (results.isEmpty()) {
                        System.out.println("❌ No results found.");
                    } else {
                        System.out.println("🔎 Results:");
                        for (Result r : results) {
                            Document doc = engine.getDocumentById(r.getDocId());
                            System.out.println("- " + doc.getName() + " | Score: " + r.getScore());
                        }
                    }
                    break;

                case "3":
                    System.out.println("👋 Exiting. Goodbye!");
                    return;

                default:
                    System.out.println("❗ Invalid choice. Try again.");
            }
        }
    }
}


class SearchEngine {
    private InvertedIndex invertedIndex;
    private Map<Integer, Document> docStore;
    private AtomicInteger idGenerator;

    public void addDocument(String filePath) throws IOException {
        try {
            // 1. Detect the reader
            DocumentReader reader = DocumentReaderFactory.getReader(filePath);
            // 2. Extract content
            String content = reader.extractText(filePath);
            // 3. Generate ID and extract metadata
            int docId = idGenerator.getAndIncrement();
            String name = Paths.get(filePath).getFileName().toString();
            String format = filePath.substring(filePath.lastIndexOf('.') + 1).toLowerCase();
            // 4. Create Document
            Document doc = new Document(docId, name, content, format);
            // 5. Store Document
            docStore.put(docId, doc);
            // 6. Index it
            invertedIndex.indexDocument(doc);
            System.out.println("✅ Document added: " + name + " (ID: " + docId + ")");
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Unsupported document format: " + filePath);
        }
    }

    public List<Result> search(String query) {
        String normalized = query.toLowerCase().trim();

        List<Posting> postings = invertedIndex.search(normalized);

        List<Result> results = new ArrayList<>();
        for (Posting p : postings) {
            results.add(new Result(p.getDocId(), p.getFrequency()));
        }

        // Sort by score descending
        results.sort((r1, r2) -> Integer.compare(r2.getScore(), r1.getScore()));

        return results;
    }


    public Document getDocumentById(int docId) {
        return docStore.get(docId);
    }
}

class Result {
    private int docId;
    private int score;

    public Result(int docId, int score) {
        this.docId = docId;
        this.score = score;
    }

    public int getDocId() {
        return docId;
    }

    public int getScore() {
        return score;
    }
}


class HtmlReader implements DocumentReader {
    @Override
    public String extractText(String filePath) {
        return null;
    }
}

class WordReader implements DocumentReader {
    @Override
    public String extractText(String filePath) {
        return null;
    }
}

class TextReader implements DocumentReader {
    @Override
    public String extractText(String filePath) {
        return null;
    }
}

class Document {
    private int id;
    private String name;
    private String content;
    private String format;

    public Document(int id, String name, String content, String format) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.format = format;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getFormat() {
        return format;
    }
}


interface DocumentReader {
    String extractText(String filePath);
}

class DocumentReaderFactory {

    public static DocumentReader getReader(String filePath) {
        if (filePath.endsWith(".html") || filePath.endsWith(".htm")) {
            return new HtmlReader();
        } else if (filePath.endsWith(".docx")) {
            return new WordReader();
        } else if (filePath.endsWith(".txt")) {
            return new TextReader();
        } else {
            throw new IllegalArgumentException("Unsupported format: " + filePath);
        }
    }
}

class InvertedIndex {
    private Map<String, List<Posting>> index = new HashMap<>();

    public void indexDocument(Document doc) {
        String content = doc.getContent().toLowerCase(); // normalize
        int docId = doc.getId();

        // Step 1: Tokenize
        String[] words = content.split("\\W+"); // split on non-word characters

        // Step 2: Count word frequencies
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            if (word.isBlank()) continue;
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        // Step 3: Add to inverted index
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            String word = entry.getKey();
            int frequency = entry.getValue();

            index.computeIfAbsent(word, k -> new ArrayList<>())
                    .add(new Posting(docId, frequency));
        }
    }

    public List<Posting> search(String word) {
        String normalized = word.toLowerCase(); // basic normalization
        return index.getOrDefault(normalized, Collections.emptyList());
    }

}

class Posting {
    private int docId;
    private int frequency;

    public Posting(int docId, int frequency) {
        this.docId = docId;
        this.frequency = frequency;
    }

    public int getDocId() {
        return docId;
    }

    public int getFrequency() {
        return frequency;
    }
}

/*+-----------------------+
|     SearchEngine      |
+-----------------------+
| - index: InvertedIndex
| - docStore: Map<Integer, Document>
| - idGenerator: AtomicInteger
+-----------------------+
| + addDocument(String filePath): void
| + search(String query): List<Result>
+-----------------------+

           |
           | uses
           v

+-----------------------+           +------------------------+
|    DocumentReader     |<----------| DocumentReaderFactory  |
+-----------------------+           +------------------------+
| + extractText(String): String     | + getReader(String): DocumentReader
+-----------------------+           +------------------------+
          /|\
           |
   ---------------------------
   |           |            |
   v           v            v
+--------------+   +----------------+   +------------------+
| TextReader   |   | HtmlReader     |   | WordReader       |
+--------------+   +----------------+   +------------------+
| + extractText()| | + extractText()|   | + extractText()   |
+--------------+   +----------------+   +------------------+

+-----------------------+
|      Document         |
+-----------------------+
| - id: int             |
| - name: String        |
| - content: String     |
| - format: String      |
+-----------------------+

+-----------------------+
|     InvertedIndex     |
+-----------------------+
| - index: Map<String, List<Posting>>
+-----------------------+
| + indexDocument(Document)
| + search(String): List<Posting>
+-----------------------+

+-----------------------+
|      Posting          |
+-----------------------+
| - docId: int          |
| - frequency: int      |
+-----------------------+

+-----------------------+
|       Result          |
+-----------------------+
| - docId: int          |
| - score: int          |
+-----------------------+
*/