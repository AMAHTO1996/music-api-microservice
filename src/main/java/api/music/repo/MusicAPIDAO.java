package api.music.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.music.entity.MusicAPIDO;

@Repository
public interface  MusicAPIDAO extends JpaRepository<MusicAPIDO, String>{

}
