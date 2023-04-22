package com.adminmodule.service.dto;

import com.adminmodule.domain.Account;

public class AccountAdapter {
   public static AccountDTO toDTO(Account account) {
      return new AccountDTO(account.getEmail(), account.getRoles());
   }

    public static Account fromDTO(AccountDTO dto) {
        return new Account(dto.getEmail(), dto.getRole());
    }
}
