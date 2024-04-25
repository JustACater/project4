public class Account {
    private static int numberOfAccounts = 1000;
    private int accountNumber;
    protected double accountBalance;
    public Account(double initialBalance) {
        this.accountBalance = initialBalance;
        this.accountNumber = numberOfAccounts;
        numberOfAccounts += 1;
    }
    public void depositMoney (double depositAmount) {
        accountBalance += depositAmount;
        System.out.printf("You've deposited $%.2f. Your account now has $%.2f.\n", depositAmount, accountBalance);
    }
    public int getAccountNumber() {
        return accountNumber;
    }

    public void withdrawalMoney (double withdrawalAmount) {
        if (withdrawalAmount < accountBalance) {
            accountBalance -= withdrawalAmount;
            System.out.printf("You've withdrawn $%.2f. Your account now has $%.2f.\n", withdrawalAmount, accountBalance);
        }
        else {
            System.out.println("Could not withdrawal. Account has insufficient funds.");
        }
    }
    @Override
    public String toString() {
        return String.format("\nAccount Number: %d \nAccount Balance: $%.2f \n", accountNumber,accountBalance);
    }
}
