package org.yxs.wj.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.yxs.wj.domain.entity.JotterArticle;

/**
* @Description: 文章dao
* @Author: yang-xiansen
* @Date: 2020/08/11 9:21
*/
public interface JotterArticleDAO extends JpaRepository<JotterArticle, Integer> {
    JotterArticle findById(int id);
}
