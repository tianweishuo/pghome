package com.pghome.pojo.wx;

/**
 * @Auther: tianws
 * @Date: 2018/11/23 14:34
 * @Description:
 */
public class Menu {
    public final static String CLICK = "click"; // click菜单
    public final static String VIEW = "view"; // url菜单
    public final static String SCANCODE_PUSH = "scancode_push"; // 扫码推事件
    public final static String SCANCODE_WAITMSG = "scancode_waitmsg"; // 扫码带提示
    public final static String PIC_SYSPHOTO = "pic_sysphoto"; // 系统拍照发图
    public final static String PIC_PHOTO_OR_ALBUM = "pic_photo_or_album"; // 拍照或者相册发图
    public final static String PIC_WEIXIN = "pic_weixin"; // 微信相册发图
    public final static String LOCATION_SELECT = "location_select"; // 发送位置
    public final static String MEDIA_ID = "media_id"; // 下发消息
    public final static String VIEW_LIMITED = "view_limited";//

    private BasicButton[] button;

    public BasicButton[] getButton() {
        return button;
    }

    public void setButton(BasicButton[] button) {
        this.button = button;
    }
}

