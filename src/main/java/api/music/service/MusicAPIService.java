package api.music.service;

import java.util.List;



import api.music.entity.MusicAPIDO;

public interface MusicAPIService {
	

	public List<MusicAPIDO>getAllMusic();
	public MusicAPIDO getMusic(String id);
	public MusicAPIDO saveMusic(MusicAPIDO MusicAPIdO);
	public MusicAPIDO updateMusic(MusicAPIDO MusicAPIdO,String id);
}
