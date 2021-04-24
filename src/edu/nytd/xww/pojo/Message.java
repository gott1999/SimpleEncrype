package edu.nytd.xww.pojo;

/**
 * @author Yanyu
 * @date 2021年4月23日
 */
public class Message {
    private String url;
    private String text;

    public Message(String url, String text) {
        this.url = url;
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
