package com.easybank.accounts.serviceImpl;

import com.easybank.accounts.constants.AccountConstants;
import com.easybank.accounts.dto.AccountsDto;
import com.easybank.accounts.dto.CustomerDto;
import com.easybank.accounts.entity.Accounts;
import com.easybank.accounts.entity.Customer;
import com.easybank.accounts.exception.CustomerAlreadyExistsException;
import com.easybank.accounts.exception.ResourceNotFoundException;
import com.easybank.accounts.mapper.AccountsMapper;
import com.easybank.accounts.mapper.CustomerMapper;
import com.easybank.accounts.repository.AccountsRepository;
import com.easybank.accounts.repository.CustomerRepository;
import com.easybank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccounts(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCostomer(new Customer(), customerDto);
        Optional<Customer> optionalCustomer= customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()){

            throw new CustomerAlreadyExistsException("Customer Already registered with given mobile number "+ customerDto.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
       Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("customer", "mobileNumber",mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account", "customerId",customer.getCustomerId().toString())
        );

        CustomerDto customerDto=CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        customerDto.setAccounts(AccountsMapper.mapToAccountDto(accounts,new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
       boolean isUpdated = false;
        AccountsDto accountsDto=customerDto.getAccounts();
        if(accountsDto !=null){
         Accounts accounts=accountsRepository.findById(accountsDto.getAccountNumber())
                 .orElseThrow(()-> new ResourceNotFoundException("Accounts", "accountNumber",accountsDto.getAccountNumber().toString()));
          AccountsMapper.mapToAccounts(accounts,accountsDto);
          accounts= accountsRepository.save(accounts);
          Long customerId = accounts.getCustomerId();

          Customer customer=customerRepository.findById(customerId)
                  .orElseThrow(()-> new ResourceNotFoundException("Customer","customerId",customerId.toString()));
          CustomerMapper.mapToCostomer(customer,customerDto);
          customerRepository.save(customer);
          isUpdated = true;
        }


        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
       Customer customer=customerRepository.findByMobileNumber(mobileNumber)
               .orElseThrow(()->new ResourceNotFoundException("Customer","MobileNumber",mobileNumber));
       accountsRepository.deleteByCustomerId(customer.getCustomerId());
       customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    private Accounts createNewAccount(Customer customer){
        Accounts newAccount=new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber=1000000000L+new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
      return  newAccount;
    }
}
