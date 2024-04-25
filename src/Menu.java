import java.util.Scanner;
public class Menu {
    Scanner scanner = new Scanner(System.in);
    Bank bank = new Bank();
    //primary menu
    public void displayFirstMenu() {
        boolean keepGoing = true;
        String userInput;
        while (keepGoing == true) {
            System.out.printf("-------Menu-------\n Please enter a selection: \n 1) Access existing account \n 2) Open a new account \n 3) Remove all accounts \n 4) Exit the program \n");
            System.out.println("Please input an option: ");
            userInput = scanner.nextLine();
            if (userInput.equals("1")) {
                accessExistingAccount();
            }
            else if (userInput.equals("2")) {
                openNewAccount();
            }
            else if (userInput.equals("3")) {
                removeAllAccounts();
            }
            else if (userInput.equals("4")) {
                keepGoing = false;
                scanner.close();
                System.out.println("Thank you for using Cardinal Banks! ");
            }
            else {
                System.out.println("Invalid input: Please enter 1,2,3,4, or 5.");
            }
        }
    }
    //Access account first portion
    private void accessExistingAccount() {
        System.out.println("Please enter your PIN: ");
        int customerPin = Integer.parseInt(scanner.nextLine());
        Customer accessedAccount;
        if (bank.getCustomer(customerPin) == null) {
            System.out.println("Invalid PIN. Returning to menu...");
            return;
        }
        else {
            accessedAccount = bank.getCustomer(customerPin);
        }
        System.out.printf("All accounts: \n %s \n", accessedAccount.getAllAccounts());
        System.out.println("Are you accessing an investment account or standard account?\n1) Investment Account\n2) Standard Account \n");
        String userSelection = scanner.nextLine();
        if (userSelection.equals("1")) {
            System.out.println("Please enter the account number you would like to access: ");
            int accountNum = Integer.parseInt(scanner.nextLine());
            if (!(accessedAccount.getAccount(accountNum) == null)) {
                accountMenu(accessedAccount, null, accessedAccount.getInvestment(accountNum));
            }
            else {
                System.out.println("Invalid account number, returning to menu...");
            }
        }
        else if (userSelection.equals("2")) {
            System.out.println("Please enter the account number you would like to access: ");
            int accountNum = Integer.parseInt(scanner.nextLine());
            if (!(accessedAccount.getAccount(accountNum) == null)) {
                accountMenu(accessedAccount, accessedAccount.getAccount(accountNum), null);
            }
            else {
                System.out.println("Invalid account number, returning to menu...");
            }
        }
        else {
            System.out.println("Invalid selection. Returning to menu...");
        }
    }
    //Option 3, removing a customer from database
    private void removeAllAccounts() {
        System.out.println("Please enter your PIN: ");
        int customerPIN = Integer.parseInt(scanner.nextLine());
        Customer accessedAccount;
        if (bank.getCustomer(customerPIN) == null) {
            System.out.println("Invalid PIN. Returning to menu...");
            return;
        }
        else {
            accessedAccount = bank.getCustomer(customerPIN);
            bank.removeCustomer(accessedAccount);
            System.out.println("You have been successfully removed from the bank registry. ");
        }
    }
    //access account menu
    private void accountMenu(Customer customer, Account account, BondAccount bondAccount) {
        boolean keepGoing = true;
        if (bondAccount == null) {
            while (keepGoing == true) {
                System.out.printf("-------Menu-------\n Please enter a selection: \n 1) Make a deposit \n 2) Make a withdrawal \n 3) Close account \n 4) See account balance \n5) Exit account access \n");
                System.out.println("Please input an option: ");
                String userInput = scanner.nextLine();
                if (userInput.equals("1")) {
                    System.out.println("Please enter the amount you want to deposit (use 0.00 format, no $ sign)");
                    account.depositMoney(Double.parseDouble(scanner.nextLine()));
                } else if (userInput.equals("2")) {
                    System.out.println("Please enter the amount you want to withdrawal (use 0.00 format, no $ sign)");
                    account.withdrawalMoney(Double.parseDouble(scanner.nextLine()));
                } else if (userInput.equals("3")) {
                    System.out.println("Please enter your PIN to confirm: ");
                    int customerPin = Integer.parseInt(scanner.nextLine());
                    if (customer.getCustomerPin() == customerPin) {
                        System.out.println("Your account has been removed from the registry.");
                        customer.removeAccount(account);
                        return;
                    } else {
                        System.out.println("Invalid PIN. Returning to account menu...");
                    }
                } else if (userInput.equals("4")) {
                    System.out.printf("----Account Details---\n %s", account.toString());
                } else if (userInput.equals("5")) {
                    keepGoing = false;
                    System.out.println("Returning to menu...");
                } else {
                    System.out.println("Invalid input: Please enter 1,2,3,4, or 5.");
                }
            }
        }
        else if (account == null){
            while (keepGoing == true) {
                System.out.printf("-------Menu-------\n Please enter a selection: \n 1) Make a deposit \n 2) Make a withdrawal \n 3) Close account \n 4) See account balance \n5) Exit account access \n");
                System.out.println("Please input an option: ");
                String userInput = scanner.nextLine();
                if (userInput.equals("1")) {
                    System.out.println("Please enter the amount you want to deposit (use 0.00 format, no $ sign)");
                    bondAccount.depositMoney(Double.parseDouble(scanner.nextLine()));
                } else if (userInput.equals("2")) {
                    System.out.println("Please enter the amount you want to withdrawal (use 0.00 format, no $ sign)");
                    bondAccount.withdrawalMoney(Double.parseDouble(scanner.nextLine()));
                } else if (userInput.equals("3")) {
                    System.out.println("Please enter your PIN to confirm: ");
                    int customerPin = Integer.parseInt(scanner.nextLine());
                    if (customer.getCustomerPin() == customerPin) {
                        System.out.println("Your account has been removed from the registry.");
                        customer.removeInvestment(bondAccount);
                        return;
                    } else {
                        System.out.println("Invalid PIN. Returning to account menu...");
                    }
                } else if (userInput.equals("4")) {
                    System.out.printf("----Account Details---\n %s", bondAccount.toString());
                } else if (userInput.equals("5")) {
                    keepGoing = false;
                    System.out.println("Returning to menu...");
                } else {
                    System.out.println("Invalid input: Please enter 1,2,3,4, or 5.");
                }
            }
        }
    }

