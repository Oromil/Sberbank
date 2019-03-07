package com.example.sberbank.main;

import android.text.util.Linkify;
import android.util.Patterns;

import com.example.sberbank.models.Post;
import com.example.sberbank.models.PostEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostMapper {
    public static String map(Post post){
        String string = "<img src=\"(.*?)\".*?>";
        Pattern pattern = Pattern.compile(string);
        Matcher matcher =pattern.matcher(post.getDescription());
        if (matcher.find()){
            return matcher.group(1);
        }
        else return "";
    }
}
