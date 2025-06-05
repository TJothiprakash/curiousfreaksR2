package fun_facts.bugs.claims;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    public String handleRequest(String inputJson) {
//        Map<String, Object> claims = JsonUtil.parseJson(inputJson);
        Gson gson = new Gson();
        Map<String, Object> claims = gson.fromJson(inputJson, new TypeToken<Map<String, Object>>(){}.getType());

        return claimService.processClaims(claims);
    }
}
 class ClaimService {

    private final TransformationStrategy strategy;
    private final EncoderService encoderService;

    public ClaimService(TransformationStrategy strategy, EncoderService encoderService) {
        this.strategy = strategy;
        this.encoderService = encoderService;
    }

    public String processClaims(Map<String, Object> claims) {
        Map<String, Object> transformed = strategy.transform(claims);
        return encoderService.encode(transformed);  // 🧨 Bug happens somewhere here
    }
}
 interface TransformationStrategy {
    Map<String, Object> transform(Map<String, Object> claims);
}
 class PrefixingStrategy implements TransformationStrategy {
    @Override
    public Map<String, Object> transform(Map<String, Object> claims) {
        return claims.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> "prefix_" + e.getKey(),
                        Map.Entry::getValue
                ));
    }
}
 class EncoderService {

    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public String encode(Map<String, Object> claims) {
        // Step 1: parallelize encoding (unnecessary, just makes it flaky)
        List<Callable<String>> tasks = claims.entrySet().stream()
                .map(e -> (Callable<String>) () -> encodeEntry(e))
                .collect(Collectors.toList());

        List<String> encodedParts = new ArrayList<>();
        try {
            List<Future<String>> futures = executor.invokeAll(tasks);
            for (Future<String> f : futures) {
                encodedParts.add(f.get());  // 💥 concurrency not deterministic
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Step 2: use broken delimiter logic (adds a trailing ".")
        return "{\"token\": \"" + String.join(".", encodedParts) + "\"}";
    }

    private String encodeEntry(Map.Entry<String, Object> entry) {
        String json = "{\"" + entry.getKey() + "\":\"" + entry.getValue().toString() + "\"}";
        return Base64.getUrlEncoder().withoutPadding().encodeToString(json.getBytes(StandardCharsets.UTF_8));
    }
}
