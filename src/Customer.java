import java.util.ArrayList;
import java.util.LinkedList;
public class Customer {
    private String customerFirstName;
    private String customerLastName;
    private int customerPin;
    private LinkedList<BondAccount> investmentLinkedList = new LinkedList<>();
    private ArrayList<Account> accountArrayList = new ArrayList<>();


    public Customer (String customerFirstName, String customerLastName, int customerPin) {
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerPin = customerPin;
    }
    public void addAccount(Account account) {
        accountArrayList.add(account);
    }
    public void addInvestment(BondAccount account) {
        investmentLinkedList.add(account);
    }
    public void removeAccount(Account account) {
        accountArrayList.remove(account);
    }
    public void removeInvestment(BondAccount account) {
        investmentLinkedList.remove(account);
    }
    public int getCustomerPin() {
        return customerPin;
    }
    public Account getAccount(int accountNumber) {
        Account foundAccount = null;
        for (Account account : accountArrayList){
            if (accountNumber == account.getAccountNumber()) {
                foundAccount = account;
            }
        }
        return foundAccount;
    }
    public BondAccount getInvestment(int accountNumber) {
        BondAccount foundAccount = null;
        for (BondAccount account : investmentLinkedList){
            if (accountNumber == account.getAccountNumber()) {
                foundAccount = account;
            }
        }
        return foundAccount;
    }
    public StringBuilder getAllAccounts() {
        StringBuilder accountStringBuilder = new StringBuilder();
        for (Account account : accountArrayList) {
            accountStringBuilder.append(account.toString());
        }
        for (BondAccount bondAccount : investmentLinkedList) {
            accountStringBuilder.append(bondAccount.toString());
        }
        return accountStringBuilder;
    }
    @Override
    public String toString() {
        return String.format("Customer Name: %s %s \nCustomer Accounts - \n%s \nCustomer Investments - ", customerFirstName, customerLastName, getAllAccounts());
    }
}
