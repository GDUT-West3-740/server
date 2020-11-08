package com.west740.mapper;

import com.west740.bean.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lucifer
 */
@Mapper
public interface ArticleMapper {

    Article getArticleById(Long aid);

    int addNewArticle(Article article);
}
