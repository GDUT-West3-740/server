package com.west740.controller;

import com.west740.bean.Article;
import com.west740.bean.ResponseBean;
import com.west740.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

/**
 * @author lucifer
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    @PostMapping("/")
    public ResponseBean addNewArticle(Article article){
        int result = articleService.addNewArticle(article);
        return null;
    }

    @GetMapping("/{aid}")
    public Article getArticleById(@PathVariable Long aid){
        return articleService.getArticleById(aid);
    }

    @PutMapping(value = "/dustbin")
    public ResponseBean updateArticleState(Long[] aids, Integer state) {
        if (articleService.updateArticleState(aids, state) == aids.length) {
            return new ResponseBean("success", "删除成功!");
        }
        return new ResponseBean("error", "删除失败!");
    }




}
