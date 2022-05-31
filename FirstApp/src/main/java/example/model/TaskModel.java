package example.model;

public class TaskModel {
	private Integer id;
	private String description;
	private String status;
	private String attachment;
	
	public TaskModel() {
		
	}
	public TaskModel( String description, String status, String attachment) {
	
		
		this.description = description;
		this.status = status;
		this.attachment = attachment;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public Integer getId() {
		return id;
	}
	@Override
	public String toString() {
		return "[id=" + id + "\n, description=" + description + ", status=" + status + ", attachment="
				+ attachment + "]";
	}
	

}
