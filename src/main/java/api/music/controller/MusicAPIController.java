package api.music.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.List;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import api.music.entity.AppProperty;
import api.music.entity.MusicAPIDO;
import api.music.service.MusicAPIServiceImp;


@CrossOrigin(origins="*")
@RestController
public class MusicAPIController {
	
	@Autowired
	private AppProperty appProperty;
	
	@Autowired
	private MusicAPIServiceImp musicAPIService;
	
	@GetMapping("/")
	public ResponseEntity<?> getMessage(){
//		int res =musicAPIService.deployOnArtifactory("https://amahto.jfrog.io/artifactory/Image/9MOmH6v6ShVXLbl.mp3","C:/Users/amaht/Downloads/ManikeMageHithe.mp3");
//		System.out.println("res :: "+res);
		String msg ="Spring Application Created by Amitkumar Mahto for Music API";
		return new ResponseEntity<>(msg, HttpStatus.OK);
		
	}
	
	@GetMapping("/music")
	public ResponseEntity<?> getAllMusic(){
		//System.out.println("musicAPIService :::"+musicAPIService);
		List<MusicAPIDO> listMusic = musicAPIService.getAllMusic();
		return new ResponseEntity<>(listMusic, HttpStatus.OK);
		
	}
	
	@GetMapping("/music/{id}")
	public ResponseEntity<?> getMusic(@PathVariable("id") String id){
		MusicAPIDO music = musicAPIService.getMusic(id);
		return new ResponseEntity<>(music, HttpStatus.OK);
		
	}
	
	@PostMapping("/music")
	public ResponseEntity<?> saveMusic(@RequestBody MusicAPIDO MusicAPIdO){
		//System.out.println("musicAPIService :::"+musicAPIService);
		MusicAPIDO MusicAPIdO1= musicAPIService.saveMusic(MusicAPIdO);
		if(MusicAPIdO1 !=null) {
			return new ResponseEntity<>(MusicAPIdO1, HttpStatus.OK);
		}
		return new ResponseEntity<>("Erreor Occur while uploading Song kindly contact Administrator", HttpStatus.BAD_GATEWAY);
		
	}
	
	@PutMapping("/music/{id}")
	public ResponseEntity<?> updateMusic(@RequestBody MusicAPIDO MusicAPIdO, @PathVariable("id") String id){
		MusicAPIDO musicAPIdO=musicAPIService.updateMusic(MusicAPIdO, id);
		if(musicAPIdO!=null){
			return new ResponseEntity<>(musicAPIdO, HttpStatus.OK);
		}
		return new ResponseEntity<>("Erreor Occur while updating Song kindly contact Administrator", HttpStatus.BAD_GATEWAY);
		
	}
	
	
	@GetMapping("/download/Image/{fileId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String fileId) throws Exception {
		MusicAPIDO musicAPIDO =  musicAPIService.getMusic(fileId);
		String filerURL;
		File file;
		byte[] content = null;
		if((musicAPIDO!=null && musicAPIDO.getImage_req()!=null && musicAPIDO.getImage_req().equalsIgnoreCase("YES"))) {
			if(musicAPIDO.getImage_name()!=null && !musicAPIDO.getImage_name().toString().equalsIgnoreCase("")) {
				filerURL = appProperty.getUrlImage()+musicAPIDO.getImage_name();
			}else {
				filerURL = appProperty.getUrlImage()+appProperty.getDefaultImage();
			} 
		}else {
			 filerURL = appProperty.getUrlImage()+appProperty.getDefaultImage();
		}
		System.out.println("filerURL ::"+filerURL);
//		String username = appProperty.getUsername();
//		String password = appProperty.getPassword();
    	RestTemplate restTemplate = new RestTemplate();
    	try {
	    	file = restTemplate.execute(filerURL, HttpMethod.GET, null, clientHttpResponse -> {
	    	    File ret = File.createTempFile("downloadI", ".tmp");
	    	    StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
	    	    return ret;
	    	});
  
	    	//System.out.println("file ::"+file);
	    	content = Files.readAllBytes(file.toPath());
    	} catch (final Exception e) {
    		return ResponseEntity.internalServerError().body(null);
    	}
    	if(content==null) {
    		return ResponseEntity.internalServerError().body(null);
    	}
    	String fileName = file.getName();
    	file.deleteOnExit();
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName
                + "\"")
                .body(new ByteArrayResource(content.clone()));
    }
	    
    @GetMapping("/download/audio/{fileId}")
    public ResponseEntity<Resource> downloadAudio(@PathVariable String fileId) throws Exception {
    	MusicAPIDO musicAPIDO =  musicAPIService.getMusic(fileId);
    	String fileURL = null;
    	if(musicAPIDO != null && musicAPIDO.getAudio_name()!=null) {
    		fileURL = appProperty.getUrlAudio()+musicAPIDO.getAudio_name();
    	}else {
    		return ResponseEntity.internalServerError().body(null);
    	}
    	
    	byte[] content = null;
    	File file;
    	//System.out.println("fileURL ::"+fileURL);
    	RestTemplate restTemplate = new RestTemplate();
    	try {
	    	    file = restTemplate.execute(fileURL, HttpMethod.GET, null, clientHttpResponse -> {
	    	    File ret = File.createTempFile("downloadV", ".tmp");
	    	    StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
	    	    return ret;
		    	});
	    		content = Files.readAllBytes(file.toPath());
    	} catch (Exception e) {
    		return ResponseEntity.internalServerError().body(null);
    	}
    	if(content==null) {
    		return ResponseEntity.internalServerError().body(null);
    	}
    	String fileName = file.getName();
    	//System.out.println("file.toPath() :2::"+file.toPath());
    	file.deleteOnExit();
    	
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName
                + "\"")
                .body(new ByteArrayResource(content.clone()));
    }

}
