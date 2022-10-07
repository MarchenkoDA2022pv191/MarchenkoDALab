package bank.entity.common;

import bank.entity.Bank;
import bank.entity.User;
import bank.utils.FullName;

import java.util.Date;

abstract public class Person {
    protected Integer id;
    protected FullName name;
    protected Date birthday;

    public Person(Integer id, FullName name, Date birthday){
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FullName getName() {
        return name;
    }

    public void setName(FullName name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
