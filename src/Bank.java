import java.util.ArrayList;

public class Bank {
    private ArrayList<Customer> customerArrayList = new ArrayList<>();

    public void addCustomer(Customer newCustomer) {
        customerArrayList.add(newCustomer);
    }
    public void removeCustomer (Customer oldCustomer) {
        customerArrayList.remove(oldCustomer);
    }
    public Customer getCustomer(int customerPin) {
        Customer foundCustomer = null;
        for (Customer customer : customerArrayList){
            if (customerPin == customer.getCustomerPin()) {
                foundCustomer = customer;
            }
        }
        return foundCustomer;
    }
    public StringBuilder getAllCustomers() {
        StringBuilder customerStringBuilder = new StringBuilder();
        for (Customer customer : customerArrayList) {
            customerStringBuilder.append(customer.toString());
        }
        return customerStringBuilder;
    }
}
