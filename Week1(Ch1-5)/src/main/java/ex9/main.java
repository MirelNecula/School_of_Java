package ex9;

import java.util.List;

public class main  {
    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("Vali" , 22),
                new Person("Denis" , 23),
                new Person("Mosu Duta" , 24)
        );
        for (Person p : people) {
            System.out.println(p);
        }

    }
}
