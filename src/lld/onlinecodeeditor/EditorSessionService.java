package lld.onlinecodeeditor;

import java.util.UUID;

interface EditorSessionService {
    EditorSession startSession(UUID snippetId, User user);
    void joinSession(UUID sessionId, User user);
    void updateCursor(UUID sessionId, UUID userId, CursorPosition position);
    void broadcastChanges(UUID sessionId, String code);
}

