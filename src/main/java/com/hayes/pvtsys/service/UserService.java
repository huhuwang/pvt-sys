package com.hayes.pvtsys.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.hayes.pvtsys.dto.PageResponse;
import com.hayes.pvtsys.pojo.PVTUser;
import com.hayes.pvtsys.query.UserQuery;
import com.hayes.pvtsys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public PageResponse<PVTUser> findPage(UserQuery query){

        Pageable pageRequest = PageRequest.of(query.getPageNum() - 1, query.getPageSize(), Sort.by(Sort.Direction.DESC, "createTime", "id"));
        Page<PVTUser> users = userRepository.findPage(pageRequest, query);
        return new PageResponse<>(users);
    }

    public void addUser(PVTUser user){
        if (user.getId() != null){
            PVTUser pvtUser = userRepository.findById(user.getId()).orElseThrow();
            pvtUser.setAccount(user.getAccount());
            pvtUser.setUserName(user.getUserName());
            pvtUser.setNickName(user.getNickName());
            pvtUser.setRole(user.getRole());
            userRepository.save(pvtUser);
        } else {
            String password = user.getPassword();
            String newPassword = SecureUtil.sha256(password);
            user.setPassword(newPassword);
            userRepository.save(user);
        }
    }

    public void deleteUser(Integer userId){
        userRepository.deleteById(userId);
    }

    public PVTUser queryUser(Integer userId){
        return userRepository.findById(userId).orElseThrow();
    }

    public String resetPaw(Integer userId){
        PVTUser pvtUser = userRepository.findById(userId).orElseThrow();
        String newPaw = RandomUtil.randomString(6);
        String newPassword = SecureUtil.sha256(newPaw);
        pvtUser.setPassword(newPassword);
        userRepository.save(pvtUser);
        return newPaw;
    }
}
