package org.yxs.wj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yxs.wj.entity.Book;
import org.yxs.wj.entity.Category;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Integer> {

    List<Book> findAllByCategory(Category category);

    List<Book> findAllByTitleLikeOrAuthorLike(String title, String author);
}
