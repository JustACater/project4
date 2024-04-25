public class BondAccount extends Account {
    private static int numberOfBondAccounts= 1000;
    private int bondAccountNumber;
    private int investmentYears;
    public BondAccount(double initialBalance, int years) {
        super(initialBalance);
        this.investmentYears = years;
        this.bondAccountNumber = numberOfBondAccounts;
        numberOfBondAccounts += 1;
    }
    public int getAccountNumber() {
        return bondAccountNumber;
    }
    public void withdrawalMoney(double amount) {
        if (investmentYears != 0) {
            if (((accountBalance * 0.05) + amount) <= accountBalance) {
                System.out.printf("Deducting $%.2f for early withdrawal penalty...\n", (accountBalance * 0.05));
                accountBalance = (accountBalance * 0.95) - amount;
                System.out.printf("Your investment account now has $%.2f left in it.\n", accountBalance);
            }
            else {
                System.out.println("Withdrawal amount and penalty rate exceeds account balance, returning you to the menu.");
            }
        }
        else {
            if (amount < accountBalance) {
                accountBalance -= amount;
                System.out.printf("You've withdrawn $%.2f. Your investment now has $%.2f left.\n", amount, accountBalance);
            }
            else {
                System.out.println("Could not withdrawal. Investment has insufficient funds.");
            }
        }
    }
    @Override
    public String toString() {
        return String.format("\nAccount Number: %d \nAccount Balance: $%.2f \nYears until maturity: %d", bondAccountNumber,accountBalance, investmentYears);
    }

}
