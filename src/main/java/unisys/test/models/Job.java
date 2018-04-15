package unisys.test.models;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Jobs")
public class Job {

	@Id
	@Column(name="ID")
	@GeneratedValue
	private Long Id;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Active")
	public Boolean active;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="Parent_Id")
	private Job parentJob;
	
	@OneToMany(mappedBy="parentJob", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Job> childJobs;
	
	@OneToMany(mappedBy="job", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("job")
	private List<Task> tasks;

	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Job getParentJob() {
		return parentJob;
	}

	public void setParentJob(Job parentJob) {
		this.parentJob = parentJob;
	}

	public List<Job> getChildJobs() {
		return childJobs;
	}

	public void setChildJobs(List<Job> childJobs) {
		this.childJobs = childJobs;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	
}