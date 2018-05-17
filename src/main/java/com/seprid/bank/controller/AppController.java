package com.seprid.bank.controller;

import com.seprid.bank.dto.BalanceOperation;
import com.seprid.bank.dto.TransactionOperation;
import com.seprid.bank.entity.Account;
import com.seprid.bank.entity.Client;
import com.seprid.bank.entity.Transaction;
import com.seprid.bank.repository.AccountRepository;
import com.seprid.bank.repository.ClientRepository;
import com.seprid.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class AppController {
    private AccountRepository accountRepository;
    private ClientRepository clientRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public AppController(AccountRepository accountRepository, ClientRepository clientRepository, TransactionRepository transactionRepository) {
        
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        this.transactionRepository = transactionRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/allClients")
    public String allClientsList(Model model) {

        List<Client> clients = clientRepository.findAll();
        model.addAttribute("clientsList", clients);
        Client client= new Client();
        model.addAttribute("client", client);

        return "allClientsList";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/allClients")
    public String createClient(@ModelAttribute("client") Client client) {

        clientRepository.save(new Client(client.getName(), client.getAddress(), client.getAge()));

        return "redirect:/allClients";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/client/{clientID}")
    public String getClientDetails(Model model, @PathVariable Integer clientID) {

        Client client = clientRepository.getOne(clientID);
        List<Account> clientAccounts = accountRepository.findAllByOwnerId(clientID);
        model.addAttribute("client", client);
        model.addAttribute("clientAccounts", clientAccounts);
        Account account = new Account(clientID, 0);
        model.addAttribute("account", account);

        return "clientPage";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/client/newAccount")
    public String createNewAccount(@ModelAttribute("account") Account account) {
        
        accountRepository.save(new Account(account.getOwnerId(), account.getAccountBalance()));
        
        return "redirect:/client/" + account.getOwnerId();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/transactionsList")
    public String getTransactionsList(Model model) {

        List<Transaction> transactionList = transactionRepository.findAll();
        model.addAttribute("transactions", transactionList);

        return "transactionsList";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/account/{accountID}")
    public String moneyOperations(Model model, @PathVariable Integer accountID) {
        
        Client client = clientRepository.getOne(accountRepository.getOne(accountID).getOwnerId());
        BalanceOperation operation = new BalanceOperation(
                client,
                accountRepository.getOne(accountID),
                0
        );
        model.addAttribute("operation", operation);

        TransactionOperation transactionOperation = new TransactionOperation(accountID, 1, 0);
        model.addAttribute("transfer", transactionOperation);

        return "moneyOperations";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deposit")
    public String depositPOST(@ModelAttribute("operation") BalanceOperation operation) {
        
        Account account = operation.getAccount();
        account.setAccountBalance(account.getAccountBalance() + operation.getAmount());
        accountRepository.save(account);

        Transaction transaction = new Transaction(
                operation.getAmount(),
                1,
                account.getAccountId(),
                new Date()
        );
        transactionRepository.save(transaction);

        return "redirect:/client/"+ operation.getClient().getId();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/withdraw")
    public String withdrawPOST(@ModelAttribute("operation") BalanceOperation operation) {
        
        if (isEnoughFunds(operation.getAccount().getAccountId(), operation.getAmount())) {
            Account account = operation.getAccount();
            account.setAccountBalance(account.getAccountBalance() - operation.getAmount());
            accountRepository.save(account);

            Transaction transaction = new Transaction(
                    operation.getAmount(),
                    account.getAccountId(),
                    1,
                    new Date()
            );
            transactionRepository.save(transaction);
        }
        
        return "redirect:/client/"+ operation.getClient().getId();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/transfer")
    public String transferPOST(@ModelAttribute("transfer")TransactionOperation operation) {
        
        if (isEnoughFunds(operation.getSenderID(), operation.getAmount())) {
            Account sender = accountRepository.getOne(operation.getSenderID());
            Account receiver = accountRepository.getOne(operation.getReceiverID());
            sender.setAccountBalance(sender.getAccountBalance() - operation.getAmount());
            receiver.setAccountBalance(receiver.getAccountBalance() + operation.getAmount());
            accountRepository.saveAll(Arrays.asList(sender, receiver));

            Transaction transaction = new Transaction(
                    operation.getAmount(),
                    operation.getSenderID(),
                    operation.getReceiverID(),
                    new Date()
            );
            transactionRepository.save(transaction);
        }
        
        return "redirect:/client/"+ accountRepository.getOne(operation.getSenderID()).getOwnerId();
    }

    private boolean isEnoughFunds(Integer accountID, Integer availableFunds){
        
        Account account = accountRepository.getOne(accountID);
        if (account.getAccountBalance() - availableFunds >= 0) {
            return true;
        } else return false;
    }
}
