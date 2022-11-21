package com.onlinestorewepr;

import com.onlinestorewepr.entity.Category;
import com.onlinestorewepr.service.AccountService;
import com.onlinestorewepr.service.CategoryService;
import com.onlinestorewepr.service.ProductService;
import com.onlinestorewepr.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;

public class Main {
  public static void main(String[] args) {

    AccountService accountService = new AccountService();
    ProductService d = new ProductService();
    Random rand = new Random(); //instance of random class
    String[] sizes = new String[]{"m", "s", "xl", "xxl", "l"};
    String[] brands = new String[]{"Noname", "Anne", "Kimi", "Lada", "Ecle"};
    String[] colors = new String[]{"blue", "orange", "red", "green", "black","brown"};
    int price = 0 ;
    for(int i = 0 ; i< 40; i++){

      d.createProduct(4,"San pham "+i, null,
              "", "Day la san pham thu "+ i+50,
              rand.nextInt(1600),rand.nextInt(100),rand.nextInt(89),
              sizes[ rand.nextInt(sizes.length)],colors[ rand.nextInt(colors.length)],brands[ rand.nextInt(brands.length)]
              );
    }
  }
}
