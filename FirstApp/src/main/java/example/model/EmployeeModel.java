package example.model;

public class EmployeeModel {
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String userName;
	private RoleModel role;

	public EmployeeModel() {
		
	};
	public EmployeeModel(String firstName, String lastName, String email, String password, String userName,
			RoleModel role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.role = role;
		
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public RoleModel getRole() {
		return role;
	}
	public void setRole(RoleModel role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "EmployeeModel [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", userName=" + userName + ", role=" + role + "]";
	}
	
	

}
