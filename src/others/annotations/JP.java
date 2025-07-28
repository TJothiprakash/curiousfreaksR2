package others.annotations;



public class JP {
    @Hi  // ✅ Applying annotation to a field
    private static String message1;

    public JP(String message1) {
        this.message1 = "jp";
    }
}
