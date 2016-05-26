package com.kk.imgod.knowgirl.model;

import java.util.List;

/**
 * 项目名称：KnowGirl
 * 包名称：com.kk.imgod.knowgirl.model
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016-05-26 10:26
 * 修改人：gaokang
 * 修改时间：2016-05-26 10:26
 * 修改备注：
 */
public class BooHeeModel {

    /**
     * hello_text : 黑夜给了我黑色的眼睛——我却用它来瞅你咋的？
     * back_img : http://up.boohee.cn/house/u/one/wallpaper/316_big.jpg
     * back_img_small : http://up.boohee.cn/house/u/one/wallpaper/316_small.jpg
     * date : 2016-05-26
     * week_images : [{"date":"2016-05-20","back_img":"http://up.boohee.cn/house/u/one/wallpaper/301_big.jpg"},{"date":"2016-05-21","back_img":"http://up.boohee.cn/house/u/one/wallpaper/302_big.jpg"},{"date":"2016-05-22","back_img":"http://up.boohee.cn/house/u/one/wallpaper/275_big.jpg"},{"date":"2016-05-23","back_img":"http://up.boohee.cn/house/u/one/wallpaper/318_big.jpg"},{"date":"2016-05-24","back_img":"http://up.boohee.cn/house/u/one/wallpaper/321_big.jpg"},{"date":"2016-05-25","back_img":"http://up.boohee.cn/house/u/one/wallpaper/319_big.jpg"},{"date":"2016-05-26","back_img":"http://up.boohee.cn/house/u/one/wallpaper/316_big.jpg"}]
     */

    private WelcomeImgBean welcome_img;
    /**
     * user_link : http://bingo.boohee.com/api/v1/user_diets.html
     * super_model_link : http://bingo.boohee.com/api/v1/user_diets/supermodel.html
     * data : [{"name":"breakfast","time_span":"00:00-10:00","content":"早餐建议：谷薯类270大卡、奶类135大卡、水果45大卡","detail":[{"name":"薄荷 红枣黑米圈","amount":70,"unit":"克","image_url":"http://f1.boohee.com/house/upload_food/2015/4/27/1550532_1430120845mid.jpg"},{"name":"牛奶","amount":1,"unit":"盒","image_url":"http://f1.boohee.com/house/food_mid/mid_photo_2015131143228872.jpg"},{"name":"橘子","amount":1,"unit":"个(小)","image_url":"http://f1.boohee.com/house/food_mid/mid_photo_20151311681587.jpg"}],"calory":450},{"name":"lunch","time_span":"10:00-14:00","content":"午餐建议：谷薯类270大卡、蔬菜类90大卡、肉蛋豆类135大卡、油脂类90大卡","detail":[{"name":"米饭","amount":250,"unit":"克","image_url":"http://f1.boohee.com/house/food_mid/mid_photo_201512613144756.jpg"},{"name":"蒜泥豆角","amount":140,"unit":"克","image_url":"http://f1.boohee.com/house/food_mid/mid_photo_2012815154582813.jpg"},{"name":"宫保鸡丁","amount":120,"unit":"克","image_url":"http://f1.boohee.com/house/food_mid/mid_photo_201281515324414407.jpg"}],"calory":585},{"name":"extral_meal","time_span":"14:00-16:00","content":"加餐建议：水果类135大卡","detail":[{"name":"苹果","amount":1,"unit":"个(中)","image_url":"http://f1.boohee.com/house/food_mid/mid_photo_2015126214658469.jpg"}],"calory":135},{"name":"dinner","time_span":"16:00-23:59","content":"晚餐建议：谷薯类180大卡、蔬菜类45大卡、肉蛋豆类90大卡、油脂类90大卡","detail":[{"name":"米饭","amount":1,"unit":"碗","image_url":"http://f1.boohee.com/house/food_mid/mid_photo_201512613144756.jpg"},{"name":"清炒豆苗","amount":110,"unit":"克","image_url":"http://f1.boohee.com/house/menu_mid/1161596076671_mid.jpg"},{"name":"凉拌豆腐皮","amount":70,"unit":"克","image_url":"http://f1.boohee.com/house/food_mid/mid_photo_201281010334915378.jpg"}],"calory":405}]
     */

