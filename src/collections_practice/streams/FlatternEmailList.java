package collections_practice.streams;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*Flatten a List<Optional<User>> into distinct emails of active users using flatMap and
 Optional.stream, and
explain how laziness and short-circuiting interact with operations like anyMatch and*/


/*3️⃣ Explanation of flatMap and Optional.stream

Optional.stream() → produces:

a stream with one element if present, or

an empty stream if empty.

flatMap(Optional::stream) “flattens” List<Optional<User>> → Stream<User> automatically removing empty optionals.

You no longer need to check for isPresent() manually.

4️⃣ Laziness & short-circuiting~~
Example: anyMatch(u -> u.isActive)

Stops processing as soon as a match is found, even if the stream has millions of elements.

distinct() is not short-circuiting — it needs~ to see the whole stream to know which elements are unique.

✅ Key takeaways

Use flatMap(Optional::stream) to safely ignore empty optionals.

Use filter → map → distinct to transform and remove duplicates.

Laziness ensures efficient evaluation; short-circuiting can stop the pipeline early for operations like anyMatch or findFirst.~*/
public class FlatternEmailList {

    static class User {
        int id;
        String name;
        String email;
        boolean isActive;

        public User(int id, String name, String email, boolean isActive) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.isActive = isActive;
        }
    }

    public static void main(String[] args) {

        List<Optional<User>> usersList = List.of(
                Optional.of(new User(1, "Alice", "alice@example.com", false)),  // kept
                Optional.of(new User(2, "Bob", "bob@example.com", false)),      // filtered out
                Optional.empty(),                                             // skipped
                Optional.of(new User(3, "Charlie", "charlie@example.com", false)), // kept
                Optional.of(new User(4, "Alice", "alice@example.com", true)));    // duplicate removed
        // Flatten, filter active, map to email, distinct
        List<String> distinctEmails = usersList.stream()
                .flatMap(Optional::stream)      // converts Optional<User> → Stream<User>, empty optionals produce empty stream
                .filter(u -> u.isActive)        // keep only active users
                .map(u -> u.email)              // map to email
                .distinct()                     // remove duplicates
                .toList();

        System.out.println(distinctEmails);

    }
}