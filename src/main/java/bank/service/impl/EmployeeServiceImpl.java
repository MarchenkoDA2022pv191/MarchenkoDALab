package bank.service.impl;

import bank.entity.Employee;
import bank.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public void toDistantWork(Employee employee) {
        employee.setDistantWork(Boolean.TRUE);
    }

    @Override
    public void toOfficeWork(Employee employee) {
        employee.setDistantWork(Boolean.FALSE);
    }

    @Override
    public void permissionForCredit(Employee employee, Boolean flag){
        employee.setCanLend(flag);

    }
}
