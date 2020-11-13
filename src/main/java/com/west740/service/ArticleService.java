package com.west740.service;

import com.west740.bean.Article;
import com.west740.mapper.ArticleMapper;
import com.west740.mapper.TagsMapper;
import com.west740.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author lucifer
 */
@Service
@Transactional
public class ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    TagsMapper tagsMapper;

    public Article getArticleById(Long aid){
        Article article = articleMapper.getArticleById(aid);
        return article;
    }

    public int updateArticleState(Long[] aids, Integer state) {
        if (state == 2) {
            return articleMapper.deleteArticleById(aids);
        } else {
            return articleMapper.updateArticleState(aids, 2);//放入到回收站中
        }
    }

    public int addNewArticle(Article article) {
        if (article.getSummary() == null||"".equals(article.getSummary())){
            String stripHtml = stripHtml(article.getHtmlContent());
            article.setSummary(stripHtml.substring(0,stripHtml.length()>50?50:stripHtml.length()));
        }
        if (article.getState()==-1){
            article.setUid(Util.getCurrentUser().getId());
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (article.getState() == 1) {
            article.setPublishDate(timestamp);
        }
        article.setEditTime(new Timestamp(System.currentTimeMillis()));
        int i = articleMapper.updateArticle(article);
        String[] dynamicTags = article.getDynamicTags();
        if (dynamicTags != null && dynamicTags.length > 0) {
            int tags = addTagsToArticle(dynamicTags, article.getId());
            if (tags == -1) {
                return tags;
            }
        }
        return i;
    }

    private int addTagsToArticle(String[] dynamicTags, Long aid) {
        tagsMapper.deleteTagsByAid(aid);
        tagsMapper.saveTags(dynamicTags);
        List<Long> tIds = tagsMapper.getTagsIdByTagName(dynamicTags);
        int i = tagsMapper.saveTagsToArticleTags(tIds,aid);
        return i == dynamicTags.length ? i : -1;
    }


    public String stripHtml(String content) {
        content = content.replaceAll("<p .*?>", "");
        content = content.replaceAll("<br\\s*/?>", "");
        content = content.replaceAll("\\<.*?>", "");
        return content;
    }

    public int getArticleCountByState(Integer state,Long uid,String keywords){
        return articleMapper.getArticleCountByState(state,uid,keywords);
    }

    public List<Article> getArticleByState(Integer state,Integer page,Integer size,String keywords){
        int start = (page-1)*size;
        Long uid = Util.getCurrentUser().getId();
        return articleMapper.getArticleByState(state,start,size,uid,keywords);
    }


    public int restoreArticle(Integer articleId) {

        return articleMapper.updateArticleStateById(articleId,1);
    }

    public List<String> getCategories() {
        return articleMapper.getCategories(Util.getCurrentUser().getId());
    }

    public List<Integer> getDataStatistics() {
        return articleMapper.getDataStatistics(Util.getCurrentUser().getId());
    }


    public void pvStatisticsPerDay() {
        articleMapper.pvStatisticsPerDay();
    }

}
