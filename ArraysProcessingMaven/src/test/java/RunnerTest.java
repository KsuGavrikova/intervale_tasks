import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RunnerTest {
    private static int[] test_array_0;
    private static int[] test_array_1;
    private static int[] test_array_2;

    private static int[] result_for_test_array_0;
    private static int[] result_for_test_array_1;
    private static int[] result_for_test_array_2;

    @BeforeAll
    static void setup() {
        test_array_0 = new int[]{};
        test_array_1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -11, -12, -13, -14, -15};
        test_array_2 = new int[]{0, 2, 3, 0, 5, 6, 7, 8, 9, 10, -11, -12, -13, -14};

        result_for_test_array_0 = new int[]{};
        result_for_test_array_1 = new int[]{10, -65};
        result_for_test_array_2 = new int[]{8, -50};
    }

    @Test
    void nullableTest() {
        System.out.println("======TEST NULLABLE=======");
        assertArrayEquals(result_for_test_array_0, Runner.convertArray(null));
        assertArrayEquals(result_for_test_array_0, Runner.convertArrayWithStreamCollect(null));
        assertArrayEquals(result_for_test_array_0, Runner.convertArrayWithStream(null));
        assertArrayEquals(result_for_test_array_0, Runner.convertArray(test_array_0));
        assertArrayEquals(result_for_test_array_0, Runner.convertArrayWithStreamCollect(test_array_0));
        assertArrayEquals(result_for_test_array_0, Runner.convertArrayWithStream(test_array_0));
    }

    @Test
    void firstDataTest() {
        System.out.println("======TEST FIRST=======");
        assertArrayEquals(result_for_test_array_1, Runner.convertArray(test_array_1));
        assertArrayEquals(result_for_test_array_1, Runner.convertArrayWithStreamCollect(test_array_1));
        assertArrayEquals(result_for_test_array_1, Runner.convertArrayWithStream(test_array_1));
    }

    @Test
    void secondDataTest() {
        System.out.println("======TEST SECOND=======");
        assertArrayEquals(result_for_test_array_2, Runner.convertArray(test_array_2));
        assertArrayEquals(result_for_test_array_2, Runner.convertArrayWithStreamCollect(test_array_2));
        assertArrayEquals(result_for_test_array_2, Runner.convertArrayWithStream(test_array_2));
    }
}
