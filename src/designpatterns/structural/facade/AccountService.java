package designpatterns.structural.facade;

public class AccountService {
    public void getAccountDetails(String accountId) {
        System.out.println("Fetching account details for account ID: " + accountId);
    }
}