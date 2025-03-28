package fun_facts;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person(1, "arvind");
        Person p2 = new Person(2, "jothiprakash");
        System.out.println(p1.name());
       // p1.name() = "arunkumar ";

        System.out.println(p2.id());
        System.out.println(p1.equals(p2));
        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
        Persons personsList = new Persons();
        personsList.addPerson(p1);
        personsList.addPerson(p2);

        System.out.println(p1.toString());
        System.out.print("hashcode  : ");
        System.out.print(p1.hashCode());

        System.out.println();
        System.out.println();
        System.out.println("All persons:");
        System.out.println(personsList.getAllPersons());

    }






}
