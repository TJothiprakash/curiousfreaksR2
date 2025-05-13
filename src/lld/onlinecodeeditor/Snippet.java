package lld.onlinecodeeditor;

import java.security.Permission;
import java.util.Date;
import java.util.List;
import java.util.UUID;

class Snippet {
    UUID id;
    String title;
    User owner;
    List<Version> versions;
    Language language;
    List<Tag> tags;
    Permission permission;
    Date createdAt;
    Date updatedAt;
}

