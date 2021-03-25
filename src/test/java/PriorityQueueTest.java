import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;
import java.util.PriorityQueue;
import java.util.stream.Stream;

class PriorityQueueTest {
    private static Stream<Arguments> testArgs() {
        return Stream.of(
            Arguments.of(
                new int[]{50, 20, 40, 30, 10},
                new int[]{10, 20, 30, 40, 50}
            ),
            Arguments.of(
                new int[]{6, -31, 42, -56, 87},
                new int[]{-56, -31, 6, 42, 87}
            ),
            Arguments.of(
                new int[]{1, 0, 0, -1, 0},
                new int[]{-1, 0, 0, 0, 1}
            ),
            Arguments.of(
                new int[]{99999, 3, 6699, 10, 0},
                new int[]{0, 3, 10, 6699, 99999}
            ),
            Arguments.of(
                new int[]{-1, -4, -999, -2, -55},
                new int[]{-999, -55, -4, -2, -1}
            )
        );
    }

    @ParameterizedTest(name = "Test {index}: Random = {0}, Correct = {1}")
    @MethodSource("testArgs")
    public void testWithArgs(int [] random, int [] correct) {
        var queue = new PriorityQueue<Integer>();
        for (int j : random) queue.add(j);

        assertEquals(correct.length, queue.size());
        for (int j : correct) assertEquals(j, queue.poll());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void exceptionTest1() {
        assertThrows(IllegalArgumentException.class, () -> new PriorityQueue<Integer>(0));
    }

    @Test
    public void exceptionTest2() {
        assertThrows(NullPointerException.class, () -> {
            var queue = new PriorityQueue<Integer>();
            queue.add(null);
        });
    }

    @Test
    public void exceptionTest3() {
        assertThrows(ClassCastException.class, () -> {
            var queue = new PriorityQueue<>();
            queue.add(1);
            queue.add("bomb");
        });
    }
}
