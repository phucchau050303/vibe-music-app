package com.center.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class MusicQuery
{
    private String searchQuery;

    private String tempo;

    private String mood;

    @JsonProperty("genres")
    private List<String> genres;
}
