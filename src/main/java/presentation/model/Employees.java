package presentation.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employees implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 255, unique = true)

	private int id;

	@Column(name = "name")

	private String name;

	@Column(name = "gender")

	private String gender;

	@Column(name = "dob")

	private String dob;

	@Column(name = "phoneNumber")

	private String phoneNumber;

	@Column(name = "hobbies")

	private List<String> hobbies;

	public Employees() {
	}

	public Employees(int id, String name, String gender, String dob, String phoneNumber, List<String> hobbies) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.hobbies = hobbies;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

}
