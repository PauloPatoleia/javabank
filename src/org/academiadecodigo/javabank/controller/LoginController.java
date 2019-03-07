package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.service.AuthService;
import org.academiadecodigo.javabank.service.CustomerService;
import org.academiadecodigo.javabank.view.LoginView;

import java.util.Set;

/**
 * The {@link LoginView} controller
 */
public class LoginController extends AbstractController {

    private Controller nextController;
    private AuthService authService;
    CustomerService customerService;

    /**
     * Sets the next controller
     *
     * @param nextController the next controller to be set
     */
    public void setNextController(Controller nextController) {
        this.nextController = nextController;
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Identifies the logged in customer
     *
     * @param id the customer id
     */
    public void onLogin(int id) {
        // TODO: 07/03/2019 use authenticate instead
        authService.setLoginCustomer(id);
        nextController.init();
    }

    public Set<Integer> getCustomerIds() {
        return customerService.getCustomerIds();
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

}
