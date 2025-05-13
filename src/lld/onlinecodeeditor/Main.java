package lld.onlinecodeeditor;

public class Main {


}




/*+---------------------+
|       User          |
+---------------------+
| id, name, role      |
| problemsSolved      |
+---------------------+

+---------------------+
|      Problem        |
+---------------------+
| id, title, desc     |
| testCases           |
| difficulty, tags    |
+---------------------+

+---------------------+
|    CodeSubmission   |
+---------------------+
| id, userId, problemId |
| language, code       |
| result, timestamp    |
+---------------------+

+---------------------+
|    ExecutionEngine  |
+---------------------+
| runCode()           |
+---------------------+

+---------------------+
|    Leaderboard      |
+---------------------+
| getTopUsers()       |
+---------------------+

+---------------------+
|     CodeEditor      |
+---------------------+
| edit, save, submit  |
+---------------------+
*/

/*import java.util.*;

enum Role { USER, ADMIN }

class User {
    String id;
    String name;
    Role role;
    int problemsSolved = 0;

    public User(String id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
}

class TestCase {
    String input;
    String expectedOutput;

    public TestCase(String input, String expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }
}

class Problem {
    String id;
    String title;
    String description;
    List<TestCase> testCases = new ArrayList<>();
    String difficulty;
    Set<String> tags;

    public Problem(String id, String title, String description, String difficulty) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.tags = new HashSet<>();
    }

    public void addTestCase(TestCase tc) {
        testCases.add(tc);
    }
}

class CodeSubmission {
    String id;
    String userId;
    String problemId;
    String language;
    String code;
    String result;
    Date timestamp;

    public CodeSubmission(String id, String userId, String problemId, String language, String code, String result) {
        this.id = id;
        this.userId = userId;
        this.problemId = problemId;
        this.language = language;
        this.code = code;
        this.result = result;
        this.timestamp = new Date();
    }
}

class ExecutionEngine {
    public static String runCode(String code, String language, String input) {
        // Stub: In real-world, send to Docker container/sandbox
        return "MockOutput"; // Always same output for demo
    }

    public static boolean validate(String output, String expected) {
        return output.equals(expected);
    }
}

class Leaderboard {
    Map<String, Integer> solvedMap = new HashMap<>();

    public void record(String userId) {
        solvedMap.put(userId, solvedMap.getOrDefault(userId, 0) + 1);
    }

    public List<String> getTopUsers() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(solvedMap.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());
        List<String> top = new ArrayList<>();
        for (Map.Entry<String, Integer> e : list) top.add(e.getKey() + " -> " + e.getValue());
        return top;
    }
}

class CodeEditor {
    Map<String, User> users = new HashMap<>();
    Map<String, Problem> problems = new HashMap<>();
    Map<String, List<CodeSubmission>> submissions = new HashMap<>();
    Leaderboard leaderboard = new Leaderboard();

    public void registerUser(String id, String name, Role role) {
        users.put(id, new User(id, name, role));
    }

    public void addProblem(String id, String title, String desc, String difficulty) {
        problems.put(id, new Problem(id, title, desc, difficulty));
    }

    public void addTestCaseToProblem(String problemId, String input, String expected) {
        problems.get(problemId).addTestCase(new TestCase(input, expected));
    }

    public void submitCode(String userId, String problemId, String code, String language) {
        Problem p = problems.get(problemId);
        boolean allPassed = true;
        for (TestCase tc : p.testCases) {
            String output = ExecutionEngine.runCode(code, language, tc.input);
            if (!ExecutionEngine.validate(output, tc.expectedOutput)) {
                allPassed = false;
                break;
            }
        }
        String result = allPassed ? "Accepted" : "Wrong Answer";
        submissions.putIfAbsent(userId, new ArrayList<>());
        submissions.get(userId).add(new CodeSubmission(UUID.randomUUID().toString(), userId, problemId, language, code, result));

        if (allPassed) {
            users.get(userId).problemsSolved++;
            leaderboard.record(userId);
        }
        System.out.println("Submission result: " + result);
    }

    public void showSubmissions(String userId) {
        List<CodeSubmission> userSubs = submissions.getOrDefault(userId, new ArrayList<>());
        for (CodeSubmission s : userSubs) {
            System.out.println(s.timestamp + " | Problem: " + s.problemId + " | Result: " + s.result);
        }
    }

    public void showLeaderboard() {
        System.out.println("Leaderboard:");
        for (String line : leaderboard.getTopUsers()) {
            System.out.println(line);
        }
    }
}*/