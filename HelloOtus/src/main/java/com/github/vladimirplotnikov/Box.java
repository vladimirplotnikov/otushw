package com.github.vladimirplotnikov;

import java.util.ArrayList;
import java.util.List;

//Создайте класс Box, в который можно складывать фрукты.
public class Box<T extends Fruit> {
    private T obj;

    public T getObj() {
        return obj;
    }

    public Box(T obj) {
        this.obj = obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    // Для хранения фруктов внутри коробки используйте ArrayList;
    List<Fruit> T = new ArrayList<>();
    // Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки,
    // и апельсины (или же если коробка создана с типом Fruit, то допустимо и такое).
    //Не забываем про метод добавления фрукта в коробку.
    //если бы принимали бы Fruit - то смогли бы переиспользовать пустую коробку (но тогда
    //пришлось бы проверять тип фрукта в коробке самому (как и было в первой версии класса)
    void addFruit (T fruit) {
        System.out.println("Я добавляю "+fruit.getClass().getSimpleName()+",который весит "+fruit.getWeight()+" "+
                fruit.getClass().getSimpleName());
        T.add(fruit);
    }
   //Реализуйте в классе Box метод weight(), который высчитывает вес коробки
   // (например, из веса одного фрукта и их количества (вес фрукта задайте сами, единицы измерения не важны),
   // или может через суммирование, тут как считаете нужным);
    double weight() {
        double currentWeight=0;
        for (Fruit i : T) {
            currentWeight=currentWeight+i.getWeight();
        }
        return  currentWeight;
    }
    //Реализуйте в классе Box метод compare(), который позволяет сравнить текущую коробку с той,
    // которую подадут в compare() в качестве параметра.
    // true – если их массы равны, false в противоположном случае. Можно сравнивать коробки с яблоками и апельсинами;
    boolean compare(Box box2){
        return Math.abs(this.weight() - box2.weight()) < 0.001f;
    }
    //Реализуйте метод, который позволяет пересыпать фрукты из текущей коробки в другую.
    // Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
    // Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
    boolean resort(Box box2){
        //пустую коробку нет смысла пересыпать в другую, будем считать, что пересыпали в таком случае
        if (this.T == null) {return true;}
        if (this.T.isEmpty()) {return true;}
        //проверим совместимость коробок
        Fruit box2fruit = null;
        //проверки пройдены, пересыпаем
        for (Fruit i:T) {box2.addFruit(i);}
            this.T.clear();
            return true;
    }
}
