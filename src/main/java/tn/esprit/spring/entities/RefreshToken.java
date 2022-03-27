package tn.esprit.spring.entities;

import java.time.Instant;
import javax.persistence.*;
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
@Entity(name = "refreshtoken")
public class RefreshToken {
	
	
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
  @Column(nullable = false, unique = true)
  private String token;
  @Column(nullable = false)
  private Instant expiryDate;

}
