package com.zkht.studydemo.hoverheaderrecyclerview;

/**
 * @author why
 * @date 2019/4/16 - 16:13
 */
public class Bean {
    private String text;
    private String groupName;

    public Bean(String text, String groupName) {
        this.text = text;
        this.groupName = groupName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
