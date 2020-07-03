package com.nie.sign.presenter.net.bean;

public class UserInfoBean {

    /**
     * userRoleId : 34
     * roleName : common_user
     * role : {"roleId":3,"roleName":"common_user","userType":"User_SYS","createDate":null,"modifyDate":null,"createBy":null,"modifyBy":null,"detail":null}
     * userId : 33
     * uname : null
     * email : 1171636667@qq.com
     * lastLogin : null
     * status : Valid
     * loginDate : 2020-06-30T15:18:07.000+0000
     * account : 117163666
     * userType : User_SYS
     * roleId : 3
     */

    private int userRoleId;
    private String roleName;
    private RoleBean role;
    private int userId;
    private Object uname;
    private String email;
    private Object lastLogin;
    private String status;
    private String loginDate;
    private String account;
    private String userType;
    private int roleId;

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public RoleBean getRole() {
        return role;
    }

    public void setRole(RoleBean role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Object getUname() {
        return uname;
    }

    public void setUname(Object uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Object lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public static class RoleBean {
        /**
         * roleId : 3
         * roleName : common_user
         * userType : User_SYS
         * createDate : null
         * modifyDate : null
         * createBy : null
         * modifyBy : null
         * detail : null
         */

        private int roleId;
        private String roleName;
        private String userType;
        private Object createDate;
        private Object modifyDate;
        private Object createBy;
        private Object modifyBy;
        private Object detail;

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public Object getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Object createDate) {
            this.createDate = createDate;
        }

        public Object getModifyDate() {
            return modifyDate;
        }

        public void setModifyDate(Object modifyDate) {
            this.modifyDate = modifyDate;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getModifyBy() {
            return modifyBy;
        }

        public void setModifyBy(Object modifyBy) {
            this.modifyBy = modifyBy;
        }

        public Object getDetail() {
            return detail;
        }

        public void setDetail(Object detail) {
            this.detail = detail;
        }
    }
}
