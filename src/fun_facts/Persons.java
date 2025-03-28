package fun_facts;

import java.util.ArrayList;
import java.util.List;

public class Persons {
    List<Person> personList;

    public Persons() {
        this.personList = new ArrayList<>();
    }

    public void addPerson(Person person) {
        personList.add(person);
    }

    public Person getPersonById(int id) {
        for (Person person : personList) {
            if (person.id() == id) {
                return person;
            }
        }
        return null;
    }

    public String getAllPersons() {
        StringBuilder sb = new StringBuilder();
        for (Person person : personList) {
            sb.append(person.toString()).append("\n");
        }
        return sb.toString();
    }

}
