package com.litecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litecommerce.model.AccountModel;
import com.litecommerce.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	public AccountModel findByUsernameAndPass(String username, String password) {
		return accountRepository.findByUsernameAndPass(username, password);
	}
	
	public void saveAccount(AccountModel acc) {
		accountRepository.save(acc);
	}
	
	public AccountModel findAccountByUser(String user) {
		return accountRepository.findAccountByUser(user);
	}
	
	public void updatePass(String pass, int id) {
		accountRepository.updatePass(pass, id);
	}
}
