package example.model;

public class TeamModel {
	private Integer id;
	private String email;
	private String name;
	public TeamModel() {
		
	}
	public TeamModel(Integer id, String email, String name) {
		
		this.id = id;
		this.email = email;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "TeamModel [id=" + id + ", email=" + email + ", name=" + name + "]";
	}
	
	

}
