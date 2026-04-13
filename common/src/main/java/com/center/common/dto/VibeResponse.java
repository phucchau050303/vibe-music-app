package com.center.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VibeResponse {
    private String title = "";
    private String playlistLink;
    private String imageUrl;
    private String platform;
    private String description;
}
