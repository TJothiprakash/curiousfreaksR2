package designpatterns.structural.facade;
public class Main {
    public static void main(String[] args) {
        BankingFacade bankingFacade = new BankingFacade();
        bankingFacade.getAccountDetails("123456");
        bankingFacade.transferFunds("123456", "654321", 100.0);
        bankingFacade.payBill("123456", "BILL001", 50.0);
    }
}