package in.ashokit.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="COMMENT_TBL")
@Setter
@Getter
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer commentId;
	
	private String name;
	
	private String email;
	
	private String comment;
	
	@CreationTimestamp
	private LocalDate createdDate;
	
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post post;
}
