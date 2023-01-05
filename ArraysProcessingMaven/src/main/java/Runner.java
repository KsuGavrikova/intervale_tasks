import java.util.Arrays;
import java.util.stream.IntStream;

public class Runner {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -11, -12, -13, -14, -15};

        printArray(convertArrayWithStreamCollect(array));
        printArray(convertArrayWithStream(array));
        printArray(convertArray(array));
    }

    static int[] convertArray(int[] array) {
        if (isNull(array)) return new int[]{};

        int countPlus = 0;
        int sumMinus = 0;

        for (int j : array) {
            if (j > 0) {
                countPlus++;
            } else {
                sumMinus += j;
            }
        }
        return new int[]{countPlus, sumMinus};
    }

    static int[] convertArrayWithStreamCollect(int[] array) {
        if (isNull(array)) return new int[]{};
        return Arrays.stream(array)
                .collect(() -> new int[]{0, 0},
                        (accum, value) -> {
                            if (value > 0) {
                                accum[0]++;
                            } else {
                                accum[1] += value;
                            }
                        },
                        (left, right) -> IntStream.concat(IntStream.of(left), IntStream.of(right))
                );
    }


    static int[] convertArrayWithStream(int[] array) {
        if (isNull(array)) return new int[]{};

        int[] arrays = {0, 0};
        arrays[0] = Arrays.stream(array).boxed().reduce(0,
                (x, y) -> {
                    if (y > 0) {
                        x++;
                    } else {
                        arrays[1] += y;
                    }
                    return x;
                }
        );

        return arrays;
    }

    private static boolean isNull(int[] array) {
        return null == array || array.length == 0;
    }

    static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }
}