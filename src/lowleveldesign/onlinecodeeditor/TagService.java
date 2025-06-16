package lowleveldesign.onlinecodeeditor;

import java.util.List;
import java.util.UUID;

interface TagService {
    void addTagToSnippet(UUID snippetId, String tagName);

    List<Tag> getAllTags();
}

