package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.model.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerServiceInterface {

    Customer get(Integer id);

    List<Customer> list();

    Set<Integer> listCustomerAccountIds(Integer id);

    double getBalance(int customerId);

    void add(Customer customer);

    public Set<Integer> getCustomerIds();

}
