package com.github.vladimirplotnikov;

public class MainApplication {
    public static void main(String[] args) {
        Fruit myFruit = new Fruit(10);
        Apple myApple = new Apple(20);
        Orange myOrange = new Orange(30);
        Box<Apple> myBox = new Box<>(null);
        Box<Orange> orangeBox = new Box(null);
        Box emptyBox = new Box(null);
        myFruit.eat();
        myBox.addFruit(myApple);
        myBox.addFruit(myApple);
        //после переписывания на Generic теперь не удастся в коробку в которой
        //что-то было, положить что-то отличное от того, что здесь когда-то было
        //например в бывшую коробку с яблоками теперь можно добавлять только яблоки
        //myBox.addFruit(myOrange);
        System.out.println("Общий вес в коробке="+myBox.weight());
        orangeBox.addFruit(myOrange);
        System.out.println("Общий вес в коробке="+orangeBox.weight());
        if (myBox.compare(orangeBox)) {
            System.out.println("Вес одинаков");
        } else {System.out.println("Вес разный");}
        //пересыпаем яблоки в пустую коробку
        myBox.resort(emptyBox);
        //пересыпаем пустую коробку в коробку с Апельсинами
        myBox.resort(orangeBox);
        //теперь добавим апельсин экскоробку с яблоками, а после этот апельсин пересыпем в коробку с апельсинами
        //myBox.addFruit(myOrange);
        //myBox.resort(orangeBox);
        orangeBox.resort(myBox);
    }

}