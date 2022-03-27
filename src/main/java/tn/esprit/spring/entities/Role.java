package tn.esprit.spring.entities;

import javax.persistence.*;






@Entity
@Table(name = "roles")

public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private RoleEn name;
	
	public Role() {
	}
	public Role(RoleEn name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public RoleEn getName() {
		return name;
	}
	public void setName(RoleEn name) {
		this.name = name;
	}
}