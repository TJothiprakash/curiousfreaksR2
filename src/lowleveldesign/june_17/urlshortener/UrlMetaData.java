package lowleveldesign.june_17.urlshortener;

import java.util.ArrayList;
import java.util.List;

public class UrlMetaData {
    String longUrl;
    String shortUrl;
    String customAlias;
    long expiryTime; // in ms
    long createdTime;
    int hitCount;
    long lastAccessTime;
    List<String> accessIps;

    public UrlMetaData(String longUrl, String shortUrl, String customAlias, long expiryTime) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.customAlias = customAlias;
        this.expiryTime = expiryTime;
        this.createdTime = System.currentTimeMillis();
        this.hitCount = 0;
        this.accessIps = new ArrayList<>();
    }

    public boolean isExpired() {
        return expiryTime > 0 && System.currentTimeMillis() > createdTime + expiryTime;
    }

    public void recordAccess(String ip) {
        hitCount++;
        lastAccessTime = System.currentTimeMillis();
        accessIps.add(ip);
    }
}
