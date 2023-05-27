package com.github.vladimirplotnikov;

import java.util.ArrayList;
//Создайте класс Box, в который можно складывать фрукты.
public class Box {
    // Для хранения фруктов внутри коробки используйте ArrayList;
    ArrayList<Fruit> fruits = new ArrayList<>();
    // Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки,
    // и апельсины (или же если коробка создана с типом Fruit, то допустимо и такое).
    boolean hasSameType (Fruit fruit) {
        for (Fruit num:fruits
        ) {if (!num.equals(fruit) && fruit!=null) {
            System.out.println("Ждали "+num.getClass().getSimpleName()+", а получили "+fruit.getClass().getSimpleName());
            return false;}
        }
        return true;
    }
    //Не забываем про метод добавления фрукта в коробку.
    void addFruit (Fruit fruit) {
        System.out.println("Я добавляю "+fruit.getQuantity()+",которые весят "+fruit.getWeight()+" "+
                fruit.getClass().getSimpleName());
        if (hasSameType(fruit)) {
            fruits.add(fruit);
        } else {
            System.out.println("Не добавили");
        }
    }
   //Реализуйте в классе Box метод weight(), который высчитывает вес коробки
   // (например, из веса одного фрукта и их количества (вес фрукта задайте сами, единицы измерения не важны),
   // или может через суммирование, тут как считаете нужным);
    double weight() {
        double curweight=0;
        for (Fruit num : fruits) {
            curweight=curweight+num.getWeight();
        }
        return  curweight;
    }
    //Реализуйте в классе Box метод compare(), который позволяет сравнить текущую коробку с той,
    // которую подадут в compare() в качестве параметра.
    // true – если их массы равны, false в противоположном случае. Можно сравнивать коробки с яблоками и апельсинами;
    boolean compare(Box box2){
        if (this.weight()== box2.weight()) {return true;}
        else {
        return false;}
    }
    //Реализуйте метод, который позволяет пересыпать фрукты из текущей коробки в другую.
    // Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
    // Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
    boolean resort(Box box2){
        //пустую коробку нет смысла пересыпать в другую, будем считать, что пересыпали в таком случае
        if (this.fruits == null) {return true;}
        if (this.fruits.isEmpty()) {return true;}
        //проверим совместимость коробок
        //Fruit box1fruit = null;
        Fruit box2fruit = null;
        //if  (!this.fruits.isEmpty()) {box1fruit = this.fruits.get(0);}
        if  (!box2.fruits.isEmpty()) {box2fruit = box2.fruits.get(0);}
        if ((box2fruit ==null) || hasSameType(box2fruit)
        )
        {
        //проверки пройдены, пересыпаем
        for (Fruit num:fruits) {box2.addFruit(num);}
            this.fruits.clear();
            return true;}
        return false;
    }
}
