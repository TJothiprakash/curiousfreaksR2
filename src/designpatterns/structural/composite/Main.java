package designpatterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Practice student = new Student();
        Practice cricketer = new Cricketer();

        TeamIndia teamIndia = new TeamIndia();
        teamIndia.addMember((designpatterns.structural.composite.Practice) student);
        teamIndia.addMember((designpatterns.structural.composite.Practice) cricketer);

        teamIndia.practice();
    }
}


// Common Interface
interface Practice {
    void practice();
}

// Leaf
class Student implements Practice {
    @Override
    public void practice() {
        System.out.println("Student: Practices 2-3 hours per day after school");
    }
}

// Leaf
class Cricketer implements Practice {
    @Override
    public void practice() {
        System.out.println("Cricketer: Practices 6 hours per day");
    }
}

// Composite
class TeamIndia implements Practice {
    private List<Practice> teamMembers = new ArrayList<>();

    public void addMember(Practice member) {
        teamMembers.add(member);
    }

    public void removeMember(Practice member) {
        teamMembers.remove(member);
    }

    @Override
    public void practice() {
        System.out.println("TeamIndia: Instructing all team members to practice...");
        for (Practice member : teamMembers) {
            member.practice(); // Uniform behavior
        }
    }
}
