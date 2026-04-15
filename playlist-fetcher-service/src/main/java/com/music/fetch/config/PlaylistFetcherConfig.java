package com.music.fetch.config;

import com.center.common.dto.MusicQuery;
import com.center.common.dto.VibeResponse;
import com.music.fetch.serivce.FetcherResponseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;


import java.util.List;
import java.util.function.Function;

@Configuration
public class PlaylistFetcherConfig {
    @Bean
    public RestClient deezerRestClient(RestClient.Builder builder){
        return builder.baseUrl("https://api.deezer.com").build();
    }

    @Bean
    Function<MusicQuery, List<VibeResponse>> fetchPlaylists(FetcherResponseService fetcherResponseService) {
        return fetcherResponseService::getPlaylists;
    }


}
