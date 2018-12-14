package com.pghome.pojo.wx;

/**
 * @Auther: tianws
 * @Date: 2018/11/23 14:56
 * @Description:
 */
public class ClickButten extends BasicButton{
    private String type = Menu.CLICK;

    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
