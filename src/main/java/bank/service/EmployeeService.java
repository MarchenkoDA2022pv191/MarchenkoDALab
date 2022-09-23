package bank.service;

import bank.entity.Employee;

public interface EmployeeService {
    void toDistantWork(Employee employee);
    void toOfficeWork(Employee employee);

    void permissionForCredit(Employee employee, Boolean flag);
}
