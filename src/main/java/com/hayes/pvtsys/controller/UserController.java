package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.dto.PageResponse;
import com.hayes.pvtsys.pojo.PVTUser;
import com.hayes.pvtsys.query.UserQuery;
import com.hayes.pvtsys.service.UserService;
import com.hayes.pvtsys.util.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/page")
    @PreAuthorize("hasAnyAuthority('list-user')")
    public HttpResult<PageResponse<PVTUser>> queryPagee(@RequestBody  UserQuery query){
        PageResponse<PVTUser> page = userService.findPage(query);
        return HttpResult.returnSuccess(page);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('add-user')")
    public HttpResult<Boolean> addUser(@RequestBody PVTUser user){
        userService.addUser(user);
        return HttpResult.returnSuccess(true);
    }

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAnyAuthority('delete-user')")
    public HttpResult<Boolean> deleteUser(@PathVariable("userId") Integer userId){
        userService.deleteUser(userId);
        return HttpResult.returnSuccess(true);
    }

    @GetMapping("/query/{userId}")
    @PreAuthorize("hasAnyAuthority('query-user')")
    public HttpResult<PVTUser> queryUser(@PathVariable("userId") Integer userId){
        PVTUser pvtUser = userService.queryUser(userId);
        return HttpResult.returnSuccess(pvtUser);
    }

    @PostMapping("/reset/{userId}")
    @PreAuthorize("hasAnyAuthority('reset-paw')")
    public HttpResult<String> resetPaw(@PathVariable("userId") Integer userId){
        String newPaw = userService.resetPaw(userId);
        return HttpResult.returnSuccess(newPaw);
    }
}
