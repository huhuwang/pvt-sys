package com.hayes.pvtsys.dto;

import cn.hutool.core.collection.CollUtil;
import com.hayes.pvtsys.pojo.PVTUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto implements UserDetails {

    private PVTUser pvtUser;

    private List<String> permissions;

    public UserDto(PVTUser pvtUser, List<String> permissions) {
        this.pvtUser = pvtUser;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (CollUtil.isNotEmpty(permissions)){
           return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        return List.of();
    }

    @Override
    public String getPassword() {

        return pvtUser.getPassword();
    }

    @Override
    public String getUsername() {
        return pvtUser.getAccount();
    }
}
