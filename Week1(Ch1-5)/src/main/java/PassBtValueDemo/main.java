package PassBtValueDemo;

public class main {
    public static void main(String[] args) {
        PassByValueDemo PassByValueDemo = new PassByValueDemo();
        String originalName = "Original Name";
        PassByValueDemo.modify(originalName);

        PassByValueDemo arr = new PassByValueDemo();
        int[] originalArray = {1, 2, 3};
        arr.modify(originalArray);

    }
}
