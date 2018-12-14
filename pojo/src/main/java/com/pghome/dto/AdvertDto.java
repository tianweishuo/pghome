package com.pghome.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: tianws
 * @Date: 2018/11/29 10:37
 * @Description:
 */
public class AdvertDto {
    //标题
    private String title;
    //图片名
    private MultipartFile imgFileName;
    //连接地址
    private String link;
    //权重
    private Integer weight;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(MultipartFile imgFileName) {
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
