package com.lyd.mapper;

import com.github.pagehelper.PageInfo;
import com.lyd.pojo.Novel;
import com.lyd.pojo.UserCollection;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NovelMapper {

    public Novel queryNovelByRank(int rank);
    public int[] queryAllRank();

    public List<Novel> queryAllNovels();

    public List<Novel> queryNovelRecommended();

    public List<Novel> queryNovelByKind(String kind);

    public List<Novel> queryNovelCollection(String name);

    public void addColletion(UserCollection userCollection);

    public List<Novel> search(String name);

    public int addNovel(Novel novel);

    public int isPass(String name);


}
