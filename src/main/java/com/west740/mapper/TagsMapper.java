package com.west740.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lucifer
 */
public interface TagsMapper {


    int deleteTagsByAid(Long aid);

    int saveTags(@Param("tags") String[] tags);

    List<Long> getTagsIdByTagName(@Param("tagNames") String[] tagNames);

    int saveTagsToArticleTags(@Param("tagIds") List<Long> tagIds, @Param("aid") Long aid);
}
