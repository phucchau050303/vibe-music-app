package com.center.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeezerPlaylistItem {
    private String title;
    private String link;
    private String description;
    @JsonProperty("picture_medium")
    private String pictureMedium;
}
