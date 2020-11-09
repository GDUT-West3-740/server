package com.west740.service;

import com.west740.bean.Article;
import com.west740.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lucifer
 */
@Service
@Transactional
public class ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    public Article getArticleById(Long aid){
        Article article = articleMapper.getArticleById(aid);
        return article;
    }

    public int updateArticleState(Long[] aids, Integer state) {

        return -1;
    }

    public int addNewArticle(Article article) {
        /* TODO
        *  对文章的处理
        */
        int result = articleMapper.addNewArticle(article);
        return 0;
    }

    public int getArticleCountByState(Integer state,Long uid,String keywords){
        return articleMapper.getArticleCountByState(state,uid,keywords);
    }

    public List<Article> getArticleByState(Integer state,Integer page,Integer size,String keywords){
        int start = (page-1)*size;
        Long uid = Long.valueOf(1);
        return articleMapper.getArticleByState(state,start,size,uid,keywords);
    }


    public int restoreArticle(Integer articleId) {

        return articleMapper.updateArticleStateById(articleId,1);
    }
}
