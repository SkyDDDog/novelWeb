package com.lyd.controller;

import com.lyd.pojo.Novel;
import com.lyd.pojo.UserCollection;
import com.lyd.service.NovelService;
import com.lyd.utills.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "小说数据接口")
@RequestMapping("/novel")
@Slf4j
public class NovelController {
    @Autowired
    NovelService novelService;

    @RequestMapping(value = "/rank",method = RequestMethod.GET)
    @ApiOperation(value = "排行榜")
    public List<Novel> rank(){
        List<Novel> novels = new ArrayList<>();
        int[] ranks = novelService.queryAllRank();
        for (int rank:ranks) {
            novels.add(novelService.queryNovelByRank(rank));
        }
        log.info("按排名查询成功");
        return novels;
    }

    @RequestMapping(value = "/recommend",method = RequestMethod.GET)
    @ApiOperation(value = "推荐书目")
    public List<Novel> recommend(){
        List<Novel> novels = novelService.queryNovelRecommended();
        log.info("查询推荐书目成功");
        return novels;
    }

    @RequestMapping(value = "/kind/gd",method = RequestMethod.GET)
    @ApiOperation(value = "古典小说分类")
    public List<Novel> gdkind() {
        String kind = "古典";
        List<Novel> novels = novelService.queryNovelByKind(kind);
        log.info("查询 " + kind + "类 书籍成功");
        return novels;
    }

    @RequestMapping(value = "/kind/xy",method = RequestMethod.GET)
    @ApiOperation(value = "悬疑小说分类")
    public List<Novel> xykind() {
        String kind = "悬疑";
        List<Novel> novels = novelService.queryNovelByKind(kind);
        log.info("查询 " + kind + "类 书籍成功");
        return novels;
    }

    @RequestMapping(value = "/kind/xz",method = RequestMethod.GET)
    @ApiOperation(value = "修真小说分类")
    public List<Novel> xzkind() {
        String kind = "修真";
        List<Novel> novels = novelService.queryNovelByKind(kind);
        log.info("查询 " + kind + "类 书籍成功");
        return novels;
    }

    @RequestMapping(value = "/kind/xh",method = RequestMethod.GET)
    @ApiOperation(value = "玄幻小说分类")
    public List<Novel> xhkind() {
        String kind = "玄幻";
        List<Novel> novels = novelService.queryNovelByKind(kind);
        log.info("查询 " + kind + "类 书籍成功");
        return novels;
    }

    @RequestMapping(value = "/kind/ds",method = RequestMethod.GET)
    @ApiOperation(value = "都市小说分类")
    public List<Novel> dskind() {
        String kind = "都市";
        List<Novel> novels = novelService.queryNovelByKind(kind);
        log.info("查询 " + kind + "类 书籍成功");
        return novels;
    }

    @RequestMapping(value = "/kind/yx",method = RequestMethod.GET)
    @ApiOperation(value = "游戏小说分类")
    public List<Novel> yxkind() {
        String kind = "游戏";
        List<Novel> novels = novelService.queryNovelByKind(kind);
        log.info("查询 " + kind + "类 书籍成功");
        return novels;
    }

    @RequestMapping(value = "/collection",method = RequestMethod.GET)
    @ApiOperation(value = "查询用户收藏")
    public List<Novel> novelCollection(@RequestParam("用户名") String username) {
        List<Novel> novels = novelService.queryNovelCollection(username);
        log.info("查询 用户" + username + " 收藏成功");
        return novels;
    }

    @RequestMapping(value = "/addCollection",method = RequestMethod.POST)
    @ApiOperation(value = "添加收藏",notes = "数据前端给，不做校验")
    public Map<Integer, String> addCollection(@RequestParam("用户名")String username, @RequestParam("小说名")String novelName, HttpServletResponse response) {
        UserCollection userCollection = new UserCollection();
        userCollection.setUsername(username);
        userCollection.setNovelname(novelName);
        Map<Integer, String> map = new HashMap<>();
        novelService.addColletion(userCollection);
        map.put(ResultCommon.ADD_SUCCESS.getStateCode(), ResultCommon.ADD_SUCCESS.getStateDec());
        response.setStatus(ResultCommon.ADD_SUCCESS.getStateCode());
        log.info("添加收藏成功");
        return map;
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    @ApiOperation(value = "模糊查询",notes = "按小说名查询,简单的模糊查询，含有小说的字就行")
    public List<Novel> search(@RequestBody String name){
        List<Novel> novels = novelService.search(name);
        log.info("模糊查询 " + name + " 成功");
        return novels;
    }

    @RequestMapping(value = "/addNovel",method = RequestMethod.POST)
    @ApiOperation(value = "上传小说")
    public Map<Integer, String> addNovel(@RequestBody Novel novel, HttpServletResponse response) {
        Map<Integer, String> map = new HashMap<>();
        int i = novelService.addNovel(novel);
        if (i > 0) {
            map.put(ResultCommon.ADDNOVEL_SUCCESS.getStateCode(), ResultCommon.ADDNOVEL_SUCCESS.getStateDec());
            response.setStatus(ResultCommon.ADDNOVEL_SUCCESS.getStateCode());
            log.info("上传新小说信息 " + novel.toString() + " 成功");
            log.info("小说信息为:"+novel.toString());
        }else {
            map.put(ResultCommon.ADDNOVEL_FAILURE.getStateCode(), ResultCommon.ADDNOVEL_FAILURE.getStateDec());
            response.setStatus(ResultCommon.ADDNOVEL_FAILURE.getStateCode());
            log.error("上传新小说信息 " + novel.toString() + " 失败");
        }

        return map;
    }

    @RequestMapping(value = "/pass",method = RequestMethod.POST)
    @ApiOperation(value = "小说审核")
    public Map<Integer, String> pass(@RequestParam("小说名")String name,@RequestParam("是否通过审核(1为通过，0为不通过)")int isPass, HttpServletResponse response) {
        Map<Integer, String> map = new HashMap<>();
        if (isPass!=0) {
            novelService.isPass(name);
            map.put(ResultCommon.ADDNOVEL_SUCCESS.getStateCode(), ResultCommon.ADDNOVEL_SUCCESS.getStateDec());
            response.setStatus(ResultCommon.ADDNOVEL_SUCCESS.getStateCode());
            log.info("小说" + name + "审核通过");
        } else {
            map.put(ResultCommon.ADDNOVEL_UNPASS.getStateCode(), ResultCommon.ADDNOVEL_UNPASS.getStateDec());
            response.setStatus(ResultCommon.ADDNOVEL_UNPASS.getStateCode());
            log.error("小说" + name + "审核失败");
        }
        return map;
    }

    @RequestMapping(value = "/selectByPage",method = RequestMethod.GET)
    @ApiOperation(value = "分页获取所有小说",notes = "传入页码号，和每页的个数")
    public List<Novel> manageMember(@RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize){
        log.info("按页查询: 第" + pageNum + "页," + "该页含有" + pageSize + "条数据");
        return novelService.findAllUserByPageF(pageNum,pageSize);
    }


}
