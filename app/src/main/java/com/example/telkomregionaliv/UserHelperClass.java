package com.example.telkomregionaliv;

public class UserHelperClass {
    String content;

    public UserHelperClass() {
    }

    public UserHelperClass(String content, String format) {
        this.content = content;
        this.format = format;
    }

    String format;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
