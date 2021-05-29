package com.woowahan.woowahanboardservice.domain.hackernews.dto.view;

import com.woowahan.woowahanboardservice.domain.hackernews.model.HackerNewsStory;

public class HackerNewsStoryView {

    private String by;

    private int score;

    private int time;

    private String title;

    private String url;

    public HackerNewsStoryView(HackerNewsStory entity) {
        this.by = entity.getBy();
        this.score = entity.getScore();
        this.time = entity.getTime();
        this.title = entity.getTitle();
        this.url = entity.getUrl();
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
