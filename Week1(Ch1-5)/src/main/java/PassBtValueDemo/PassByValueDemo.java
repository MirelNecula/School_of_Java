package PassBtValueDemo;

public class PassByValueDemo {

    public void modify(String name) {
        System.out.print("Inside modify method before change: " + name);
        name = "Modified Name";
        System.out.println(", Inside modify method after change: " + name);
    }

    public void modify(int[] arr) {
        System.out.print("Inside modify method before change: " + arr[0]);
        arr[0] = 99;
        System.out.println(", Inside modify method after change: " + arr[0]);
    }
}
