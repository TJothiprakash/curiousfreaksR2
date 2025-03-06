package collections_practice.streams;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class XMLStreamProcessor {
    public static void main(String[] args) {
        try {
            File file = new File("employees.xml"); // Load XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            // Extract all <employee> elements
            NodeList nodeList = doc.getElementsByTagName("employee");

            // Process XML using Streams
            List<EmployeeObject> employees = IntStream.range(0, nodeList.getLength())
                    .mapToObj(i -> (Element) nodeList.item(i)) // Convert NodeList to Stream of Elements
                    .map(XMLStreamProcessor::parseEmployee)  // Map to Employee objects
                    .collect(Collectors.toList());

            // Print processed employees
            employees.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to convert XML Element to Employee object
    private static EmployeeObject parseEmployee(Element element) {
        int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
        String name = element.getElementsByTagName("name").item(0).getTextContent();
        int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
        String department = element.getElementsByTagName("department").item(0).getTextContent();
        return new EmployeeObject(id, name, age, department);
    }
}

// Employee Model Class
class EmployeeObject {
    private int id;
    private String name;
    private int age;
    private String department;

    public EmployeeObject(int id, String name, int age, String department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee { ID: " + id + ", Name: " + name + ", Age: " + age + ", Dept: " + department + " }";
    }
}
