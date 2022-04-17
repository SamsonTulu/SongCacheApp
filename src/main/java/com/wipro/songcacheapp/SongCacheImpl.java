package com.wipro.songcacheapp;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SongCacheImpl implements SongCache {

    private final Map<String, Integer> record;

    public SongCacheImpl(){
        record = new ConcurrentHashMap<>();
    }

    @Override
    public void recordSongPlays(String songId, int numPlays) {

        if(record.containsKey(songId)){
            record.put(songId, record.get(songId) + numPlays);
        } else {
            record.put(songId, numPlays);
        }
    }

    @Override
    public int getPlaysForSong(String songId) {
        return record.getOrDefault(songId, -1);
    }

    public List<String> getTopNSongsPlayed(int n){

        return record.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
