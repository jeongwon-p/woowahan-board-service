package com.woowahan.woowahanboardservice.domain.hackernews.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woowahan.woowahanboardservice.domain.hackernews.excpection.ConnectionException;
import com.woowahan.woowahanboardservice.domain.hackernews.excpection.InternalServerException;
import com.woowahan.woowahanboardservice.domain.hackernews.model.HackerNewsStory;

import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;

public class HackerNewsHelper {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static List<Integer> getLatelyNewStoryIdsByLimit(int limit) {
        List<Integer> newStories;
        URL url;
        try {
            url = new URL(HackerNewsConstants.HACKER_NEWS_API_BASE_URL + HackerNewsConstants.NEW_STORIES_END_POINT
                    + HackerNewsConstants.ENDPOINT_SUFFIX + HackerNewsConstants.ORDER_BY_KEY_SUFFIX + HackerNewsConstants.LIMIT_TO_FIRST_SUFFIX + limit);
            newStories = objectMapper.readValue(url, new TypeReference<List<Integer>>() {
            });
        } catch (UnknownHostException e) {
            throw new ConnectionException();
        } catch (Exception e) {
            throw new InternalServerException();
        }
        return newStories;
    }

    public static HackerNewsStory getStory(int storyId) {
        HackerNewsStory story = null;
        try {
            story = objectMapper.readValue(new URL(HackerNewsConstants.HACKER_NEWS_API_BASE_URL + HackerNewsConstants.ITEM_END_POINT
                    + storyId + HackerNewsConstants.ENDPOINT_SUFFIX), HackerNewsStory.class);
        } catch (UnknownHostException e) {
            throw new ConnectionException();
        } catch (Exception e) {
            throw new InternalServerException();
        }
        return story;
    }
}
