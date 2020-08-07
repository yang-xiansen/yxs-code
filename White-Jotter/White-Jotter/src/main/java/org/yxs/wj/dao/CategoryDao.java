package org.yxs.wj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yxs.wj.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {
}
