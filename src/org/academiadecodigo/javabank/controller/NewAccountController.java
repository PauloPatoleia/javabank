package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.factories.AccountFactory;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.service.AccountServiceInterface;
import org.academiadecodigo.javabank.view.NewAccountView;

import java.util.Map;

/**
 * The {@link NewAccountView} controller
 */
public class NewAccountController extends AbstractController {

    private Integer newAccountId;
    private AccountFactory accountFactory = new AccountFactory();
    private Map<Integer, Account> accountMap;
    private AccountServiceInterface accountService;

    public void setAccountService(AccountServiceInterface accountService) {
        this.accountService = accountService;
    }

    /**
     * Gets the new account id
     *
     * @return the new account id
     */
    public Integer getNewAccountId() {
        return newAccountId;
    }

    /**
     * Creates a new {@link Account}
     *
     * @see Controller#init()
     */
    @Override
    public void init() {

        newAccountId = createAccount(AccountType.CHECKING);
        super.init();
    }

    private int createAccount(AccountType accountType) {

        Account newAccount = accountFactory.createAccount(accountType);
        authService.getAccessingCustomer().addAccount(newAccount);
        accountService.add(newAccount);
        return newAccount.getId();

    }
}
