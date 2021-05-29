package com.woowahan.woowahanboardservice.domain.hackernews.helper;

public class HackerNewsConstants {
    // https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty&orderBy=%22$key%22&limitToFirst=10

    public static final String HACKER_NEWS_API_BASE_URL = "https://hacker-news.firebaseio.com/v0";
    public static final String NEW_STORIES_END_POINT = "/newstories";
    public static final String ITEM_END_POINT = "/item/";
    public static final String ENDPOINT_SUFFIX = ".json?print=pretty";
    public static final String ORDER_BY_KEY_SUFFIX = "&orderBy=%22$key%22";
    public static final String LIMIT_TO_FIRST_SUFFIX = "&limitToFirst=";
}
