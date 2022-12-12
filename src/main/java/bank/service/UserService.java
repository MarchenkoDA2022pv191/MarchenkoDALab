package bank.service;

import bank.entity.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface UserService {
    /*Смена пользователем работы, а соответственно, и заработной платы, и пересчёт его кредитного рейтинга*/
    void changeWork(User user, String newWork, Double newMonthSalary);

    String getInfo(User user);

    void saveToFile(String fileName, Bank bank, User user) throws IOException;
    void updateFromFile(String fileName, User user) throws IOException;

}
