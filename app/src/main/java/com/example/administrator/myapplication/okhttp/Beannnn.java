package com.example.administrator.myapplication.okhttp;

import java.util.List;

/**
 * Created by xcy on 2017/8/31 0031.
 */

public class Beannnn {


    private int result;
    private ReDataBean reData;

    @Override
    public String toString() {
        return "Beannnn{" +
                "result=" + result +
                ", reData=" + reData +
                '}';
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ReDataBean getReData() {
        return reData;
    }

    public void setReData(ReDataBean reData) {
        this.reData = reData;
    }

    public static class ReDataBean {
        @Override
        public String toString() {
            return "ReDataBean{" +
                    "adUrl=" + adUrl +
                    ", stage=" + stage +
                    ", progress=" + progress +
                    ", currentPager=" + currentPager +
                    ", pager=" + pager +
                    ", https=" + https +
                    ", banner=" + banner +
                    ", rows=" + rows +
                    '}';
        }

        private AdUrlBean adUrl;
        private int stage;
        private int progress;
        private int currentPager;
        private PagerBean pager;
        private int https;
        private List<BannerBean> banner;
        private List<RowsBean> rows;

        public AdUrlBean getAdUrl() {
            return adUrl;
        }

        public void setAdUrl(AdUrlBean adUrl) {
            this.adUrl = adUrl;
        }

        public int getStage() {
            return stage;
        }

        public void setStage(int stage) {
            this.stage = stage;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public int getCurrentPager() {
            return currentPager;
        }

        public void setCurrentPager(int currentPager) {
            this.currentPager = currentPager;
        }

        public PagerBean getPager() {
            return pager;
        }

        public void setPager(PagerBean pager) {
            this.pager = pager;
        }

        public int getHttps() {
            return https;
        }

        public void setHttps(int https) {
            this.https = https;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class AdUrlBean {
            @Override
            public String toString() {
                return "AdUrlBean{" +
                        "id=" + id +
                        ", adPicUrl='" + adPicUrl + '\'' +
                        ", adLinkUrl='" + adLinkUrl + '\'' +
                        '}';
            }

            /**
             * id : 1
             * adPicUrl :
             * adLinkUrl :
             */

            private int id;
            private String adPicUrl;
            private String adLinkUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAdPicUrl() {
                return adPicUrl;
            }

            public void setAdPicUrl(String adPicUrl) {
                this.adPicUrl = adPicUrl;
            }

            public String getAdLinkUrl() {
                return adLinkUrl;
            }

            public void setAdLinkUrl(String adLinkUrl) {
                this.adLinkUrl = adLinkUrl;
            }
        }

        public static class PagerBean {
            /**
             * desc : true
             * length : 10
             * totalPager : 108
             * totalItem : 1077
             */

            private String desc;
            private int length;
            private int totalPager;
            private int totalItem;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
                this.length = length;
            }

            public int getTotalPager() {
                return totalPager;
            }

            public void setTotalPager(int totalPager) {
                this.totalPager = totalPager;
            }

            public int getTotalItem() {
                return totalItem;
            }

            public void setTotalItem(int totalItem) {
                this.totalItem = totalItem;
            }
        }

        public static class BannerBean {
            /**
             * id : 0
             * imgUrl : http://ott.wansecheng.com/adv/201708/147189_org_yrem5baj463a.jpg
             * imgWebUrl : http://activity.px.ewanse.com/818
             * bannerTittle : 商学院APP喊麦
             */

            private int id;
            private String imgUrl;
            private String imgWebUrl;
            private String bannerTittle;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getImgWebUrl() {
                return imgWebUrl;
            }

            public void setImgWebUrl(String imgWebUrl) {
                this.imgWebUrl = imgWebUrl;
            }

            public String getBannerTittle() {
                return bannerTittle;
            }

            public void setBannerTittle(String bannerTittle) {
                this.bannerTittle = bannerTittle;
            }
        }

        public static class RowsBean {
            @Override
            public String toString() {
                return "RowsBean{" +
                        "id='" + id + '\'' +
                        ", stage='" + stage + '\'' +
                        ", type_id='" + type_id + '\'' +
                        ", user_id='" + user_id + '\'' +
                        ", course_title='" + course_title + '\'' +
                        ", desc='" + desc + '\'' +
                        ", content='" + content + '\'' +
                        ", is_live='" + is_live + '\'' +
                        ", live_url='" + live_url + '\'' +
                        ", live_pwd='" + live_pwd + '\'' +
                        ", level='" + level + '\'' +
                        ", add_time='" + add_time + '\'' +
                        ", file_path='" + file_path + '\'' +
                        ", s_time='" + s_time + '\'' +
                        ", e_time='" + e_time + '\'' +
                        ", cover_img='" + cover_img + '\'' +
                        ", click_num='" + click_num + '\'' +
                        ", check_status='" + check_status + '\'' +
                        ", check_time=" + check_time +
                        ", approval_com=" + approval_com +
                        ", admin_id='" + admin_id + '\'' +
                        ", hot_order='" + hot_order + '\'' +
                        ", credits='" + credits + '\'' +
                        ", score='" + score + '\'' +
                        ", is_recommend='" + is_recommend + '\'' +
                        ", is_del='" + is_del + '\'' +
                        ", course_type='" + course_type + '\'' +
                        ", status='" + status + '\'' +
                        ", do_time='" + do_time + '\'' +
                        ", c_size='" + c_size + '\'' +
                        ", audio='" + audio + '\'' +
                        ", record_length='" + record_length + '\'' +
                        ", record_overtime='" + record_overtime + '\'' +
                        ", is_lock='" + is_lock + '\'' +
                        ", lecturer_id='" + lecturer_id + '\'' +
                        ", label_id='" + label_id + '\'' +
                        ", stage_id='" + stage_id + '\'' +
                        ", is_withdraw='" + is_withdraw + '\'' +
                        ", course_profit_click_num='" + course_profit_click_num + '\'' +
                        ", is_download='" + is_download + '\'' +
                        ", studyStatus=" + studyStatus +
                        ", type_name='" + type_name + '\'' +
                        ", stage_name='" + stage_name + '\'' +
                        ", studyCount=" + studyCount +
                        ", courseType=" + courseType +
                        ", is_mycourse=" + is_mycourse +
                        '}';
            }

            private String id;
            private String stage;
            private String type_id;
            private String user_id;
            private String course_title;
            private String desc;
            private String content;
            private String is_live;
            private String live_url;
            private String live_pwd;
            private String level;
            private String add_time;
            private String file_path;
            private String s_time;
            private String e_time;
            private String cover_img;
            private String click_num;
            private String check_status;
            private Object check_time;
            private Object approval_com;
            private String admin_id;
            private String hot_order;
            private String credits;
            private String score;
            private String is_recommend;
            private String is_del;
            private String course_type;
            private String status;
            private String do_time;
            private String c_size;
            private String audio;
            private String record_length;
            private String record_overtime;
            private String is_lock;
            private String lecturer_id;
            private String label_id;
            private String stage_id;
            private String is_withdraw;
            private String course_profit_click_num;
            private String is_download;
            private int studyStatus;
            private String type_name;
            private String stage_name;
            private int studyCount;
            private int courseType;
            private int is_mycourse;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getStage() {
                return stage;
            }

            public void setStage(String stage) {
                this.stage = stage;
            }

            public String getType_id() {
                return type_id;
            }

            public void setType_id(String type_id) {
                this.type_id = type_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getCourse_title() {
                return course_title;
            }

            public void setCourse_title(String course_title) {
                this.course_title = course_title;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getIs_live() {
                return is_live;
            }

            public void setIs_live(String is_live) {
                this.is_live = is_live;
            }

            public String getLive_url() {
                return live_url;
            }

            public void setLive_url(String live_url) {
                this.live_url = live_url;
            }

            public String getLive_pwd() {
                return live_pwd;
            }

            public void setLive_pwd(String live_pwd) {
                this.live_pwd = live_pwd;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getFile_path() {
                return file_path;
            }

            public void setFile_path(String file_path) {
                this.file_path = file_path;
            }

            public String getS_time() {
                return s_time;
            }

            public void setS_time(String s_time) {
                this.s_time = s_time;
            }

            public String getE_time() {
                return e_time;
            }

            public void setE_time(String e_time) {
                this.e_time = e_time;
            }

            public String getCover_img() {
                return cover_img;
            }

            public void setCover_img(String cover_img) {
                this.cover_img = cover_img;
            }

            public String getClick_num() {
                return click_num;
            }

            public void setClick_num(String click_num) {
                this.click_num = click_num;
            }

            public String getCheck_status() {
                return check_status;
            }

            public void setCheck_status(String check_status) {
                this.check_status = check_status;
            }

            public Object getCheck_time() {
                return check_time;
            }

            public void setCheck_time(Object check_time) {
                this.check_time = check_time;
            }

            public Object getApproval_com() {
                return approval_com;
            }

            public void setApproval_com(Object approval_com) {
                this.approval_com = approval_com;
            }

            public String getAdmin_id() {
                return admin_id;
            }

            public void setAdmin_id(String admin_id) {
                this.admin_id = admin_id;
            }

            public String getHot_order() {
                return hot_order;
            }

            public void setHot_order(String hot_order) {
                this.hot_order = hot_order;
            }

            public String getCredits() {
                return credits;
            }

            public void setCredits(String credits) {
                this.credits = credits;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(String is_recommend) {
                this.is_recommend = is_recommend;
            }

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }

            public String getCourse_type() {
                return course_type;
            }

            public void setCourse_type(String course_type) {
                this.course_type = course_type;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDo_time() {
                return do_time;
            }

            public void setDo_time(String do_time) {
                this.do_time = do_time;
            }

            public String getC_size() {
                return c_size;
            }

            public void setC_size(String c_size) {
                this.c_size = c_size;
            }

            public String getAudio() {
                return audio;
            }

            public void setAudio(String audio) {
                this.audio = audio;
            }

            public String getRecord_length() {
                return record_length;
            }

            public void setRecord_length(String record_length) {
                this.record_length = record_length;
            }

            public String getRecord_overtime() {
                return record_overtime;
            }

            public void setRecord_overtime(String record_overtime) {
                this.record_overtime = record_overtime;
            }

            public String getIs_lock() {
                return is_lock;
            }

            public void setIs_lock(String is_lock) {
                this.is_lock = is_lock;
            }

            public String getLecturer_id() {
                return lecturer_id;
            }

            public void setLecturer_id(String lecturer_id) {
                this.lecturer_id = lecturer_id;
            }

            public String getLabel_id() {
                return label_id;
            }

            public void setLabel_id(String label_id) {
                this.label_id = label_id;
            }

            public String getStage_id() {
                return stage_id;
            }

            public void setStage_id(String stage_id) {
                this.stage_id = stage_id;
            }

            public String getIs_withdraw() {
                return is_withdraw;
            }

            public void setIs_withdraw(String is_withdraw) {
                this.is_withdraw = is_withdraw;
            }

            public String getCourse_profit_click_num() {
                return course_profit_click_num;
            }

            public void setCourse_profit_click_num(String course_profit_click_num) {
                this.course_profit_click_num = course_profit_click_num;
            }

            public String getIs_download() {
                return is_download;
            }

            public void setIs_download(String is_download) {
                this.is_download = is_download;
            }

            public int getStudyStatus() {
                return studyStatus;
            }

            public void setStudyStatus(int studyStatus) {
                this.studyStatus = studyStatus;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public String getStage_name() {
                return stage_name;
            }

            public void setStage_name(String stage_name) {
                this.stage_name = stage_name;
            }

            public int getStudyCount() {
                return studyCount;
            }

            public void setStudyCount(int studyCount) {
                this.studyCount = studyCount;
            }

            public int getCourseType() {
                return courseType;
            }

            public void setCourseType(int courseType) {
                this.courseType = courseType;
            }

            public int getIs_mycourse() {
                return is_mycourse;
            }

            public void setIs_mycourse(int is_mycourse) {
                this.is_mycourse = is_mycourse;
            }
        }
    }
}
