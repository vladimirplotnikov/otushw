package com.github.vladimirplotnikov;

import java.util.ArrayList;
import java.util.List;

//Создайте класс Box, в который можно складывать фрукты.
public class Box<T extends Fruit> {

    public Box(T obj) {
    }

    // Для хранения фруктов внутри коробки используйте ArrayList;
    List<Fruit> fruits = new ArrayList<>();
    // Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки,
    // и апельсины (или же если коробка создана с типом Fruit, то допустимо и такое).
    //Не забываем про метод добавления фрукта в коробку.
    //если бы принимали бы Fruit - то смогли бы переиспользовать пустую коробку (но тогда
    //пришлось бы проверять тип фрукта в коробке самому (как и было в первой версии класса)
    void addFruit (T fruit) {
        System.out.println("Я добавляю "+fruit.getClass().getSimpleName()+",который весит "+fruit.getWeight()+" "+
                fruit.getClass().getSimpleName());
        fruits.add(fruit);
    }
   //Реализуйте в классе Box метод weight(), который высчитывает вес коробки
   // (например, из веса одного фрукта и их количества (вес фрукта задайте сами, единицы измерения не важны),
   // или может через суммирование, тут как считаете нужным);
    double weight() {
        double currentWeight=0;
        for (Fruit i : fruits) {
            currentWeight += i.getWeight();
        }
        return  currentWeight;
    }
    //Реализуйте в классе Box метод compare(), который позволяет сравнить текущую коробку с той,
    // которую подадут в compare() в качестве параметра.
    // true – если их массы равны, false в противоположном случае. Можно сравнивать коробки с яблоками и апельсинами;
    boolean compare(Box box){
        return Math.abs(this.weight() - box.weight()) < 0.001f;
    }
    //Реализуйте метод, который позволяет пересыпать фрукты из текущей коробки в другую.
    // Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
    // Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
    boolean resort(Box box){
        //пустую коробку нет смысла пересыпать в другую, будем считать, что пересыпали в таком случае
        if (this.fruits == null) {return true;}
        if (this.fruits.isEmpty()) {return true;}
        //проверим совместимость коробок
        Fruit box2fruit = null;
        //проверки пройдены, пересыпаем
        for (Fruit i: fruits) {box.addFruit(i);}
            this.fruits.clear();
            return true;
    }
}
