package api.music.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="MusicData")
public class MusicAPIDO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
	
	//Image Attribute
	@Column(name="image_name")
	private String image_name;
	@Column(name="image_req")
	private String image_req;
	@Column(name="image_mimetype")
	private String image_mimetype;
	@Column(name="image_path")
	private String image_path;
	
	//Audio Attribute
	@Column(name="audio_name", unique = true)
	private String audio_name;
	@Column(name="audio_mimetype")
	private String audio_mimetype;
	@Column(name="audio_path")
	private String audio_path;
	
	//General Attribute
	@Column(name="song_title")
	private String song_title;
	@Column(name="song_desc")
	private String song_desc;
	@Column(name="song_artist")
	private String song_artist;
	@Column(name="song_language")
	private String song_language;
	@Column(name="song_rel_on")
	private Date song_rel_on;
	@Column(name="song_movie_name")
	private String song_movie_name;
	@Column(name="song_album_name")
	private String song_album_name;

	//Additional Attribute
	@Column(name="add_field1")
	private String add_field1;
	@Column(name="add_field2")
	private String add_field2;
	@Column(name="add_field3")
	private String add_field3;
	@Column(name="add_field4")
	private String add_field4;
	@Column(name="add_field5")
	private String add_field5;
	@Column(name="add_field6")
	private String add_field6;
	
	@Column(name="created_on")
	private Date created_on;
	@Column(name="updated_on")
	private Date updated_on;
	@Column(name="deleted")
	private String deleted;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public String getImage_req() {
		return image_req;
	}
	public void setImage_req(String image_req) {
		this.image_req = image_req;
	}
	public String getImage_mimetype() {
		return image_mimetype;
	}
	public void setImage_mimetype(String image_mimetype) {
		this.image_mimetype = image_mimetype;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getAudio_name() {
		return audio_name;
	}
	public void setAudio_name(String audio_name) {
		this.audio_name = audio_name;
	}
	public String getAudio_mimetype() {
		return audio_mimetype;
	}
	public void setAudio_mimetype(String audio_mimetype) {
		this.audio_mimetype = audio_mimetype;
	}
	public String getAudio_path() {
		return audio_path;
	}
	public void setAudio_path(String audio_path) {
		this.audio_path = audio_path;
	}
	public String getSong_title() {
		return song_title;
	}
	public void setSong_title(String song_title) {
		this.song_title = song_title;
	}
	public String getSong_desc() {
		return song_desc;
	}
	public void setSong_desc(String song_desc) {
		this.song_desc = song_desc;
	}
	public String getSong_artist() {
		return song_artist;
	}
	public void setSong_artist(String song_artist) {
		this.song_artist = song_artist;
	}
	public String getSong_language() {
		return song_language;
	}
	public void setSong_language(String song_language) {
		this.song_language = song_language;
	}
	public Date getSong_rel_on() {
		return song_rel_on;
	}
	public void setSong_rel_on(Date song_rel_on) {
		this.song_rel_on = song_rel_on;
	}
	public String getSong_movie_name() {
		return song_movie_name;
	}
	public void setSong_movie_name(String song_movie_name) {
		this.song_movie_name = song_movie_name;
	}
	public String getSong_album_name() {
		return song_album_name;
	}
	public void setSong_album_name(String song_album_name) {
		this.song_album_name = song_album_name;
	}
	public String getAdd_field1() {
		return add_field1;
	}
	public void setAdd_field1(String add_field1) {
		this.add_field1 = add_field1;
	}
	public String getAdd_field2() {
		return add_field2;
	}
	public void setAdd_field2(String add_field2) {
		this.add_field2 = add_field2;
	}
	public String getAdd_field3() {
		return add_field3;
	}
	public void setAdd_field3(String add_field3) {
		this.add_field3 = add_field3;
	}
	public String getAdd_field4() {
		return add_field4;
	}
	public void setAdd_field4(String add_field4) {
		this.add_field4 = add_field4;
	}
	public String getAdd_field5() {
		return add_field5;
	}
	public void setAdd_field5(String add_field5) {
		this.add_field5 = add_field5;
	}
	public String getAdd_field6() {
		return add_field6;
	}
	public void setAdd_field6(String add_field6) {
		this.add_field6 = add_field6;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public Date getUpdated_on() {
		return updated_on;
	}
	public void setUpdated_on(Date update_on) {
		this.updated_on = update_on;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public MusicAPIDO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MusicAPIDO(String id, String image_name, String image_req, String image_mimetype, String image_path,
			String audio_name, String audio_mimetype, String audio_path, String song_title, String song_desc,
			String song_artist, String song_language, Date song_rel_on, String song_movie_name, String song_album_name,
			String add_field1, String add_field2, String add_field3, String add_field4, String add_field5,
			String add_field6, Date created_on, Date updated_on, String deleted) {
		super();
		this.id = id;
		this.image_name = image_name;
		this.image_req = image_req;
		this.image_mimetype = image_mimetype;
		this.image_path = image_path;
		this.audio_name = audio_name;
		this.audio_mimetype = audio_mimetype;
		this.audio_path = audio_path;
		this.song_title = song_title;
		this.song_desc = song_desc;
		this.song_artist = song_artist;
		this.song_language = song_language;
		this.song_rel_on = song_rel_on;
		this.song_movie_name = song_movie_name;
		this.song_album_name = song_album_name;
		this.add_field1 = add_field1;
		this.add_field2 = add_field2;
		this.add_field3 = add_field3;
		this.add_field4 = add_field4;
		this.add_field5 = add_field5;
		this.add_field6 = add_field6;
		this.created_on = created_on;
		this.updated_on = updated_on;
		this.deleted = deleted;
	}
	
	
	
}
