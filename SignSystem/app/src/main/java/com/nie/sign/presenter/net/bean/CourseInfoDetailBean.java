package com.nie.sign.presenter.net.bean;

public class CourseInfoDetailBean {

    /**
     * id : 17
     * userInfo : {"id":12,"account":"117163666","pwd":"c487dee60db613483ffd7b4a27abbc94","userName":null,"sex":null,"email":"1171636668@qq.com","phone":null,"status":"Normal_SYS","lastLogin":"2020-07-02 07:15:28","createDate":"2020-07-02 07:10:20","modifyDate":null,"createBy":"117163666","modifyBy":null,"role":{"modifyBy":null,"createBy":null,"modifyDate":null,"roleId":2,"roleName":"admin","userType":"Admin_SYS","detail":null,"createDate":null}}
     * course : {"courseName":"软件技术172","location":null,"startWeek":null,"endWeek":null,"startSection":null,"endSection":null,"semester":null,"status":1,"lng":0,"lat":0,"createDate":"2020-07-02 07:11:52","modifyDate":"2020-07-02 07:16:00","createBy":"117163666","modifyBy":"117163666","id":16}
     * score : 0
     * createDate : 2020-07-02 07:11:53
     * modifyDate : null
     * createBy : 117163666
     * modifyBy : null
     * isCharge : 1
     * status : 1
     * userName : null
     * courseName : 软件技术172
     * account : 117163666
     * userId : 12
     * email : 1171636668@qq.com
     * lastLogin : 2020-07-02T07:15:28.000+0000
     * phone : null
     * sex : null
     * courseId : 16
     * userStatus : Normal_SYS
     */

    private int id;
    private UserInfoBean userInfo;
    private CourseBean course;
    private int score;
    private String createDate;
    private Object modifyDate;
    private String createBy;
    private Object modifyBy;
    private int isCharge;
    private int status;
    private Object userName;
    private String courseName;
    private String account;
    private int userId;
    private String email;
    private String lastLogin;
    private Object phone;
    private Object sex;
    private int courseId;
    private String userStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public CourseBean getCourse() {
        return course;
    }

    public void setCourse(CourseBean course) {
        this.course = course;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Object getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Object modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Object getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Object modifyBy) {
        this.modifyBy = modifyBy;
    }

    public int getIsCharge() {
        return isCharge;
    }

    public void setIsCharge(int isCharge) {
        this.isCharge = isCharge;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getSex() {
        return sex;
    }

    public void setSex(Object sex) {
        this.sex = sex;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public static class UserInfoBean {
        /**
         * id : 12
         * account : 117163666
         * pwd : c487dee60db613483ffd7b4a27abbc94
         * userName : null
         * sex : null
         * email : 1171636668@qq.com
         * phone : null
         * status : Normal_SYS
         * lastLogin : 2020-07-02 07:15:28
         * createDate : 2020-07-02 07:10:20
         * modifyDate : null
         * createBy : 117163666
         * modifyBy : null
         * role : {"modifyBy":null,"createBy":null,"modifyDate":null,"roleId":2,"roleName":"admin","userType":"Admin_SYS","detail":null,"createDate":null}
         */

        private int id;
        private String account;
        private String pwd;
        private Object userName;
        private Object sex;
        private String email;
        private Object phone;
        private String status;
        private String lastLogin;
        private String createDate;
        private Object modifyDate;
        private String createBy;
        private Object modifyBy;
        private RoleBean role;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public Object getUserName() {
            return userName;
        }

        public void setUserName(Object userName) {
            this.userName = userName;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLastLogin() {
            return lastLogin;
        }

        public void setLastLogin(String lastLogin) {
            this.lastLogin = lastLogin;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public Object getModifyDate() {
            return modifyDate;
        }

        public void setModifyDate(Object modifyDate) {
            this.modifyDate = modifyDate;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public Object getModifyBy() {
            return modifyBy;
        }

        public void setModifyBy(Object modifyBy) {
            this.modifyBy = modifyBy;
        }

        public RoleBean getRole() {
            return role;
        }

        public void setRole(RoleBean role) {
            this.role = role;
        }

        public static class RoleBean {
            /**
             * modifyBy : null
             * createBy : null
             * modifyDate : null
             * roleId : 2
             * roleName : admin
             * userType : Admin_SYS
             * detail : null
             * createDate : null
             */

            private Object modifyBy;
            private Object createBy;
            private Object modifyDate;
            private int roleId;
            private String roleName;
            private String userType;
            private Object detail;
            private Object createDate;

            public Object getModifyBy() {
                return modifyBy;
            }

            public void setModifyBy(Object modifyBy) {
                this.modifyBy = modifyBy;
            }

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public Object getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(Object modifyDate) {
                this.modifyDate = modifyDate;
            }

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

            public Object getDetail() {
                return detail;
            }

            public void setDetail(Object detail) {
                this.detail = detail;
            }

            public Object getCreateDate() {
                return createDate;
            }

            public void setCreateDate(Object createDate) {
                this.createDate = createDate;
            }
        }
    }

    public static class CourseBean {
        /**
         * courseName : 软件技术172
         * location : null
         * startWeek : null
         * endWeek : null
         * startSection : null
         * endSection : null
         * semester : null
         * status : 1
         * lng : 0.0
         * lat : 0.0
         * createDate : 2020-07-02 07:11:52
         * modifyDate : 2020-07-02 07:16:00
         * createBy : 117163666
         * modifyBy : 117163666
         * id : 16
         */

        private String courseName;
        private Object location;
        private Object startWeek;
        private Object endWeek;
        private Object startSection;
        private Object endSection;
        private Object semester;
        private int status;
        private double lng;
        private double lat;
        private String createDate;
        private String modifyDate;
        private String createBy;
        private String modifyBy;
        private int id;

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public Object getLocation() {
            return location;
        }

        public void setLocation(Object location) {
            this.location = location;
        }

        public Object getStartWeek() {
            return startWeek;
        }

        public void setStartWeek(Object startWeek) {
            this.startWeek = startWeek;
        }

        public Object getEndWeek() {
            return endWeek;
        }

        public void setEndWeek(Object endWeek) {
            this.endWeek = endWeek;
        }

        public Object getStartSection() {
            return startSection;
        }

        public void setStartSection(Object startSection) {
            this.startSection = startSection;
        }

        public Object getEndSection() {
            return endSection;
        }

        public void setEndSection(Object endSection) {
            this.endSection = endSection;
        }

        public Object getSemester() {
            return semester;
        }

        public void setSemester(Object semester) {
            this.semester = semester;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getModifyDate() {
            return modifyDate;
        }

        public void setModifyDate(String modifyDate) {
            this.modifyDate = modifyDate;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getModifyBy() {
            return modifyBy;
        }

        public void setModifyBy(String modifyBy) {
            this.modifyBy = modifyBy;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
