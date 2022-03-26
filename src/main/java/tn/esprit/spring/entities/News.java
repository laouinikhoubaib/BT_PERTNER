package tn.esprit.spring.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class News {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int News_id;
	private String NewsTitle;
	private String NewsDescription;
	private LocalDate publishedat =(java.time.LocalDate.now());

    private Integer views = 0;
   

	private float rating ;

	
	@OneToOne(cascade = CascadeType.ALL,mappedBy="news")
	
	 public Integer getViews() {
	        return views == null ? 0 : views;
	    }
	 public News(Integer News_id, String NewsTitle, Integer views) {
			super();
			this.News_id = News_id;
			this.NewsTitle = NewsTitle;
			this.views = views;
		}
	 @ManyToMany(cascade = CascadeType.ALL,mappedBy="nes")
		private Set<FileDB> files;
}
