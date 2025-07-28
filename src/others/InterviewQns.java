package others;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InterviewQns {
    public static void main(String[] args) {

        String [] arr = {"hi",""};

        List<String> filteredList =  Arrays.stream(arr).filter(s-> s !=null && !s.isEmpty() ).collect(Collectors.toList());
        System.out.println(filteredList.getClass());
        System.out.println(filteredList);
    }
}
