package com.example.sberbank.main;

import android.text.Html;
import com.example.sberbank.models.Post;
import com.example.sberbank.models.PostEntity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostMapper {

    private static String PATTERN = "<img src=\"(.*?)\".*?>";

    public static PostEntity map(Post post) {

        PostEntity.Builder builder = new PostEntity.Builder(post.getTitle(), post.getLink());

        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher =pattern.matcher(post.getDescription());
        String content = "";
        if (matcher.find()){
            builder.imageUtl(matcher.group(1));
            content = post.getDescription().replace(matcher.group(0), "");
        } else content = post.getDescription();

        content = Html.fromHtml(content).toString();
        content = content.concat(post.getLink());
        builder.content(content)
                .date(post.getDate())
                .tags(mapTags(post.getTags()));

        return builder.build();
    }

    private static String mapTags(List<String> tagsList) {
        String tags = "";
        for (int i = 0; i < tagsList.size(); i++) {
            tags = tags.concat(tagsList.get(i));
            if (i != tagsList.size() - 1) {
                tags = tags.concat(", ");
            }
        }
        return tags;
    }
}
