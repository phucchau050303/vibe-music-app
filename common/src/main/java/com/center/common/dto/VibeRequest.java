package com.center.common.dto;

import lombok.Data;

@Data
public class VibeRequest {
    private String prompt;

    private int resultCount;

    private String searchType; //"PLAYLISTS" or "SONGS"

    private String platform; //"DEEZER" or "YOUTUBE"
}
