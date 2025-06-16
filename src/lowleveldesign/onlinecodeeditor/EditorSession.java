package lowleveldesign.onlinecodeeditor;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

class EditorSession {
    UUID sessionId;
    Snippet snippet;
    List<User> collaborators;
    Map<UUID, CursorPosition> userCursors;
    Date lastActivity;
}

