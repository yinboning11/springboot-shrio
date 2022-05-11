package com.boning.service;

import com.boning.pojo.Account;

public interface AccountService {
    public Account findByUsername(String username);
}
