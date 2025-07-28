package others.exceptions;

public class StoreExceptionandRethrwoit {

    public void exampleMethod() throws Exception {
        Exception tryException = null;  // Store exception from try block

        try {
            // Code that throws an exception
            throw new RuntimeException("Exception from try block");
        } catch (Exception e) {
            tryException = e;  // Store the original exception
        } finally {
            try {
                // Code that throws another exception
                throw new IllegalStateException("Exception from finally block");
            } catch (Exception finallyException) {
                if (tryException != null) {
                    finallyException.addSuppressed(tryException); // Attach try exception as suppressed
                }
                finallyException.printStackTrace();
                throw finallyException;  // Rethrow the finally exception
            }
        }
    }

    public static void main(String[] args) {
        StoreExceptionandRethrwoit storeExceptionAndRethrwoit = new StoreExceptionandRethrwoit();
        try {
            storeExceptionAndRethrwoit.exampleMethod();
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
            for (Throwable suppressed : e.getSuppressed()) {
                System.out.println("Suppressed: " + suppressed.getMessage());

            }
        }
    }
}
