import java.util.Arrays;

public class matrix {
    public static void main(String[] args){
        Object[] data = new Object[5];
        data[0] = new Integer[] {1,2,3};
        data[1] = new Integer[] {4,5,6};
        data[2] = new Integer[] {7,8,9};
        data[3] = new Integer[] {10,11,12};
        data[4] = new Integer[] {13,14,15};

        Arrays.sort(data);
        for (Object row: data){
            Integer[] row1 = (Integer[]) row;
            System.out.println(row1);
        }
    }
}


