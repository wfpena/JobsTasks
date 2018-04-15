package unisys.test.models;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Wilson Pena
 *
 */
@Entity
@Table(name="Tasks")
public class Task {

	@Id
	@Column(name="ID", nullable=false)
	private int Id;
	
	@Column(name="Name", nullable=false)
	private String name;
	
	@Column(name="Weight", nullable=false)
	private Double weight;
	
	@Column(name="Completed")
	private boolean completed;
	
	@Column(name="CreationDate")
	private String createdAt;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="Job_Id")
	@JsonIgnore
	private Job job;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	
	public void setValuesFromJson(){
		
	}
	
}
