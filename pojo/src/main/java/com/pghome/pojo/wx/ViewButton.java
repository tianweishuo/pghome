package com.pghome.pojo.wx;

/**
 * @Auther: tianws
 * @Date: 2018/11/23 14:53
 * @Description:
 */
public class ViewButton extends BasicButton {

    private String type = Menu.VIEW;
    private String url;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
