package otus.study.cashmachine.machine.service;

import org.springframework.stereotype.Service;
import otus.study.cashmachine.machine.data.CashMachine;

import java.math.BigDecimal;
import java.util.List;
@Service
public interface CashMachineService {
    List<Integer> getMoney(CashMachine machine, String cardNum, String pin, BigDecimal amount);

    BigDecimal putMoney(CashMachine machine, String cardNum, String pin, List<Integer> notes);

    BigDecimal checkBalance(CashMachine machine, String cardNum, String pin);

    boolean changePin(String cardNum, String oldPin, String newPin);
}
