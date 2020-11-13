package com.west740.mapper;

import com.west740.bean.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lucifer
 */
@Mapper
public interface ArticleMapper {

    Article getArticleById(Long aid);

    int addNewArticle(Article article);

    int getArticleCountByState(@Param("state") Integer state, @Param("uid") Long uid, @Param("keywords") String keywords);

    List<Article> getArticleByState(@Param("state") Integer state, @Param("start") Integer start,
                                    @Param("count") Integer count, @Param("uid") Long uid,@Param("keywords") String keywords);

    int updateArticleStateById(@Param("articleId") Integer articleId,@Param("state") Integer state);

    int updateArticleState(@Param("aids") Long aids[], @Param("state") Integer state);

    int deleteArticleById(@Param("aids") Long[] aids);

    int updateArticle(Article article);

    void pvIncrement(Long aid);

    void pvStatisticsPerDay();

    List<String> getCategories(Long uid);

    List<Integer> getDataStatistics(Long uid);
}
