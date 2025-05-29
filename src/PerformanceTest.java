import java.util.Random;

public class PerformanceTest {
    private static final int TEST_SIZE = 10000;
    private static final int TEST_ITERATIONS = 3;

    public static void start() {
        Random random = new Random();

        ChainHashMap<Integer, Integer> chainMap = new ChainHashMap<>(TEST_SIZE);
        LinearHashMap<Integer, Integer> linearMap = new LinearHashMap<>(TEST_SIZE);

        for (int i = 0; i < TEST_SIZE; i++) {
            int key = random.nextInt();
            int value = random.nextInt();
            chainMap.put(key, value);
            linearMap.put(key, value);
        }

        long startTime, endTime;

        startTime = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            int key = random.nextInt();
            chainMap.get(key);
        }
        endTime = System.nanoTime();
        System.out.println("ChainHashMap get time: " + (endTime - startTime) / 1000000.0 + " ms");

        startTime = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            int key = random.nextInt();
            linearMap.get(key);
        }
        endTime = System.nanoTime();
        System.out.println("LinearHashMap get time: " + (endTime - startTime) / 1000000.0 + " ms");
    }
}














