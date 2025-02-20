package collections_practice.streams.exercise_2;

import java.util.ArrayList;
import java.util.List;

public class Theatre {
    private String name;
    private String city;
    private int capacity;
    private String owner;
    private String address;
    List<Movie> movies = new ArrayList<>();
    public Theatre(String name, String city, int capacity, String owner, String address) {
        this.name = name;
        this.city = city;
        this.capacity = capacity;
        this.owner = owner;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getOwner() {
        return owner;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Theatre{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", capacity=" + capacity +
                ", owner='" + owner + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}
