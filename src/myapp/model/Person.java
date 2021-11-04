package myapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

@Entity(name = "Person")
@Table(name="TPerson",uniqueConstraints = {
		@UniqueConstraint(columnNames = {
				"first_name", "birth_day"
				})
	})
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Basic(optional = false)
	@Column(name = "first_name", length = 200,
	nullable = false, unique = true)
	private String firstName;
	
	@Basic()
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_day")
	private Date birthDay;
	
	@Version()
	private long version = 0;
	
	@Transient
	public static long updateCounter = 0;
	
	public Person() {
		super();
	}
	public Person(String firstName, Date birthDay) {
		super();
		this.firstName = firstName;
		this.birthDay = birthDay;
	}
	
	@PreUpdate
	public void beforeUpdate() {
		System.err.println("PreUpdate of " + this);
	}
	
	@PostUpdate
	public void afterUpdate() {
		System.err.println("PostUpdate of " + this);
		updateCounter++;
	}
	public final long getId() {
		return id;
	}
	public final void setId(long id) {
		this.id = id;
	}
	public final String getFirstName() {
		return firstName;
	}
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public final Date getBirthDay() {
		return birthDay;
	}
	public final void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public final long getVersion() {
		return version;
	}
	public final void setVersion(long version) {
		this.version = version;
	}
	public static final long getUpdateCounter() {
		return updateCounter;
	}
	public static final void setUpdateCounter(long updateCounter) {
		Person.updateCounter = updateCounter;
	}
	public static final long getSerialversionuid() {
		return serialVersionUID;
	}
	
}