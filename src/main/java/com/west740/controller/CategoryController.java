package com.west740.controller;

import com.west740.bean.Category;
import com.west740.bean.ResponseBean;
import com.west740.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 理员专属Controller
 */
@RestController
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public ResponseBean deleteById(@PathVariable String ids) {
        boolean result = categoryService.deleteCategoryByIds(ids);
        if (result) {
            return new ResponseBean("success", "删除成功!");
        }
        return new ResponseBean("error", "删除失败!");
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseBean addNewCate(Category category) {

        if ("".equals(category.getCateName()) || category.getCateName() == null) {
            return new ResponseBean("error", "请输入栏目名称!");
        }

        int result = categoryService.addCategory(category);

        if (result == 1) {
            return new ResponseBean("success", "添加成功!");
        }
        return new ResponseBean("error", "添加失败!");
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseBean updateCate(Category category) {
        int i = categoryService.updateCategoryById(category);
        if (i == 1) {
            return new ResponseBean("success", "修改成功!");
        }
        return new ResponseBean("error", "修改失败!");
    }
}
