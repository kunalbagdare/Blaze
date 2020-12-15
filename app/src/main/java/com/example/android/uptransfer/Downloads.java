package com.example.android.uptransfer;

public class Downloads {
    private String filename;
    private String link;

    public Downloads(){}
    public Downloads(String filename,String link){this.filename=filename; this.link=link;}

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFilename() {
        return filename;
    }

    public String getLink() {
        return link;
    }


}
