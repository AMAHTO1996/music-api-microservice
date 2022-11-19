package api.music.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import api.music.entity.AppProperty;
import api.music.entity.MusicAPIDO;
import api.music.repo.MusicAPIDAO;

@Service
public class MusicAPIServiceImp implements MusicAPIService{
	
	public static final int ID_LENGTH = 15;
	
	@Autowired
	private MusicAPIDAO musicAPIDAO;

	@Autowired
	private AppProperty appProperty;
	
	@Override
	public List<MusicAPIDO> getAllMusic() {
		
		List<MusicAPIDO> listMusicAPIDO= musicAPIDAO.findAll();
		List<MusicAPIDO> updatedListMusicAPIDO = new ArrayList<>();
		for( MusicAPIDO musicAPIDO : listMusicAPIDO) {
			String image_url = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/download/Image/").path(musicAPIDO.getId()).toUriString();
			String audio_url = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/download/audio/").path(musicAPIDO.getId()).toUriString();
			musicAPIDO.setImage_path(image_url);
			musicAPIDO.setAudio_path(audio_url);
			updatedListMusicAPIDO.add(musicAPIDO);
		}
		
		return updatedListMusicAPIDO;
	}

	@Override
	public MusicAPIDO getMusic(String id) {
		MusicAPIDO musicAPIDO = null;
		if(!musicAPIDAO.findById(id).isEmpty()) {
			 musicAPIDO = musicAPIDAO.findById(id).get();
		}else {
			return null;
		}
		String image_url = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/download/Image/").path(musicAPIDO.getId()).toUriString();
		String audio_url = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/download/audio/").path(musicAPIDO.getId()).toUriString();
		musicAPIDO.setImage_path(image_url);
		musicAPIDO.setAudio_path(audio_url);
		
		return musicAPIDO;
	}

