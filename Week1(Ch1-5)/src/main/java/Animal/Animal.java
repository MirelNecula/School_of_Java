package Animal;

import java.util.ArrayList;


public class Animal {

    public void makeSound() {
        System.out.println("Animal sound");
    }
    public static void main(String[] args) {

        ArrayList<Animal> zoo = new ArrayList<Animal>();
        zoo.add(new Animal());
        zoo.add(new Mammal());
        zoo.add(new Bird());
        zoo.add(new Dog());
        zoo.add(new Eagle());
        for (Animal animal : zoo) {
            animal.makeSound();
        }

        System.out.println("Animal sound");
        Animal myAnimal = new Animal();
        Animal myMammal = new Mammal();
        Animal myBird = new Bird();
        Animal myDog = new Dog();
        Animal myEagle = new Eagle();

        myAnimal.makeSound();
        myMammal.makeSound();
        myBird.makeSound();
        myDog.makeSound();
        myEagle.makeSound();
    }

}
class Mammal extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Mammal sound");
    }
}

class Bird extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Bird sound");
    }
}

class Dog extends Mammal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks");
    }
}

class Eagle extends Bird {
    @Override
    public void makeSound() {
        System.out.println("Eagle screeches");
    }
}