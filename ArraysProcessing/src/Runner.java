import java.util.Arrays;
import java.util.function.IntConsumer;

public class Runner {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -11, -12, -13, -14, -15};
        int[] array_1 = new int[]{0, 2, 3, 0, 5, 6, 7, 8, 9, 10, -11, -12, -13, -14};

        printArray(convertArrayWithStream(array));
        printArray(convertArray(array_1));
    }

    private static int[] convertArray(int[] array) {
        if (null == array || array.length == 0) return new int[]{};

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

    private static int[] convertArrayWithStream(int[] array) {
        if (null == array || array.length == 0) return new int[]{};

        CustomIntSummaryStatistics stats = new CustomIntSummaryStatistics();
        Arrays.stream(array).forEach(stats);

        return stats.getStatsAsArray();
    }

    static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static class CustomIntSummaryStatistics implements IntConsumer {
        private int count;
        private int sum;

        @Override
        public void accept(int value) {
            if (value > 0) {
                count++;
            } else {
                sum += value;
            }
        }

        public final int[] getStatsAsArray() {
            return new int[]{count, sum};
        }
    }
}