package lowleveldesign.june_17.urlshortener;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UrlShortenerService {
    private final Map<String, UrlMetaData> shortToMeta = new ConcurrentHashMap<>();
    private final Map<String, String> longToShort = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();

    // Shorten with auto TTL (optional), custom alias (optional)
    public String shortenUrl(String longUrl, Long ttlMillis, String customAlias) {
        if (customAlias != null && shortToMeta.containsKey(customAlias)) {
            throw new RuntimeException("Alias already in use");
        }

        if (longToShort.containsKey(longUrl)) {
            String existingShort = longToShort.get(longUrl);
            UrlMetaData meta = shortToMeta.get(existingShort);
            if (!meta.isExpired()) return existingShort;
        }

        String shortCode = (customAlias != null) ? customAlias : generateShortCode();

        UrlMetaData meta = new UrlMetaData(longUrl, shortCode, customAlias, ttlMillis != null ? ttlMillis : 0);
        shortToMeta.put(shortCode, meta);
        longToShort.put(longUrl, shortCode);

        return shortCode;
    }

    public String expandUrl(String shortCode, String ip) {
        UrlMetaData meta = shortToMeta.get(shortCode);
        if (meta == null) throw new RuntimeException("Short URL not found");

        if (meta.isExpired()) {
            shortToMeta.remove(shortCode);
            longToShort.remove(meta.longUrl);
            throw new RuntimeException("Short URL has expired");
        }

        meta.recordAccess(ip);
        return meta.longUrl;
    }

    private String generateShortCode() {
        long id = counter.incrementAndGet();
        return encodeBase62(id);
    }

    private String encodeBase62(long id) {
        String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            sb.append(base62.charAt((int)(id % 62)));
            id /= 62;
        }
        return sb.reverse().toString();
    }

    public UrlMetaData getMeta(String shortCode) {
        return shortToMeta.get(shortCode);
    }
}

