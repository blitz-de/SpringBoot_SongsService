package htwb.ai.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
//@JsonIgnoreProperties(value="songList")
@Table(name="songList")
public class SongList  {

	@Id
	@GeneratedValue
	@Column(nullable =false, name="id",unique=true)
	private Integer id;
	@Column(length=50)
	private String name;
	@JsonProperty(value="isPrivate")
	private Boolean isPrivate;
	
	
//	private DAOUser owner;
	
	@JsonBackReference
	@ManyToOne(fetch= FetchType.LAZY, cascade= {CascadeType.ALL}) //was empty
	@JoinColumn(name="owner")
    private DAOUser owner;
	
	  @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})	//WAS EAGER, WAS CASCADEtYPE.MERGE
	   @JoinTable(name = "songlists_songs",
	            joinColumns = 
	        {@JoinColumn(name = "songListId", referencedColumnName = "id")},
	            inverseJoinColumns = 
	        {@JoinColumn(name = "songId", referencedColumnName = "id")})
	private List<Song> songList;


	public SongList() {}
	
	public SongList(String name, Boolean isPrivate){//, DAOUser user, List<Song> songs) {
//			this.id = id;
		this.name = name;
		this.isPrivate = isPrivate;
//		this.owner = user;
//		this.songList = songs;
	}

	@JsonProperty(value = "isPrivate")
	public Boolean getIsPrivate() {
		return isPrivate;
	}
	
    @JsonProperty(value = "isPrivate")	
	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	
	public DAOUser getOwner() {
		return owner;
	}

	public void setOwner(DAOUser owner) {
		this.owner = owner;
	}
//
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SongList [id=" + id + ", name=" + name + "]";
	}
//	@Transient
//	public void addSong(Song song) {
//	    song.setSongList(this);
//	    songs.add(song);
//	}
	
	
    public List<Song> getSongList() {
		return songList;
	}

	public void setSongList(List<Song> songList) {
		this.songList = songList;
	}

}
