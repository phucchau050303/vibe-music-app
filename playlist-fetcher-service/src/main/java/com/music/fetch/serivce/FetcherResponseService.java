package com.music.fetch.serivce;

import com.center.common.dto.DeezerPlaylistSearchResponse;
import com.center.common.dto.MusicQuery;
import com.center.common.dto.VibeResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class FetcherResponseService {
    private final RestClient deezerRestClient;

    public FetcherResponseService(RestClient deezerRestClient) {
        this.deezerRestClient = deezerRestClient;
    }

    public List<VibeResponse> getPlaylists(MusicQuery query){
            String genres = query.getGenres() == null ? "" : String.join(" ", query.getGenres());

            String combinedSearchText = String.join(" ",
                    query.getSearchQuery(),
                    query.getMood(),
                    genres
            ).trim();
            DeezerPlaylistSearchResponse response = deezerRestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/search/playlist")
                            .queryParam("q", combinedSearchText)
                            .build())
                    .retrieve()
                    .body(DeezerPlaylistSearchResponse.class);

            if (response == null || response.getData() == null){
                return List.of();
            }

            return response.getData().stream()
                    .map(item -> VibeResponse.builder()
                            .title(item.getTitle())
                            .playlistLink(item.getLink())
                            .imageUrl(item.getPictureMedium())
                            .platform("DEEZER")
                            .description(item.getDescription())
                            .build())
                    .toList();
    }
}
