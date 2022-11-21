package com.onlinestorewepr.service;

import com.onlinestorewepr.dao.CategoryDAO;
import com.onlinestorewepr.entity.Category;

import java.util.List;

public class CategoryService {
  private CategoryDAO categoryDAO;
  private ServiceResult serviceResult;

  public ServiceResult getServiceResult() {
    return serviceResult;
  }

  public void setServiceResult(ServiceResult serviceResult) {
    this.serviceResult = serviceResult;
  }

  public CategoryService() {
    categoryDAO = new CategoryDAO();
    serviceResult= new ServiceResult();
  }

  public void createCategory(String name, String description) {
    String message, messageType;

    // Check if a category with the same name already exists in DB
    if (categoryDAO.findByName(name) == null) {
      try {
        Category category = new Category(name, description);
        categoryDAO.insert(category);

        message = "A new category was created successfully!";
        messageType = "success";
      } catch (Exception e) {
        e.printStackTrace();
        message = "An error occurred when creating a new category! Please try again.";
        messageType = "danger";
      }
    } else {
      message = String.format("A category with name %s already exists! Please choose another name", name);
      messageType = "danger";
    }

    serviceResult.setMessage(message);
    serviceResult.setMessageType(messageType);
  }

  public void deleteCategory(int id) {
    Category category = categoryDAO.get(id);
    String message, messageType;
    if (category != null) {
      if (category.getProducts() == null || category.getProducts().isEmpty()) {
        try {
          categoryDAO.delete(id);
          message = "Category was deleted successfully!";
          messageType = "primary";
        } catch (Exception e) {
          e.printStackTrace();
          message = "An error occurred when creating a new category! Please try again.";
          messageType = "danger";
        }
      } else {
        message = String.format("Could not delete the category %s because it currently contains some products.", category.getName());
        messageType = "danger";
      }
    } else {
      message = String.format("Category with id %s does not exist", id);
      messageType = "danger";
    }

    serviceResult.setMessage(message);
    serviceResult.setMessageType(messageType);
  }

  public void updateCategory(int id, String name, String description) {
    Category category = categoryDAO.get(id);
    String message = "", messageType = "";
    if (category != null) {
      try {
        category.setName(name);
        category.setDescription(description);
        categoryDAO.update(category);

        message = "Category's info was changed successfully!";
        messageType = "success";
      } catch (Exception e) {
        e.printStackTrace();
        message = "An error occurred when creating a new category! Please try again.";
        messageType = "danger";
      }

    } else {
      message = String.format("Category with name %s does not exist", name);
      messageType = "danger";
    }

    serviceResult.setMessage(message);
    serviceResult.setMessageType(messageType);
  }

  public Category getCategory(int id) {
    return categoryDAO.get(id);
  }

  public List<Category> getAllCategories() {
    List<Category> categories = null;

    categories = categoryDAO.getAll();
    if (categories != null) {
      categories.sort((o1, o2) -> {
        int compareValue = o1.getName().compareTo(o2.getName());
        return (Integer.compare(compareValue, 0));
      });
    }

    return categories;
  }
}
