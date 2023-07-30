package com.github.vladimirplotnikov.decoratororder;
//Создайте класс, который реализует шаблон проектирования "Decorator"
// для обработки заказов в ресторане. Добавьте базовый функционал для класса заказа,
// а затем реализуйте декораторы для добавления дополнительных опций к заказу,
// таких как дополнительные ингредиенты или изменение порций.
public interface Order {
    public void setOrder(String name);
    public String getOrder();
}
