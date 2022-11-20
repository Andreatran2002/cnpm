package com.onlinestorewepr;

import com.onlinestorewepr.entity.Category;
import com.onlinestorewepr.service.AccountService;
import com.onlinestorewepr.service.CategoryService;
import com.onlinestorewepr.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class Main {
  public static void main(String[] args) {

    AccountService accountService = new AccountService();
    accountService.createUser("Andrea tran", "andreatran", "123Phuongvy","0788888888","Female");
  }
}
