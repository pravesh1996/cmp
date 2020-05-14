package com.pravesh.cms.service;

import com.pravesh.cms.dao.CustomerDAO;
import com.pravesh.cms.exception.CustomerNotFoundException;
import com.pravesh.cms.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerService {
    @Autowired
    private CustomerDAO customerDAO;

  public Customer addCustomer(Customer customer)
    {
      /*  customer.setCustomerId(customerIdCount);
        customerList.add(customer);
        customerIdCount++;*/
        return customerDAO.save(customer);
        /*return customer;*/
    }
    public List<Customer> getCustomer()
    {
       return customerDAO.findAll();
       /*return customerList;*/

    }
    public Customer getCustomer(int customerId)
    {
       /*return customerList
                .stream()
                .filter(c->c.getCustomerId()==customerId)
                .findFirst()
                .get();*/

       Optional<Customer> optionalCustomer= customerDAO.findById(customerId);
       if(!optionalCustomer.isPresent())
           throw new CustomerNotFoundException("Customer Record is not available....");
       return optionalCustomer.get();
    }
   public Customer upadteCustomer(int customerId,Customer customer)
    {
        customer.setCustomerId(customerId);
        /*customerList
                .stream()
                .forEach(c->{
                    if (c.getCustomerId()==customerId){
                        c.setCustomerFirstName(customer.getCustomerFirstName());
                        c.setCustomerLastName(customer.getCustomerLastName());
                        c.setCustomerId(customer.getCustomerId());

                    }
                });
        return customerList
                .stream()
                .filter(c->c.getCustomerId()==customerId)
                .findFirst()
                .get();*/
        return customerDAO.save(customer);
    }
   public void deleteCustomer(int customerId)
   {
      /* customerList
               .stream()
               .forEach(c->{
                   if (c.getCustomerId()==customerId)
                   {
                       customerList.remove(c);
                   }
               });*/
       customerDAO.deleteById(customerId);
   }
}
