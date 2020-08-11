package org.yxs.wj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.yxs.wj.dao.CategoryDao;
import org.yxs.wj.domain.entity.Category;

import java.util.List;

@Service
public class CategotyService {

    @Autowired
    private CategoryDao categoryDao;

    public List<Category> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return categoryDao.findAll(sort);
    }

    public Category get(int id) {
        return categoryDao.findById(id).orElse(null);
    }
}
