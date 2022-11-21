package com.onlinestorewepr.service;

import com.onlinestorewepr.dao.UserDAO;
import com.onlinestorewepr.entity.User;

import java.util.List;

public class AccountService {
    UserDAO userDAO = new UserDAO();
    private ServiceResult serviceResult;

    public ServiceResult getServiceResult() {
        return serviceResult;
    }

    public void setServiceResult(ServiceResult serviceResult) {
        this.serviceResult = serviceResult;
    }
    public Boolean  createUser(String fullName, String username, String password, String phone , String gender )
    {
        User userCreated = userDAO.findUserCreated(username);

        if(userCreated!=null){
            return false;
        }
        else {
            User userNew = new User();
            if(username != null){
                userNew.setUsername(username);

            }
            userNew.setPassword(password);

            userNew.setName(fullName);
            userNew.setGender(gender);
            userNew.setPhone(phone);
            userDAO.insert(userNew);
            System.out.println(userNew);


        }

        return true;
    }
    public List<User> getAll(){
        return userDAO.getAll();
    }
    public void remove(int id){
        userDAO.delete(id);
    }
    public User get(String username){
        return userDAO.get(username);
    }
}
