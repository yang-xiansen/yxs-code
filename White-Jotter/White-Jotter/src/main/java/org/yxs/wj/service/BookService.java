package org.yxs.wj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.yxs.wj.dao.BookDao;
import org.yxs.wj.entity.Book;
import org.yxs.wj.entity.Category;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private CategotyService categoryService;

    public List<Book> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return bookDao.findAll(sort);
    }

    public void addOrUpdate(Book book) {
        bookDao.save(book);
    }

    public void deleteById(int id) {
        bookDao.deleteById(id);
    }

    public List<Book> listByCategory(int cid) {
        Category category = categoryService.get(cid);
        return bookDao.findAllByCategory(category);
    }
}