	@Override
	public MusicAPIDO saveMusic(MusicAPIDO MusicAPIdO) {
		
		String fileN =RandomStringUtils.randomAlphanumeric(ID_LENGTH);
		String imageName= fileN+".jpg";
		String audioName= fileN+".mp3";
		String imageUrl= appProperty.getUrlImage()+imageName;
		String audioUrl= appProperty.getUrlAudio()+audioName;
		boolean imageFlag = checkImageRequired(MusicAPIdO);
		boolean audioFlag = checkAudioRequired(MusicAPIdO);
		if(audioFlag) {
			int resAudio = deployOnArtifactory(audioUrl,MusicAPIdO.getAudio_path());
			if(resAudio != 201) {
				return null;
			}
			MusicAPIdO.setAudio_name(audioName);
			if(imageFlag) {
				int resImage = deployOnArtifactory(imageUrl,MusicAPIdO.getImage_path());
				MusicAPIdO.setImage_name(imageName);
				if(resImage != 201) {
					MusicAPIdO.setImage_req("NO");
					MusicAPIdO.setImage_name(null);
				}
			}else {
				MusicAPIdO.setImage_req("NO");
				MusicAPIdO.setImage_name(null);
			}
		}else {
			System.out.println("audioFlag :"+audioFlag +" imageFlag : "+imageFlag);
			return null;
		}
		MusicAPIdO.setImage_mimetype("jpg");
		MusicAPIdO.setAudio_mimetype("mp3");
		MusicAPIdO.setCreated_on(new Date());
		MusicAPIdO.setUpdated_on(new Date());
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
//		try {
//			Date date = formatter.parse(MusicAPIdO.getSong_rel_on().toString());
//			MusicAPIdO.setSong_rel_on(date);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		if(musicAPIDAO.save(MusicAPIdO) != null) {
			return MusicAPIdO;
		}
		return null;
	}

	@Override
	public MusicAPIDO updateMusic(MusicAPIDO MusicAPIdO, String id) {
		MusicAPIDO musicAPIDO = null;
		if(!musicAPIDAO.findById(id).isEmpty()) {
			 musicAPIDO = musicAPIDAO.findById(id).get();
			 if(null!=MusicAPIdO && !MusicAPIdO.getAdd_field1().isEmpty())
				 musicAPIDO.setAdd_field1(MusicAPIdO.getAdd_field1());
			 if(null!=MusicAPIdO && !MusicAPIdO.getAdd_field1().isEmpty())
				 musicAPIDO.setAdd_field1(MusicAPIdO.getAdd_field1());
			 if(null!=MusicAPIdO && !MusicAPIdO.getAdd_field2().isEmpty())
				 musicAPIDO.setAdd_field2(MusicAPIdO.getAdd_field2());
			 if(null!=MusicAPIdO && !MusicAPIdO.getAdd_field3().isEmpty())
				 musicAPIDO.setAdd_field3(MusicAPIdO.getAdd_field3());
			 if(null!=MusicAPIdO && !MusicAPIdO.getAdd_field4().isEmpty())
				 musicAPIDO.setAdd_field4(MusicAPIdO.getAdd_field4());
			 if(null!=MusicAPIdO && !MusicAPIdO.getAdd_field5().isEmpty())
				 musicAPIDO.setAdd_field5(MusicAPIdO.getAdd_field5());
			 if(null!=MusicAPIdO && !MusicAPIdO.getAdd_field6().isEmpty())
				 musicAPIDO.setAdd_field6(MusicAPIdO.getAdd_field6());
			 if(null!=MusicAPIdO && !MusicAPIdO.getSong_title().isEmpty())
				 musicAPIDO.setSong_title(MusicAPIdO.getSong_title());
			 if(null!=MusicAPIdO && !MusicAPIdO.getSong_desc().isEmpty())
				 musicAPIDO.setSong_desc(MusicAPIdO.getSong_desc());
			 if(null!=MusicAPIdO && !MusicAPIdO.getSong_language().isEmpty())
				 musicAPIDO.setSong_language(MusicAPIdO.getSong_language());
			 if(null!=MusicAPIdO && !MusicAPIdO.getSong_artist().isEmpty())
				 musicAPIDO.setSong_artist(MusicAPIdO.getSong_artist());
			 if(null!=MusicAPIdO && !MusicAPIdO.getSong_movie_name().isEmpty())
				 musicAPIDO.setSong_movie_name(MusicAPIdO.getSong_movie_name());
			 if(null!=MusicAPIdO && !MusicAPIdO.getSong_album_name().isEmpty())
				 musicAPIDO.setSong_album_name(MusicAPIdO.getSong_album_name());
				 musicAPIDO.setUpdated_on(new Date());
			 if(musicAPIDAO.save(musicAPIDO) != null) {
					return musicAPIDO;
				}
		}else {
			return null;
		}
		return null;
	}
	
	
	//To Deploy Artifact on Artifactory
	public int deployOnArtifactory(String fullUrl, String filepath) {
		
		//System.out.println("UserName ::"+appProperty.getUsername());
		//System.out.println("Password ::"+appProperty.getPassword());
		//System.out.println("fullUrl ::"+fullUrl);
		//System.out.println("filepath ::"+filepath);
		String username = appProperty.getUsername();
		String password = appProperty.getPassword();

		//String filePath = "C:/Users/amaht/Downloads/Pmay.jpeg";
		String[] command = { "curl", "-u", username + ":" + password, "-o", "/dev/null", "-s", "-w", "%{http_code}\\n" ,"-X", "PUT", fullUrl, "-T", filepath};
		//String[] command = { "curl", "-u", username + ":" + password, "-X", "PUT", fullUrl, "-T", filepath};
		//System.out.println("command=="+command);
		ProcessBuilder process = new ProcessBuilder(command);
		Process p;
		String result = null;
		try {
			p = process.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append(System.getProperty("line.separator"));
			}
			result = builder.toString();
			System.out.print(result);

		} catch (Exception e) {
			System.out.print("error");
			e.printStackTrace();
		}
		if(result.trim().equals("201")) {
			return 201;
		}
		return 0;
		
	}

	public boolean checkImageRequired(MusicAPIDO MusicAPIdO) {
		//System.out.println("MusicAPIdO.getImage_path() :: "+MusicAPIdO.getImage_path());
		if(null!=MusicAPIdO && null!=MusicAPIdO.getImage_req() && MusicAPIdO.getImage_req().equalsIgnoreCase("YES")) {
			if(MusicAPIdO.getImage_path()!=null && !MusicAPIdO.getImage_path().trim().equalsIgnoreCase("")) {
				return true;
			}
		}
		return false;
		
	}
	
	public boolean checkAudioRequired(MusicAPIDO MusicAPIdO) {
		//System.out.println("MusicAPIdO.getAudio_path() :: "+MusicAPIdO.getAudio_path());
		if(null!=MusicAPIdO && null!=MusicAPIdO.getAudio_path() && !MusicAPIdO.getAudio_path().trim().equalsIgnoreCase("")) {
			return true;
		}
		return false;
	}
}
