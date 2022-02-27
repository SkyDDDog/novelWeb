package com.lyd.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyd.mapper.NovelMapper;
import com.lyd.mapper.UserMapper;
import com.lyd.pojo.Novel;
import com.lyd.pojo.UserCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NovelServiceImpl implements NovelService{

    @Autowired
    NovelMapper novelMapper;

    @Override
    public Novel queryNovelByRank(int rank) {
        return novelMapper.queryNovelByRank(rank);
    }

    @Override
    public int[] queryAllRank() {
        return novelMapper.queryAllRank();
    }

    @Override
    public List<Novel> queryAllNovels() {
        return novelMapper.queryAllNovels();
    }

    @Override
    public List<Novel> queryNovelRecommended() {
        return novelMapper.queryNovelRecommended();
    }

    @Override
    public List<Novel> queryNovelByKind(String kind) {
        return novelMapper.queryNovelByKind(kind);
    }

    @Override
    public List<Novel> queryNovelCollection(String name) {
        return novelMapper.queryNovelCollection(name);
    }

    @Override
    public void addColletion(UserCollection userCollection) {
        novelMapper.addColletion(userCollection);
    }

    @Override
    public List<Novel> search(String name) {
        return novelMapper.search(name);
    }

    @Override
    public int addNovel(Novel novel) {
        return novelMapper.addNovel(novel);
    }

    @Override
    public int isPass(String name) {
        return novelMapper.isPass(name);
    }

    @Override
    public List<Novel> findAllUserByPageF(int pageNum, int pageSize) {
        // TODO Auto-generated method stub
        PageHelper.startPage(pageNum, pageSize);
        List<Novel> lists = novelMapper.queryAllNovels();
        return lists;
    }

    @Override
    public PageInfo<Novel> findAllUserByPageS(int pageNum, int pageSize) {
        // TODO Auto-generated method stub
        PageHelper.startPage(pageNum, pageSize);
        List<Novel> lists = novelMapper.queryAllNovels();
        PageInfo<Novel> pageInfo = new PageInfo<Novel>(lists);
        return pageInfo;
    }

    @Override
    public List<Novel> getNovelName() {
        return novelMapper.getNovelName();
    }

    @Override
    public List<Novel> getUnPass() {
        return novelMapper.getUnPass();
    }
}