    private DietPlanBean diet_plan;
    /**
     * name : HIIT 中级
     * date : 第 1/5 天
     */

    private SportsPlanBean sports_plan;

    public WelcomeImgBean getWelcome_img() {
        return welcome_img;
    }

    public void setWelcome_img(WelcomeImgBean welcome_img) {
        this.welcome_img = welcome_img;
    }

    public DietPlanBean getDiet_plan() {
        return diet_plan;
    }

    public void setDiet_plan(DietPlanBean diet_plan) {
        this.diet_plan = diet_plan;
    }

    public SportsPlanBean getSports_plan() {
        return sports_plan;
    }

    public void setSports_plan(SportsPlanBean sports_plan) {
        this.sports_plan = sports_plan;
    }

    public static class WelcomeImgBean {
        private String hello_text;
        private String back_img;
        private String back_img_small;
        private String date;
        /**
         * date : 2016-05-20
         * back_img : http://up.boohee.cn/house/u/one/wallpaper/301_big.jpg
         */

        private List<WeekImagesBean> week_images;

        public String getHello_text() {
            return hello_text;
        }

        public void setHello_text(String hello_text) {
            this.hello_text = hello_text;
        }

        public String getBack_img() {
            return back_img;
        }

        public void setBack_img(String back_img) {
            this.back_img = back_img;
        }

        public String getBack_img_small() {
            return back_img_small;
        }

        public void setBack_img_small(String back_img_small) {
            this.back_img_small = back_img_small;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<WeekImagesBean> getWeek_images() {
            return week_images;
        }

        public void setWeek_images(List<WeekImagesBean> week_images) {
            this.week_images = week_images;
        }

        public static class WeekImagesBean {
            private String date;
            private String back_img;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getBack_img() {
                return back_img;
            }

            public void setBack_img(String back_img) {
                this.back_img = back_img;
            }
        }
    }

    public static class DietPlanBean {
        private String user_link;
        private String super_model_link;
        /**
         * name : breakfast
         * time_span : 00:00-10:00
         * content : 早餐建议：谷薯类270大卡、奶类135大卡、水果45大卡
         * detail : [{"name":"薄荷 红枣黑米圈","amount":70,"unit":"克","image_url":"http://f1.boohee.com/house/upload_food/2015/4/27/1550532_1430120845mid.jpg"},{"name":"牛奶","amount":1,"unit":"盒","image_url":"http://f1.boohee.com/house/food_mid/mid_photo_2015131143228872.jpg"},{"name":"橘子","amount":1,"unit":"个(小)","image_url":"http://f1.boohee.com/house/food_mid/mid_photo_20151311681587.jpg"}]
         * calory : 450
         */

        private List<DataBean> data;

        public String getUser_link() {
            return user_link;
        }

        public void setUser_link(String user_link) {
            this.user_link = user_link;
        }

        public String getSuper_model_link() {
            return super_model_link;
        }

        public void setSuper_model_link(String super_model_link) {
            this.super_model_link = super_model_link;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            private String name;
            private String time_span;
            private String content;
            private int calory;
            /**
             * name : 薄荷 红枣黑米圈
             * amount : 70
             * unit : 克
             * image_url : http://f1.boohee.com/house/upload_food/2015/4/27/1550532_1430120845mid.jpg
             */

            private List<DetailBean> detail;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTime_span() {
                return time_span;
            }

            public void setTime_span(String time_span) {
                this.time_span = time_span;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getCalory() {
                return calory;
            }

            public void setCalory(int calory) {
                this.calory = calory;
            }

            public List<DetailBean> getDetail() {
                return detail;
            }

            public void setDetail(List<DetailBean> detail) {
                this.detail = detail;
            }

            public static class DetailBean {
                private String name;
                private int amount;
                private String unit;
                private String image_url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getAmount() {
                    return amount;
                }

                public void setAmount(int amount) {
                    this.amount = amount;
                }

                public String getUnit() {
                    return unit;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
                }

                public String getImage_url() {
                    return image_url;
                }

                public void setImage_url(String image_url) {
                    this.image_url = image_url;
                }
            }
        }
    }

    public static class SportsPlanBean {
        private String name;
        private String date;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
