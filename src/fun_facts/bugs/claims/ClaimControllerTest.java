package fun_facts.bugs.claims;


import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class ClaimControllerTest {

    @Test
    public void testTokenEncoding() {
        ClaimController controller = new ClaimController(
                new ClaimService(new PrefixingStrategy(), new EncoderService())
        );

        String input = "{\"name\":\"mariammal\", \"role\":\"boss\"}";
        String token = controller.handleRequest(input);

        assertTrue(token.contains("token"));
        assertFalse(token.endsWith(".."));  // 💣 Fails sometimes
        System.out.println("Generated: " + token);
    }
}
