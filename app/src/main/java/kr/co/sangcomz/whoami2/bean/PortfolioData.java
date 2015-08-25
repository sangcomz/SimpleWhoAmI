package kr.co.sangcomz.whoami2.bean;

/**
 * Created by 석원 on 8/25/2015.
 */
public class PortfolioData {
    int imageResource;
    String title;
    String content;

    public PortfolioData(int imageResource, String title, String content){
        this.imageResource = imageResource;
        this.title = title;
        this.content = content;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
