package com.example.administrator.myapplication.okhttp;

/**
 * Created by xcy on 2017/8/31 0031.
 */

public class LoginResultBean {

    /**
     * biz_action : 0
     * biz_msg :
     * return_status : 0
     * data : {"nickname":"xcy","name":"许超阳","cell_phone":"15068160426",
     * "private_token":"c1bff948c5f6056c","im_password":"922dea992abf9707",
     * "im_id":"u-509-e495f3fb412b29d8d48ee596f6","birthday":"1991-11-07","user_id":509,
     * "staff_id":1782,"screen_name":"许超阳","avatar_url":"http://img.ishangchao.cn/default.png",
     * "group_id":12,"group_name":"","company_id":67,"company_name":"万色城","is_staff":true,
     * "is_match":false,"is_eject_warning":false,
     * "warning_content":"如果您是该公司员工，请联系HR核实姓名和手机号；如有问题，请联系上朝客服；","sign_in_limit":600,
     * "is_sync_address_list":true,"can_send_vedio":false}
     */

    private int biz_action;
    private String biz_msg;
    private int return_status;
    private DataBean data;

    public int getBiz_action() {
        return biz_action;
    }

    public void setBiz_action(int biz_action) {
        this.biz_action = biz_action;
    }

    public String getBiz_msg() {
        return biz_msg;
    }

    public void setBiz_msg(String biz_msg) {
        this.biz_msg = biz_msg;
    }

    public int getReturn_status() {
        return return_status;
    }

    public void setReturn_status(int return_status) {
        this.return_status = return_status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * nickname : xcy
         * name : 许超阳
         * cell_phone : 15068160426
         * private_token : c1bff948c5f6056c
         * im_password : 922dea992abf9707
         * im_id : u-509-e495f3fb412b29d8d48ee596f6
         * birthday : 1991-11-07
         * user_id : 509
         * staff_id : 1782
         * screen_name : 许超阳
         * avatar_url : http://img.ishangchao.cn/default.png
         * group_id : 12
         * group_name :
         * company_id : 67
         * company_name : 万色城
         * is_staff : true
         * is_match : false
         * is_eject_warning : false
         * warning_content : 如果您是该公司员工，请联系HR核实姓名和手机号；如有问题，请联系上朝客服；
         * sign_in_limit : 600
         * is_sync_address_list : true
         * can_send_vedio : false
         */

        private String nickname;
        private String name;
        private String cell_phone;
        private String private_token;
        private String im_password;
        private String im_id;
        private String birthday;
        private int user_id;
        private int staff_id;
        private String screen_name;
        private String avatar_url;
        private int group_id;
        private String group_name;
        private int company_id;
        private String company_name;
        private boolean is_staff;
        private boolean is_match;
        private boolean is_eject_warning;
        private String warning_content;
        private int sign_in_limit;
        private boolean is_sync_address_list;
        private boolean can_send_vedio;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCell_phone() {
            return cell_phone;
        }

        public void setCell_phone(String cell_phone) {
            this.cell_phone = cell_phone;
        }

        public String getPrivate_token() {
            return private_token;
        }

        public void setPrivate_token(String private_token) {
            this.private_token = private_token;
        }

        public String getIm_password() {
            return im_password;
        }

        public void setIm_password(String im_password) {
            this.im_password = im_password;
        }

        public String getIm_id() {
            return im_id;
        }

        public void setIm_id(String im_id) {
            this.im_id = im_id;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(int staff_id) {
            this.staff_id = staff_id;
        }

        public String getScreen_name() {
            return screen_name;
        }

        public void setScreen_name(String screen_name) {
            this.screen_name = screen_name;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public int getGroup_id() {
            return group_id;
        }

        public void setGroup_id(int group_id) {
            this.group_id = group_id;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public int getCompany_id() {
            return company_id;
        }

        public void setCompany_id(int company_id) {
            this.company_id = company_id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public boolean isIs_staff() {
            return is_staff;
        }

        public void setIs_staff(boolean is_staff) {
            this.is_staff = is_staff;
        }

        public boolean isIs_match() {
            return is_match;
        }

        public void setIs_match(boolean is_match) {
            this.is_match = is_match;
        }

        public boolean isIs_eject_warning() {
            return is_eject_warning;
        }

        public void setIs_eject_warning(boolean is_eject_warning) {
            this.is_eject_warning = is_eject_warning;
        }

        public String getWarning_content() {
            return warning_content;
        }

        public void setWarning_content(String warning_content) {
            this.warning_content = warning_content;
        }

        public int getSign_in_limit() {
            return sign_in_limit;
        }

        public void setSign_in_limit(int sign_in_limit) {
            this.sign_in_limit = sign_in_limit;
        }

        public boolean isIs_sync_address_list() {
            return is_sync_address_list;
        }

        public void setIs_sync_address_list(boolean is_sync_address_list) {
            this.is_sync_address_list = is_sync_address_list;
        }

        public boolean isCan_send_vedio() {
            return can_send_vedio;
        }

        public void setCan_send_vedio(boolean can_send_vedio) {
            this.can_send_vedio = can_send_vedio;
        }
    }
}
