package collections_practice.utilitymethods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PracticeUtilityMethods {
    /*Problem 1: Event Scheduling with Rotations
Scenario:
You are managing a list of events for a conference. The events are scheduled in a fixed order:
["Opening Ceremony", "Keynote Speech", "Networking Break", "Panel Discussion", "Closing Ceremony"].

Task:
Rotate the events by a specified number of positions to create variations in schedules for different days.
Example: Rotate by 2 → ["Panel Discussion", "Closing Ceremony", "Opening Ceremony", "Keynote Speech", "Networking Break"].
Print the new schedule.
Methods to Use:
Collections.rotate
*/
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("Opening Ceremony", "Keynote Speech", "Networking Break", "Panel Discussion", "Closing Ceremony"));
        System.out.println("Original Schedule: " + list);
        int rotations = 2;
        Collections.rotate(list, 2);
        System.out.println("Rotated list " + list);

        /*Problem 2: Student Grades Analysis
Scenario:
You are given a list of student grades:
[85, 92, 76, 81, 92, 85, 92].

Task:
Find the highest and lowest grades.
Count how many times the grade 92 appears.
Replace all instances of the lowest grade with 50 (minimum passing score).
Methods to Use:
Collections.max, Collections.min, Collections.frequency, Collections.replaceAll
*/
        List<Integer> studentGrades = new ArrayList<>(List.of(85, 92, 76, 81, 92, 85, 92));
        int maxGrade = Collections.max(studentGrades);
        int minGrade = Collections.min(studentGrades);
        int frequency = Collections.frequency(studentGrades, 92);
        System.out.println(studentGrades);
        Collections.replaceAll(studentGrades, minGrade, 50);
        System.out.println(studentGrades);
        System.out.println("Max Grade: " + maxGrade + ", Min Grade: " + minGrade + ", Frequency of 92: " + frequency);



        /*Problem 3: Thread-Safe Task List
Scenario:
A shared task list is being used by multiple threads to manage project tasks:
["Design", "Development", "Testing", "Deployment"].

Task:
Convert the task list into a thread-safe collection.
Demonstrate adding and removing tasks in a thread-safe manner.
Methods to Use:
Collections.synchronizedList
*/

        List<String> taskList = new ArrayList<>(List.of("Design", "Developemetn", "Testing", "Deployment"));
        List<String> synchronizedList = Collections.synchronizedList(taskList);
        System.out.println(synchronizedList);
        synchronizedList.add("Bug Fixing");
        System.out.println(synchronizedList);
        synchronizedList.remove("Testing");
        System.out.println(synchronizedList);


    /*Problem 4: Inventory Management
Scenario:
You are managing an inventory for an e-commerce store. The inventory contains product IDs:
[101, 102, 103, 101, 104, 105, 101].

Task:
Determine the most sold product (most frequent ID).
Create a copy of the inventory to process refunds without modifying the original list.
Methods to Use:
Collections.frequency, Collections.copy
*/  // Original inventory
        List<Integer> inventory = new ArrayList<>(List.of(101, 102, 103, 101, 104, 105, 101));

        // Step 1: Determine the most sold product (most frequent ID)
        int mostSold = 0;
        int maxFrequency = 0;
        for (int product : inventory) {
            int frequencyCount = Collections.frequency(inventory, product);
            if (frequencyCount > maxFrequency) {
                maxFrequency = frequencyCount;
                mostSold = product;
            }
        }
        System.out.println("Most Sold Product ID: " + mostSold + " (Frequency: " + maxFrequency + ")");

        // Step 2: Create a copy of the inventory for refunds
        List<Integer> inventoryCopy = new ArrayList<>(Collections.nCopies(inventory.size(), 0)); // Initialize with placeholders
        Collections.copy(inventoryCopy, inventory); // Copy content
        System.out.println("Original Inventory: " + inventory);
        System.out.println("Copy of Inventory (for refunds): " + inventoryCopy);

        // Step 3: Verify modifications to the original do not affect the copy
        inventory.set(0, 999); // Change the first item in the original
        System.out.println("Modified Original Inventory: " + inventory);
        System.out.println("Unchanged Copy of Inventory: " + inventoryCopy);



        /*Problem 5: Circular Seating Arrangement
Scenario:
A circular table has participants seated in the following order:
["Alice", "Bob", "Charlie", "Diana", "Eve"].

Task:
Swap two participants at specified positions (e.g., swap positions 1 and 4 → "Alice", "Eve", "Charlie", "Diana", "Bob").
Rotate the seating arrangement clockwise by 3 positions.
Methods to Use:
Collections.swap, Collections.rotate

*/
        List<String> circulatTable = new LinkedList<>(List.of("Alice", "Bob", "Charlie", "Diana", "Eve"));
        System.out.println("Original Seating Arrangement: " + circulatTable);
        Collections.swap(circulatTable, 1, 4);

       /* System.out.println("After Swapping 1 and 4: " + circulatTable);
        System.out.println("Before rotating : ");
        System.out.println(circulatTable);
        Collections.rotate(circulatTable, 3);
        System.out.println("After rotating : "+circulatTable);
        Collections.rotate(circulatTable, -3);
        System.out.println("After rotating : "+circulatTable);
       */ /*
Problem 6: Playlist Management
Scenario:
You are managing a playlist for a music player app. The playlist is:
["Song A", "Song B", "Song C", "Song D", "Song E"].

Task:
Shuffle the playlist randomly.
Find and play the first song alphabetically.
Replace all instances of a skipped song with "Song Skipped".
Methods to Use:
Collections.shuffle, Collections.min, Collections.replaceAll
*/
        List<String> playlist = new ArrayList<>(List.of("Song A", "Song B", "Song C", "Song D", "Song E"));
        System.out.println("Original Playlist: " + playlist);
        Collections.shuffle(playlist);
        System.out.println("Shuffled Playlist: " + playlist);
        String firstSong = Collections.min(playlist);
        System.out.println("First Song: " + firstSong);
        Collections.replaceAll(playlist, "Song D", "Song Skipped");
        System.out.println("Playlist after replacing 'Song D': " + playlist);
        /*
Problem 7: Comparing Employee Lists
Scenario:
You have two lists of employees:

List 1 (Team A): ["Alice", "Bob", "Charlie"]
List 2 (Team B): ["Diana", "Eve", "Charlie"].
Task:
Check if the two lists have any common employees.
Print the names of employees who belong to both teams.
Methods to Use:
Collections.disjoint
*/
        List<String> teamA = new ArrayList<>(List.of("Alice", "Bob", "Charlie"));
        List<String> teamB = new ArrayList<>(List.of("Diana", "Eve", "Jhon"));
        System.out.println("Team A: " + teamA);
        System.out.println("Team B: " + teamB);
        boolean areDisjoint = Collections.disjoint(teamA, teamB);
        System.out.println("Are the two lists disjoint? " + areDisjoint);
        /*
Problem 8: Generating Default Data
Scenario:
A form is used to collect survey responses. If a question is unanswered, it should display a default value:
"Not Answered".

Task:
Create a list of 10 responses all initialized to "Not Answered".
Update specific responses as they are provided (e.g., replace index 3 with "Yes" and index 5 with "No").
Methods to Use:
Collections.nCopies, List.set
*/
        List<String> formData = new ArrayList<>(Collections.nCopies(10, "Unanswered"));
        System.out.println(formData);
        formData.set(3, "yes");
        formData.set(4, "no");
       // formData.set(10,"yes");
        System.out.println(formData);

    }
}