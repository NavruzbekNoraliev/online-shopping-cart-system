package com.adminmodule.service.dto;

import com.adminmodule.domain.Account;

public class AccountAdapter {
   public static AccountDTO toDTO(Account account) {
      return new AccountDTO(account.getUsername(), account.getRoles());
   }

    public static Account fromDTO(AccountDTO dto) {
        return new Account(dto.getUsername(), dto.getRole());
    }
}
