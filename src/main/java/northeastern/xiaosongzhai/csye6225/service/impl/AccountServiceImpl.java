package northeastern.xiaosongzhai.csye6225.service.impl;

import northeastern.xiaosongzhai.csye6225.entity.Account;
import northeastern.xiaosongzhai.csye6225.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/10/4 11:29
 * @Description: to verify account
 */
@Service
public class AccountServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(account.getEmail(), account.getPassword(), new ArrayList<>());
    }
}
