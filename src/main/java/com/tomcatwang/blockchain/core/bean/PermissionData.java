package com.tomcatwang.blockchain.core.bean;

import java.util.List;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
public class PermissionData extends BaseData {
    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
