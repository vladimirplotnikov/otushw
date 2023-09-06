package org.example.service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import otus.study.cashmachine.bank.service.impl.AccountServiceImpl;
import otus.study.cashmachine.machine.service.CashMachineService;
import otus.study.cashmachine.bank.dao.AccountDao;
import otus.study.cashmachine.bank.dao.CardsDao;
import otus.study.cashmachine.bank.service.AccountService;
import otus.study.cashmachine.bank.service.CardService;
import otus.study.cashmachine.bank.service.impl.CardServiceImpl;
import otus.study.cashmachine.machine.data.CashMachine;
import otus.study.cashmachine.machine.data.MoneyBox;
import otus.study.cashmachine.machine.service.MoneyBoxService;
import otus.study.cashmachine.machine.service.impl.CashMachineServiceImpl;
import otus.study.cashmachine.machine.service.impl.MoneyBoxServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SomeDataController {
    static AccountDao accountDao = new AccountDao();

    static AccountService accountService;
    static CardsDao cardsDao;
    static CardService cardService;
    static MoneyBoxService moneyBoxService;
    static CashMachineService cashMachineService;
    static MoneyBox moneyBox;
    static CashMachine cashMachine;


    static {
        accountService = new AccountServiceImpl(accountDao);
        cardsDao = new CardsDao();
        cardService = new CardServiceImpl(accountService, cardsDao);
        moneyBoxService = new MoneyBoxServiceImpl();
        cashMachineService = new CashMachineServiceImpl(cardService, accountService, moneyBoxService);
        MoneyBox moneyBox = new MoneyBox();
        CashMachine cashMachine = new CashMachine(moneyBox);
    }

    @GetMapping(value = "people")
    public String people(Model model) {
        model.addAttribute("name", "Vasya");
        return "people";
    }

    @PostMapping(value = "people")
    public String add(Model model, String name) {
        model.addAttribute("name", name);
        return "people";
    }

    @GetMapping(value = "atm")
    public String atm(Model model) {
//        MoneyBox moneyBox = new MoneyBox();
//        CashMachine cashMachine = new CashMachine(moneyBox);
        try
        {
            BigDecimal initialSum = cashMachineService.checkBalance(cashMachine, "1111", "0000");
            System.out.println("Initial sum " + initialSum);
            model.addAttribute("balance", initialSum.toString());
            return "atm";
        }
        catch (Exception e)
        {return e.toString();}

    }

    @PostMapping(value = "atm")
    public String atm(Model model, String card, String pin, String newpin, String button, String amount) {
        model.addAttribute("card", card);
        model.addAttribute("pin", pin);
        model.addAttribute("newpin", newpin);
        model.addAttribute("button", button);
        model.addAttribute("amount", amount);
        BigDecimal bigDecimalAmount = null;


        try {
            if (button.equals("Взять деньги")||button.equals("Положить деньги"))
            bigDecimalAmount = new BigDecimal(amount);

        if (button.equals("Взять деньги")) {
            //cashMachineService.getMoney(cashMachine,card,pin,bigDecimalAmount);
            cardService.getMoney(card, pin, bigDecimalAmount);
            model.addAttribute("message", "Взяли с карты "+amount);
        }
        else if (button.equals("Положить деньги")) {
            cardService.putMoney(card, pin, bigDecimalAmount);
            model.addAttribute("message", "Положили на карту "+amount);
        }
        else if (button.equals("Посмотреть баланс")) {
            model.addAttribute("message", "Баланс смотри выше");}
        else if (button.equals("Сменить пинкод")) {
            cardService.cnangePin(card, pin, newpin);
            pin=newpin;
            model.addAttribute("message", "Сменили пинкод карты "+card);
        }
        } catch (Exception e)
        {model.addAttribute("message", e.toString());}

        //вывод баланса
        try {
        model.addAttribute("balance", cardService.getBalance(card,pin));}
        catch (Exception e)
        {model.addAttribute("message", e.toString());}
        return "atm";
    }


}
