package collections_practice.streams.exercise_3;


public class Person {
    String name;
    String city;
    int id;
    boolean isEmployed;
    int age;


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", id=" + id +
                ", isEmployed=" + isEmployed +
                ", age=" + age +
                '}';
    }

    public int getAge() {
        if (age == 0) System.out.println("NO data found for age , please set the age for +" + this + " person ");
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person(String name, String city, int id) {
        this.name = name;
        this.city = city;
        this.id = id;
        this.isEmployed = false;

    }


    public Person(String name, String city, int id, int age) {
        this.name = name;
        this.city = city;
        this.id = id;
        this.isEmployed = false;
        this.age = age;

    }

    public Person(String name, String city, int id, boolean isEmployed) {
        this.name = name;
        this.city = city;
        this.id = id;
        this.isEmployed = isEmployed;

    }

    public Person(String name, String city, int id, boolean isEmployed, int age) {
        this.name = name;
        this.city = city;
        this.id = id;
        this.isEmployed = isEmployed;
        this.age = age;


    }

    public boolean isEmployed() {
        return this.isEmployed;
    }

    public void setEmployed(boolean employed) {
        this.isEmployed = employed;
    }
}