    //Open new account, used with option 1
    private void openNewAccount() {
        System.out.println("Are you a new or existing customer?\n1) New customer\n2) Existing customer \n");
        String userSelection = scanner.nextLine();
        Customer accessedAccount;
        if (userSelection.equals("1")) {
            accessedAccount = createCustomer();
        }
        else if (userSelection.equals("2")) {
            System.out.println("Please enter your PIN: ");
            int customerPIN = Integer.parseInt(scanner.nextLine());
            if (bank.getCustomer(customerPIN) == null) {
                System.out.println("Invalid PIN. Returning to menu...");
                return;
            }
            else {
                accessedAccount = bank.getCustomer(customerPIN);
            }
        }
        else {
            System.out.println("Invalid selection. Returning to menu...");
            return;
        }
        System.out.println("Are you adding an investment account or standard account?\n1) Investment Account\n2) Standard Account \n");
        userSelection = scanner.nextLine();
        if (userSelection.equals("1")) {
            System.out.println("Please enter your initial deposit (use 0.00 format, no $ sign): ");
            double initialDeposit = Double.parseDouble(scanner.nextLine());
            System.out.println("How many years would you like to invest your money for? ");
            int years = Integer.parseInt(scanner.nextLine());
            BondAccount newInvestment = new BondAccount(initialDeposit, years);
            accessedAccount.addAccount(newInvestment);
            System.out.printf("New investment opened: %d\n", newInvestment.getAccountNumber());
        }
        else if (userSelection.equals("2")) {
            System.out.println("Please enter your initial deposit (use 0.00 format, no $ sign): ");
            double initialDeposit = Double.parseDouble(scanner.nextLine());
            Account newAccount = new Account(initialDeposit);
            accessedAccount.addAccount(newAccount);
            System.out.printf("New account opened: %d\n", newAccount.getAccountNumber());
        }
        else {
            System.out.println("Invalid selection. Returning to menu...");
        }
    }
    //second portion of new account creation (for new customers)
    private Customer createCustomer () {
        System.out.println("Please enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Please enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Please create a PIN number (integers only): ");
        int userPIN = Integer.parseInt(scanner.nextLine());
        Customer newCustomer = new Customer (firstName,lastName,userPIN);
        bank.addCustomer(newCustomer);
        return newCustomer;
    }
}
