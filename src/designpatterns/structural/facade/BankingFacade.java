package designpatterns.structural.facade;

public class BankingFacade {
    private AccountService accountService;
    private TransferService transferService;
    private BillPaymentService billPaymentService;

    public BankingFacade() {
        this.accountService = new AccountService();
        this.transferService = new TransferService();
        this.billPaymentService = new BillPaymentService();
    }

    public void getAccountDetails(String accountId) {
        accountService.getAccountDetails(accountId);
    }

    public void transferFunds(String fromAccount, String toAccount, double amount) {
        transferService.transferFunds(fromAccount, toAccount, amount);
    }

    public void payBill(String accountId, String billId, double amount) {
        billPaymentService.payBill(accountId, billId, amount);
    }
}
