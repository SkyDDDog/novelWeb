package com.lyd.service;

import com.github.pagehelper.PageInfo;
import com.lyd.pojo.Novel;
import com.lyd.pojo.UserCollection;

import java.util.List;

public interface NovelService {

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

    List<Novel> findAllUserByPageF(int pageNum,int pageSize);

    PageInfo<Novel> findAllUserByPageS(int pageNum, int pageSize);

}
