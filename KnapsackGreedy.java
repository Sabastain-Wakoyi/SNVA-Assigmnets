import java.util.*;

public class KnapsackGreedy {

    static class Item {
        int weight, value;
        double ratio;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.ratio = (double) value / weight;
        }
    }

    public static void main(String[] args) {

        Item[] items = {new Item(1, 1), new Item(3, 4), new Item(4, 5), new Item(5, 7)}; // weight and value of each item
        int maxCapacity = 7; // maximum capacity of the knapsack

        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                return Double.compare(i2.ratio, i1.ratio);
            }
        });

        double maxValue = 0;
        for (Item item : items) {
            if (maxCapacity >= item.weight) {
                maxValue += item.value;
                maxCapacity -= item.weight;
            } else {
                maxValue += item.ratio * maxCapacity;
                break;
            }
        }

        // print the maximum value that can be obtained
        System.out.println("Maximum value that can be obtained: " + maxValue);
    }
}
