package com.hayes.pvtsys.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PermissionDto implements Serializable {

    private Integer roleId;

    private List<Integer> permissionIds;

}
