package com.github.vladimirplotnikov;

public class MainApplication {
    public static void main(String[] args) {
        Fruit myFruit = new Fruit(10,1);
        Apple myApple = new Apple(20,2);
        Orange myOrange = new Orange(30,2);
        Box myBox = new Box();
        Box orangeBox = new Box();
        Box emptyBox = new Box();
        myFruit.eat();
        myBox.addFruit(myApple);
        myBox.addFruit(myApple);
        myBox.addFruit(myOrange);
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
        myBox.addFruit(myOrange);
        myBox.resort(orangeBox);
    }

}