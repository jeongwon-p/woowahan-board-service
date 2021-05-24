package com.woowahan.woowahanboardservice.domain.hackernews.model;


import java.io.Serializable;
import java.util.Objects;

public class HackerNewsStory implements Serializable {

    private String by;

    private int score;

    private int time;

    private String title;

    private String url;

    public HackerNewsStory() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HackerNewsStory that = (HackerNewsStory) o;
        return score == that.score && time == that.time && by.equals(that.by) && title.equals(that.title) && url.equals(that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(by, score, time, title, url);
    }

    public String getBy() {
        return by;
    }

    public int getScore() {
        return score;
    }

    public int getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
