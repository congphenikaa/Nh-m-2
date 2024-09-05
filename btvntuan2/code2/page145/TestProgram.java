import static code2.page145.debug.DebugClass.debug;
import static code2.page145.debugoff.DebugOffClass.debug;

// exercise 3

public class TestProgram {
    public static void main(String[] args) {
        debug("This should be printed (debug version).");
        debug("This won't be printed (debugoff version).");
    }
}
