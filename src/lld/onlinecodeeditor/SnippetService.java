package lld.onlinecodeeditor;

import java.util.List;
import java.util.UUID;

interface SnippetService {
    Snippet createSnippet(User user, String title, String code, Language lang);
    Snippet updateSnippet(UUID snippetId, String code, User user);
    void deleteSnippet(UUID snippetId, User user);
    List<Snippet> getSnippetsByUser(User user);
    List<Snippet> searchByTagOrTitle(String query);
}

