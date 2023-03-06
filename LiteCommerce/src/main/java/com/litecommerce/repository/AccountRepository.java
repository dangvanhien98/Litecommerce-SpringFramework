package com.litecommerce.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.litecommerce.model.AccountModel;

public interface AccountRepository extends CrudRepository<AccountModel, Integer> {
	
	@Query(value = "select * from account where user_name = ?1 and pass_word = ?2", nativeQuery = true)
	AccountModel findByUsernameAndPass(String username, String password);
	
	@Query(value = "select * from account where user_name = ?1", nativeQuery = true)
	AccountModel findAccountByUser(String user);
	
	@Modifying
	@Transactional
	@Query(value = "update account set pass_word = ?1 where accountid = ?2", nativeQuery = true)
	void updatePass(String pass, int id);

}
