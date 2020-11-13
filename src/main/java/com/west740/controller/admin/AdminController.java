package com.west740.controller.admin;

import com.west740.bean.Article;
import com.west740.bean.ResponseBean;
import com.west740.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lucifer
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ArticleService articleService;


    @GetMapping(value = "/article/all")
    public Map<String,Object> getArticleByStateByAdmin(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                                       @RequestParam(value = "count",defaultValue = "6")Integer count,
                                                       String keywords){
        List<Article> articles = articleService.getArticleByState(-2,page,count,keywords);
        Map<String,Object> map = new HashMap<>();
        map.put("articles",articles);
        map.put("totalCount",articleService.getArticleCountByState(1,null,keywords));
        return map;
    }

    @PutMapping(value = "/article/dusbin")
    public ResponseBean updateArticleState(Long[] aids,Integer state){
        if(articleService.updateArticleState(aids,state) == aids.length){
            return new ResponseBean("success","删除成功！");
        }
        return new ResponseBean("error","删除失败！");
    }

}
