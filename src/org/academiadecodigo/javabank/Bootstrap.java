package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.*;
import org.academiadecodigo.javabank.controller.transaction.DepositController;
import org.academiadecodigo.javabank.controller.transaction.WithdrawalController;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.service.AuthService;
import org.academiadecodigo.javabank.service.CustomerService;
import org.academiadecodigo.javabank.view.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for wiring the objects dependencies
 */
public class Bootstrap {

    /**
     * Creates a {@code Bank} and populates it with data
     *
     * @return the bank
     */
    public CustomerService generateTestData() {

        CustomerService customerService = new CustomerService();


        // Bank bank = new Bank();
        //AccountService accountManager = new AccountService();
        //bank.setAccountManager(accountManager);

        Customer c1 = new Customer(1, "Rui");
        Customer c2 = new Customer(2, "Sergio");
        Customer c3 = new Customer(3, "Bruno");

        customerService.add(c1);
        customerService.add(c2);
        customerService.add(c3);
        //bank.addCustomer(c1);
        //bank.addCustomer(c2);
        //bank.addCustomer(c3);

        return customerService;
    }

    /**
     * Wires the necessary object dependencies
     *
     * @param customerService the bank to wire
     * @return the login controller
     */
    public LoginController wireObjects(CustomerService customerService) {

        // attach all input to standard i/o
        Prompt prompt = new Prompt(System.in, System.out);

        AccountService accountService = new AccountService();
        AuthService authService = new AuthService();
        authService.setCustomerService(customerService);


        // wire login controller and view
        LoginController loginController = new LoginController();
        loginController.setAuthService(authService);
        loginController.setCustomerService(customerService);
        LoginView loginView = new LoginView();
        loginController.setView(loginView);
        // loginController.setBank(bank);
        //loginView.setBank(bank);
        loginView.setLoginController(loginController);
        loginView.setPrompt(prompt);


        // wire main controller and view
        MainController mainController = new MainController();
        mainController.setAuthService(authService);
        mainController.setCustomerService(customerService);
        MainView mainView = new MainView();
        //mainView.setBank(bank);
        mainView.setMainController(mainController);
        mainView.setPrompt(prompt);
        mainView.setMainController(mainController);
        mainController.setView(mainView);
        loginController.setNextController(mainController);



        // wire balance controller and view
        BalanceController balanceController = new BalanceController();
        balanceController.setAuthService(authService);
        BalanceView balanceView = new BalanceView();
        balanceView.setBalanceController(balanceController);
        balanceController.setView(balanceView);
        // balanceView.setBank(bank);

        // wire new account controller and view
        NewAccountView newAccountView = new NewAccountView();
        NewAccountController newAccountController = new NewAccountController();
        newAccountController.setAccountService(accountService);
        newAccountController.setAuthService(authService);
        //newAccountController.setBank(bank);
        newAccountController.setView(newAccountView);
        newAccountView.setNewAccountController(newAccountController);

        // wire account transactions controllers and views
        DepositController depositController = new DepositController();
        WithdrawalController withdrawalController = new WithdrawalController();

        AccountTransactionView depositView = new AccountTransactionView();
        AccountTransactionView withdrawView = new AccountTransactionView();

        depositController.setAccountService(accountService);
        depositController.setAuthService(authService);
        depositController.setView(depositView);
        withdrawalController.setAccountService(accountService);
        withdrawalController.setView(withdrawView);
        withdrawalController.setAuthService(authService);
        //depositView.setBank(bank);
        depositView.setPrompt(prompt);
        depositView.setTransactionController(depositController);
        //withdrawView.setBank(bank);
        withdrawView.setPrompt(prompt);
        withdrawView.setTransactionController(withdrawalController);

        // setup the controller map
        Map<Integer, Controller> controllerMap = new HashMap<>();
        controllerMap.put(UserOptions.GET_BALANCE.getOption(), balanceController);
        controllerMap.put(UserOptions.OPEN_ACCOUNT.getOption(), newAccountController);
        controllerMap.put(UserOptions.DEPOSIT.getOption(), depositController);
        controllerMap.put(UserOptions.WITHDRAW.getOption(), withdrawalController);

        mainController.setControllerMap(controllerMap);


        return loginController;
    }
}
