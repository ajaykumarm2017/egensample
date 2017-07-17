package egen.sampleapp.domain;

//Generated Jul 15, 2017 4:29:55 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

/**
* User generated by hbm2java
*/
@Entity
@Table(name = "USER", schema = "PUBLIC")
public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5967494790373537880L;
	
	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private Short age;
	private Gender gender;
	private Long phone;
	private Integer zip;

	public User() {
	}
	
	public User(Long id) {
		this.id = id;
	}

	public User(Long id, String firstName, String middleName, String lastName, Short age, Gender gender, Long phone,
			Integer zip) {
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.zip = zip;
	}

	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FIRST_NAME", length = 50, nullable=false)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "MIDDLE_NAME", length = 50, nullable=true)
	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "LAST_NAME", length = 50, nullable=false)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "AGE", nullable=false)
	@Min(value=1)
	public Short getAge() {
		return this.age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	@Column(name = "GENDER", length = 1)
	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Column(name = "PHONE", length=10)
	@Min(value=1)
	public Long getPhone() {
		return this.phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	@Column(name = "ZIP", nullable=true)
	public Integer getZip() {
		return this.zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}
}
