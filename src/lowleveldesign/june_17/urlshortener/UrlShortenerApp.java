package lowleveldesign.june_17.urlshortener;

public class UrlShortenerApp {
    public static void main(String[] args) throws InterruptedException {
        UrlShortenerService service = new UrlShortenerService();

        String short1 = service.shortenUrl("https://openai.com", 5000L, null);
        System.out.println("Shortened: " + short1);

        String resolved = service.expandUrl(short1, "127.0.0.1");
        System.out.println("Resolved: " + resolved);

        // After expiry
        Thread.sleep(5100);
        try {
            service.expandUrl(short1, "127.0.0.1");
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // Custom alias
        String short2 = service.shortenUrl("https://chat.openai.com", null, "chatgpt");
        System.out.println("Shortened: " + short2);
        System.out.println("Resolved: " + service.expandUrl("chatgpt", "8.8.8.8"));

        UrlMetaData meta = service.getMeta("chatgpt");
        System.out.println("Hits: " + meta.hitCount + " IPs: " + meta.accessIps);
    }
}
