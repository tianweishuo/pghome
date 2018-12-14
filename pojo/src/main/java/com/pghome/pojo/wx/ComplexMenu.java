package com.pghome.pojo.wx;

/**
 * @Auther: tianws
 * @Date: 2018/11/23 14:54
 * @Description: 级菜单包含二级菜单的封装
 */
public class ComplexMenu extends BasicButton{
    private BasicButton[] sub_button;

    public BasicButton[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(BasicButton[] sub_button) {
        this.sub_button = sub_button;
    }
}
