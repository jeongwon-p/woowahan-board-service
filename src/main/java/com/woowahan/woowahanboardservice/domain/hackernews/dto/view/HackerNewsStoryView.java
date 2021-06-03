package com.woowahan.woowahanboardservice.domain.hackernews.dto.view;

import com.woowahan.woowahanboardservice.domain.hackernews.model.HackerNewsStory;

public class HackerNewsStoryView {

    private final String by;

    private final int score;

    private final int time;

    private final String title;

    private final String url;

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
