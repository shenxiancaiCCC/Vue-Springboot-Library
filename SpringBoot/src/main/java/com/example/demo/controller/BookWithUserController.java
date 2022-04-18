package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.commom.Result;
import com.example.demo.entity.BookWithUser;
import com.example.demo.entity.BookWithUser;
import com.example.demo.mapper.BookWithUserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookwithuser")
public class BookWithUserController {
    @Resource
    BookWithUserMapper BookWithUserMapper;
//
//    @PostMapping
//    public Result<?> save(@RequestBody Book Book){
//        BookMapper.insert(Book);
//        return Result.success();
//    }

//    //    批量删除
//    @PostMapping("/deleteBatch")
//    public  Result<?> deleteBatch(@RequestBody List<Integer> ids){
//        BookMapper.deleteBatchIds(ids);
//        return Result.success();
//    }
//    @PutMapping
//    public  Result<?> update(@RequestBody Book Book){
//        BookMapper.updateById(Book);
//        return Result.success();
//    }
//    @DeleteMapping("/{id}")
//    public Result<?> delete(@PathVariable Long id){
//        BookMapper.deleteById(id);
//        return Result.success();
//    }
    @PostMapping("/insertNew")
    public Result<?> insertNew(@RequestBody BookWithUser BookWithUser){
        BookWithUserMapper.insert(BookWithUser);
        return Result.success();
    }
    @PostMapping
    public Result<?> update(@RequestBody BookWithUser BookWithUser){
        UpdateWrapper<BookWithUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("isbn",BookWithUser.getIsbn()).eq("id",BookWithUser.getId());
        BookWithUserMapper.update(BookWithUser, updateWrapper);
        return Result.success();
    }
//删除一条记录
    @PostMapping("/deleteRecord")
    public  Result<?> deleteRecord(@RequestBody BookWithUser BookWithUser){
        Map<String,Object> map = new HashMap<>();
        map.put("isbn",BookWithUser.getIsbn());
        map.put("id",BookWithUser.getId());
        BookWithUserMapper.deleteByMap(map);
        return Result.success();
    }

    @PostMapping("/deleteRecords")
    public Result<?> deleteRecords(@RequestBody List<BookWithUser> BookWithUsers){
        int len = BookWithUsers.size();
        for(int i=0;i<len;i++) {
            BookWithUser curRecord = BookWithUsers.get(i);
            Map<String,Object> map = new HashMap<>();
            map.put("isbn",curRecord.getIsbn());
            map.put("id",curRecord.getId());
            BookWithUserMapper.deleteByMap(map);
        }
        return Result.success();
    }
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search1,
                              @RequestParam(defaultValue = "") String search2,
                              @RequestParam(defaultValue = "") String search3){
        LambdaQueryWrapper<BookWithUser> wrappers = Wrappers.<BookWithUser>lambdaQuery();
        if(StringUtils.isNotBlank(search1)){
            wrappers.like(BookWithUser::getIsbn,search1);
        }
        if(StringUtils.isNotBlank(search2)){
            wrappers.like(BookWithUser::getBookName,search2);
        }
        if(StringUtils.isNotBlank(search3)){
            wrappers.like(BookWithUser::getId,search3);
        }
        Page<BookWithUser> BookPage =BookWithUserMapper.selectPage(new Page<>(pageNum,pageSize), wrappers);
        return Result.success(BookPage);
    }
}
