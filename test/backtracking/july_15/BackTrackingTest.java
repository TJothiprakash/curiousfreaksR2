package backtracking.july_15;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class BackTrackingTest {

    @Test
    public void testPermuteString() {
        Test1 t = new Test1();
        List<String> out = t.permuteString("abc");
        assertEquals(new HashSet<>(List.of("abc", "acb", "bac", "bca", "cab", "cba")),
                new HashSet<>(out));
    }

    @Test
    public void testPermuteUnique() {
        Test1 t = new Test1();
        List<List<Integer>> out = t.permuteUnique(new int[]{1, 1, 2});
        assertEquals(3, out.size());
    }

    // Optional main() to run manually
    public static void main(String[] args) {
        new BackTrackingTest().testPermuteString();
        new BackTrackingTest().testPermuteUnique();
    }
}
