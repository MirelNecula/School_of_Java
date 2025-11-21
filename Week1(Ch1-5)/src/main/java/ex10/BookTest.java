package ex10;

public class BookTest  {
    public static void main(String[] args) {
    Book b1 = new Book ("Denis din padurea adormita" , "Vali" , "12345");
    Book b2 = new Book ("Duta din padurea adormita" , "Mirel" , "122345");
        System.out.println(b1.toString());
        System.out.println(b2.toString());
        System.out.println(b1.equals(b2));
    }
}
