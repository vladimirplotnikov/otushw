# otushw
Задача:

1.Создайте классы Fruit, Apple extends Fruit, Orange extends Fruit;

2.Создайте класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины (или же если коробка создана с типом Fruit, то допустимо и такое). Для хранения фруктов внутри коробки используйте ArrayList;

3.Реализуйте в классе Box метод weight(), который высчитывает вес коробки (например, из веса одного фрукта и их количества (вес фрукта задайте сами, единицы измерения не важны), или может через суммирование, тут как считаете нужным);

4.Реализуйте в классе Box метод compare(), который позволяет сравнить текущую коробку с той, которую подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае. Можно сравнивать коробки с яблоками и апельсинами;

5.Реализуйте метод, который позволяет пересыпать фрукты из текущей коробки в другую. Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами. Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;

6.Не забываем про метод добавления фрукта в коробку.