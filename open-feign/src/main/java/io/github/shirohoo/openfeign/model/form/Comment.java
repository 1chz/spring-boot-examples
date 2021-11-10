package io.github.shirohoo.openfeign.model.form;

import lombok.Data;

@Data
public class Comment {

    private Long postId;
    private Long id;
    private String name;
    private String email;
    private String body;

}
