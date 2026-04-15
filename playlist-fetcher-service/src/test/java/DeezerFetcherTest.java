import com.center.common.dto.DeezerPlaylistItem;
import com.center.common.dto.DeezerPlaylistSearchResponse;
import com.center.common.dto.MusicQuery;
import com.center.common.dto.VibeResponse;
import com.music.fetch.serivce.FetcherResponseService;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;


import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings({"unchecked", "rawtypes"})
public class DeezerFetcherTest {
    @Test
    void get_Empty_Playlist(){
        //Arrange
        RestClient restClient = mock(RestClient.class);
        RestClient.RequestHeadersUriSpec uriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec<?> headersSpec = mock(RestClient.RequestHeadersSpec.class);
        RestClient.ResponseSpec responseSpec = mock(RestClient.ResponseSpec.class);

        FetcherResponseService service = new FetcherResponseService(restClient);


        MusicQuery query = new MusicQuery();
        query.setSearchQuery("study chill");
        query.setMood("calm");
        query.setGenres(List.of("lofi"));

        when(restClient.get()).thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class))).thenReturn(headersSpec);
        when(headersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(DeezerPlaylistSearchResponse.class)).thenReturn(null);

        //Act
        List<VibeResponse> result = service.getPlaylists(query);

        //Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void resultMappingCorrect(){
        //Arrange
        RestClient restClient = mock(RestClient.class);
        RestClient.RequestHeadersUriSpec uriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec<?> headersSpec = mock(RestClient.RequestHeadersSpec.class);
        RestClient.ResponseSpec responseSpec = mock(RestClient.ResponseSpec.class);

        FetcherResponseService service = new FetcherResponseService(restClient);


        MusicQuery query = new MusicQuery();
        query.setSearchQuery("study chill");
        query.setMood("calm");
        query.setGenres(List.of("lofi"));

        DeezerPlaylistItem item = new DeezerPlaylistItem();
        item.setTitle("Lofi Beats");
        item.setLink("https://www.deezer.com/playlist/123");
        item.setPictureMedium("http://img.deezer.com/playlistimg123.jpg");
        item.setDescription("Playlist for relaxing");

        DeezerPlaylistSearchResponse data = new DeezerPlaylistSearchResponse();
        data.setData(List.of(item));

        when(restClient.get()).thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class))).thenReturn(headersSpec);
        when(headersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(DeezerPlaylistSearchResponse.class)).thenReturn(data);

        //Act
        List<VibeResponse> responses = service.getPlaylists(query);

        //Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());

        VibeResponse playlist = responses.get(0);
        assertEquals("Lofi Beats", playlist.getTitle());
        assertEquals("https://www.deezer.com/playlist/123", playlist.getPlaylistLink());
        assertEquals("http://img.deezer.com/playlistimg123.jpg", playlist.getImageUrl());
        assertEquals("Playlist for relaxing", playlist.getDescription());
    }

    @Test
    void emptyGenre_Test(){
        //Arrange
        RestClient restClient = mock(RestClient.class);
        RestClient.RequestHeadersUriSpec uriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec<?> headersSpec = mock(RestClient.RequestHeadersSpec.class);
        RestClient.ResponseSpec responseSpec = mock(RestClient.ResponseSpec.class);

        FetcherResponseService service = new FetcherResponseService(restClient);


        MusicQuery query = new MusicQuery();
        query.setSearchQuery("study chill");
        query.setMood("calm");
        query.setGenres(null);

        DeezerPlaylistItem item = new DeezerPlaylistItem();
        item.setTitle("Lofi Beats");
        item.setLink("https://www.deezer.com/playlist/123");
        item.setPictureMedium("http://img.deezer.com/playlistimg123.jpg");
        item.setDescription("Playlist for relaxing");

        DeezerPlaylistSearchResponse data = new DeezerPlaylistSearchResponse();
        data.setData(List.of(item));

        when(restClient.get()).thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class))).thenReturn(headersSpec);
        when(headersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(DeezerPlaylistSearchResponse.class)).thenReturn(data);

        //Act
        List<VibeResponse> responses = service.getPlaylists(query);

        //Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());

        VibeResponse playlist = responses.get(0);
        assertEquals("Lofi Beats", playlist.getTitle());
        assertEquals("https://www.deezer.com/playlist/123", playlist.getPlaylistLink());
        assertEquals("http://img.deezer.com/playlistimg123.jpg", playlist.getImageUrl());
        assertEquals("Playlist for relaxing", playlist.getDescription());
    }
}
