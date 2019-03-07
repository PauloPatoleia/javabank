package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CustomerService implements CustomerServiceInterface {

    private HashMap<Integer, Customer> customers = new HashMap<>();


    @Override
    public Customer get(Integer id) {
        return customers.get(id);
    }

    @Override
    public List<Customer> list() {
        List<Customer> customersList = new ArrayList<Customer>();

        for (int i = 0; i < customers.size(); i++) {
            customersList.add(customers.get(i));
        }

        return customersList;

    }

    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {
       return customers.get(id).getAccountIds();
    }

    @Override
    public double getBalance(int customerId) {

        double balance = 0;

        for (Customer customer : customers.values()) {
            balance += customer.getBalance();
        }

        return balance;

    }

    @Override
    public void add(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public Set<Integer> getCustomerIds() {
        return customers.keySet();
    }
}
