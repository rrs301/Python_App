package com.innocruts.python_app;

/**
 * Created by Rahul on 2/4/2016.
 */
public class FeedItem {
   String Title;
    String Description;
    String Code;
    String Output;
    String Button;

    public String getHeadingTopicId() {
        return HeadingTopicId;
    }

    public void setHeadingTopicId(String headingTopicId) {
        HeadingTopicId = headingTopicId;
    }

    String HeadingTopicId;
   String TopicName;
    String TopicExcercise;

    public String getTopic_Status() {
        return Topic_Status;
    }

    public void setTopic_Status(String topic_Status) {
        Topic_Status = topic_Status;
    }

    String Topic_Status;

    public String getTitleName() {
        return TitleName;
    }

    public void setTitleName(String titleName) {
        TitleName = titleName;
    }

    String TitleName;

    public String getTopicName() {
        return TopicName;
    }

    public void setTopicName(String topicName) {
        TopicName = topicName;
    }

    public String getTopicExcercise() {
        return TopicExcercise;
    }

    public void setTopicExcercise(String topicExcercise) {
        TopicExcercise = topicExcercise;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getOutput() {
        return Output;
    }

    public void setOutput(String output) {
        Output = output;
    }

    public String getButton() {
        return Button;
    }

    public void setButton(String button) {
        Button = button;
    }
}