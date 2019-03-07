package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.model.Customer;


public class AuthService implements AuthServiceInterface {

    private int loginCustomer;
    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean authenticate(Integer id) {
        // TODO: 07/03/2019 check if customer exists in customerService list and setAccessingCustomer
       return id == loginCustomer;
    }

    @Override
    public Customer getAccessingCustomer() {
        return customerService.get(loginCustomer);
    }

    public void setLoginCustomer(int loginCustomer) {
        this.loginCustomer = loginCustomer;
    }

    public int getLoginCustomer() {
        return loginCustomer;
    }
}
