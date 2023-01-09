public class CharacterCount {
    public static void main(String[] args){
        String string = "Essoko Eneteh";
        int count = 0;
        for (int i = 0; i < string.length(); i++){
            if(string.charA(i) !='')
                count ++;
        }
        System.out.println("Total number of characters in a string;" + count);

    }
}
