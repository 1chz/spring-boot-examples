package io.github.shirohoo.openfeign.model.form;

import lombok.Data;

@Data
public class Photo {

    private Long albumId;
    private Long id;
    private String title;
    private String url;
    private String thumbnailUrl;

}
