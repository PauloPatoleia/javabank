package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.service.AuthService;
import org.academiadecodigo.javabank.service.CustomerService;
import org.academiadecodigo.javabank.view.BalanceView;

/**
 * The {@link BalanceView} controller
 */
public class BalanceController extends AbstractController {


    public Customer getLoginCustomer() {

        return authService.getAccessingCustomer();

    }



}
