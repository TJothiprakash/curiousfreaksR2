package lowleveldesign.onlinecodeeditor;

import jdk.jshell.Snippet;

import java.util.List;
import java.util.UUID;

class User {
    UUID id;
    String username;
    String email;
    String passwordHash;
    List<Snippet> ownedSnippets;
    List<EditorSession> activeSessions;
}

/*// Step 1: Core Models

import java.util.*;

enum Language {
    JAVA, PYTHON, CPP
}

enum Permission {
    PRIVATE, PUBLIC, SHARED
}

class User {
    UUID id;
    String username;
    String email;
    String passwordHash;
    List<Snippet> ownedSnippets = new ArrayList<>();
    List<EditorSession> activeSessions = new ArrayList<>();
}

class Snippet {
    UUID id;
    String title;
    User owner;
    List<Version> versions = new ArrayList<>();
    Language language;
    List<Tag> tags = new ArrayList<>();
    Permission permission;
    Date createdAt;
    Date updatedAt;

    public Snippet(String title, User owner, String code, Language lang) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.owner = owner;
        this.language = lang;
        this.permission = Permission.PRIVATE;
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.versions.add(new Version(1, code));
    }
}

class Version {
    int versionNumber;
    String code;
    Date timestamp;

    public Version(int versionNumber, String code) {
        this.versionNumber = versionNumber;
        this.code = code;
        this.timestamp = new Date();
    }
}

class Tag {
    UUID id = UUID.randomUUID();
    String name;

    public Tag(String name) {
        this.name = name;
    }
}

class ExecutionRequest {
    String code;
    Language language;
    Map<String, String> inputParams = new HashMap<>();
}

class ExecutionResult {
    String output;
    String error;
    int exitCode;
    long executionTimeMs;
}

class EditorSession {
    UUID sessionId = UUID.randomUUID();
    Snippet snippet;
    List<User> collaborators = new ArrayList<>();
    Map<UUID, CursorPosition> userCursors = new HashMap<>();
    Date lastActivity = new Date();
}

class CursorPosition {
    int line;
    int column;

    public CursorPosition(int line, int column) {
        this.line = line;
        this.column = column;
    }
}

// Step 2: Services

interface UserService {
    User register(String username, String email, String password);
    User login(String username, String password);
}

class InMemoryUserService implements UserService {
    private final Map<String, User> userMap = new HashMap<>();

    public User register(String username, String email, String password) {
        if (userMap.containsKey(username)) throw new RuntimeException("Username already exists");
        User user = new User();
        user.id = UUID.randomUUID();
        user.username = username;
        user.email = email;
        user.passwordHash = password;
        userMap.put(username, user);
        return user;
    }

    public User login(String username, String password) {
        User user = userMap.get(username);
        if (user == null || !user.passwordHash.equals(password)) throw new RuntimeException("Invalid credentials");
        return user;
    }
}

interface SnippetService {
    Snippet createSnippet(User user, String title, String code, Language lang);
    Snippet updateSnippet(UUID snippetId, String code, User user);
    void deleteSnippet(UUID snippetId, User user);
    List<Snippet> getSnippetsByUser(User user);
}

class InMemorySnippetService implements SnippetService {
    private final Map<UUID, Snippet> snippetMap = new HashMap<>();

    public Snippet createSnippet(User user, String title, String code, Language lang) {
        Snippet snippet = new Snippet(title, user, code, lang);
        user.ownedSnippets.add(snippet);
        snippetMap.put(snippet.id, snippet);
        return snippet;
    }

    public Snippet updateSnippet(UUID snippetId, String code, User user) {
        Snippet snippet = snippetMap.get(snippetId);
        if (snippet == null || !snippet.owner.equals(user)) throw new RuntimeException("Not allowed");
        int version = snippet.versions.size() + 1;
        snippet.versions.add(new Version(version, code));
        snippet.updatedAt = new Date();
        return snippet;
    }

    public void deleteSnippet(UUID snippetId, User user) {
        Snippet snippet = snippetMap.get(snippetId);
        if (snippet != null && snippet.owner.equals(user)) {
            snippetMap.remove(snippetId);
            user.ownedSnippets.remove(snippet);
        }
    }

    public List<Snippet> getSnippetsByUser(User user) {
        return new ArrayList<>(user.ownedSnippets);
    }
}

// ExecutionService (Mock)

interface ExecutionService {
    ExecutionResult execute(ExecutionRequest request);
}

class MockExecutionService implements ExecutionService {
    public ExecutionResult execute(ExecutionRequest request) {
        ExecutionResult result = new ExecutionResult();
        result.output = "Executed " + request.language + " code.";
        result.exitCode = 0;
        result.executionTimeMs = 123;
        return result;
    }
}

// Next Steps: TagService, EditorSessionService, SearchService, Test Console UI*/