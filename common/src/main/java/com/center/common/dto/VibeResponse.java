package com.center.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class VibeResponse {
    private String title = "";
    private String playlistLink;
    private String imageUrl;
    private String platform;
    private String description;
}
