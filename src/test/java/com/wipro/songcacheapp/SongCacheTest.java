package com.wipro.songcacheapp;


import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class SongCacheTest {
    @Test
    public void cacheIsWorking(){

        SongCache cache = new SongCacheImpl();
        cache.recordSongPlays ("ID-1",3);
        cache.recordSongPlays("ID-1", 1);
        cache.recordSongPlays("ID-2",2);
        cache.recordSongPlays("ID-3", 5);

        assertThat(cache.getPlaysForSong("ID-1"), is(4));
        assertThat(cache.getPlaysForSong("ID-9"), is(-1));

        assertThat(cache.getTopNSongsPlayed (2), contains("ID-3","ID-1"));
        assertThat (cache.getTopNSongsPlayed(0), is(empty()));

    }
}
