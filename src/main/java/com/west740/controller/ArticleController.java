package com.west740.controller;

import com.west740.bean.Article;
import com.west740.bean.ResponseBean;
import com.west740.service.ArticleService;
import com.west740.utils.Util;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        if(result==1){
            return new ResponseBean("success",article.getId()+"");
        }else {
            return new ResponseBean("error",article.getState()==0?"文章保存失败":"文章发表失败");
        }
    }


    @RequestMapping(value = "/uploadimg", method = RequestMethod.POST)
    public ResponseBean uploadImg(HttpServletRequest req, MultipartFile image) {
        StringBuffer url = new StringBuffer();
        String filePath = "/blogimg/" + simpleDateFormat.format(new Date());
        String imgFolderPath = req.getServletContext().getRealPath(filePath);
        File imgFolder = new File(imgFolderPath);
        if (!imgFolder.exists()) {
            imgFolder.mkdirs();
        }
        url.append(req.getScheme())
                .append("://")
                .append(req.getServerName())
                .append(":")
                .append(req.getServerPort())
                .append(req.getContextPath())
                .append(filePath);
        String imgName = UUID.randomUUID() + "_" + image.getOriginalFilename().replaceAll(" ", "");
        try {
            IOUtils.write(image.getBytes(), new FileOutputStream(new File(imgFolder, imgName)));
            url.append("/").append(imgName);
            return new ResponseBean("success", url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseBean("error", "上传失败!");
    }

    @GetMapping("/{aid}")
    public Article getArticleById(@PathVariable Long aid){
        Article article = articleService.getArticleById(aid);
        System.out.println(article);
        return article;
    }

    @PutMapping(value = "/dustbin")
    public ResponseBean updateArticleState(Long[] aids, Integer state) {
        if (articleService.updateArticleState(aids, state) == aids.length) {
            return new ResponseBean("success", "删除成功!");
        }
        return new ResponseBean("error", "删除失败!");
    }

    @GetMapping("/all")
    public Map<String,Object> getArticleByState(@RequestParam(value = "state",defaultValue = "-1") Integer state,
                                                @RequestParam(value = "page",defaultValue = "1") Integer page,
                                                @RequestParam(value = "count",defaultValue = "6") Integer size,
                                                String keywords){
        Long uid = Util.getCurrentUser().getId();
        int totalCount = articleService.getArticleCountByState(state,uid,keywords);
        List<Article> articles = articleService.getArticleByState(state,page,size,keywords);
        Map<String,Object> map = new HashMap<>();
        map.put("totalCount",totalCount);
        map.put("articles",articles);
        return map;
    }


    @PutMapping(value = "/restore")
    public ResponseBean restoreArticle(Integer articleId){
        if (articleService.restoreArticle(articleId) == 1) {
            return new ResponseBean("success","还原成功");
        }
        return new ResponseBean("error","还原失败");

    }

    @RequestMapping("/dataStatistics")
    public Map<String,Object> dataStatistics() {
        Map<String, Object> map = new HashMap<>();
        List<String> categories = articleService.getCategories();
        List<Integer> dataStatistics = articleService.getDataStatistics();
        map.put("categories", categories);
        map.put("ds", dataStatistics);
        return map;
    }

}
