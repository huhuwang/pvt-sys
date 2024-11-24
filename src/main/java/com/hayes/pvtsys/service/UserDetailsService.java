package com.hayes.pvtsys.service;

import com.hayes.pvtsys.dto.UserDto;
import com.hayes.pvtsys.pojo.PVTPermission;
import com.hayes.pvtsys.pojo.PVTUser;
import com.hayes.pvtsys.repository.PermissionRepository;
import com.hayes.pvtsys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissonRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        PVTUser user = userRepository.findPVTUserByAccount(username);
        if (user == null) {
            throw new RuntimeException("用户名或者密码错误");
        }

        List<PVTPermission> pvtPermissions = permissonRepository.queryAllByRole(user.getRole().getId());
        //查询权限信息
        List<String> auth = pvtPermissions.stream().map(PVTPermission::getPermissionCode).collect(Collectors.toList());
        //把数据封装成userDetail返回
        return new UserDto(user, auth);
    }
}
