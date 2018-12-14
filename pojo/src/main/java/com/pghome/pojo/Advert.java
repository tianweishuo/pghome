package com.pghome.pojo;

/**
 * @Auther: tianws
 * @Date: 2018/11/29 10:20
 * @Description:
 */
public class Advert {

    //id
    private Integer id;
    //标题
    private String title;
    //图片名
    private String imgFileName;
    //连接地址
    private String link;
    //权重
    private Integer weight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
