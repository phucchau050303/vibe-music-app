package com.center.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeezerPlaylistSearchResponse {
    private List<DeezerPlaylistItem> data;
}
