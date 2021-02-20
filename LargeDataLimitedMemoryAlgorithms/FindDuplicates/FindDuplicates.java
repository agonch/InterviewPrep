public class FindDuplicates {

    private static final Runtime RUNTIME = Runtime.getRuntime();
    private static final long MAX_MEMORY = RUNTIME.maxMemory(); // in bytes
    private static final int BYTES_IN_MB = 1024*1024;  // bytes in a megabyte

    public static void main(String[] args) {
        /* Only allowed 20 MB of memory */
        System.out.println("MAX_MEMORY = " + (MAX_MEMORY * 1.0 / BYTES_IN_MB) + "MB");
        if (MAX_MEMORY > MBToBytes(20)) {
            throw new IllegalStateException("You are only allowed up to 20MB of memory." +
                    " Provide java flag '-Xmx20m'");
        }


    }

    private static long MBToBytes(int mb) {
        return (long) mb * BYTES_IN_MB;
    }

    private static void printUsedMemory() {
        System.out.println("Used memory: " + (RUNTIME.totalMemory() - RUNTIME.freeMemory()));
    }
}
