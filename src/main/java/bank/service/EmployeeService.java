package bank.service;

import bank.entity.Employee;

public interface EmployeeService {
    /*Отправка работника на удалённую работу*/
    void toDistantWork(Employee employee);
    /*Отправка работника на работу в офисе*/
    void toOfficeWork(Employee employee);

    /*Разрешить/Запретить выдавать кредиты*/
    void permissionForCredit(Employee employee, Boolean flag);
}
