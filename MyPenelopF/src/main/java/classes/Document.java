package classes;

public class Document {
	
	private Integer id;
	
	private String name;
	
	private String path;
	
	private Project project;
	
	static private int autoincrement = 0;
	static private int increment() {
		return ++Document.autoincrement;
	}
	
	public Document() {
		this.id = Document.increment();
	}
	
	public Document(String name, String path, Project project) {
		this.id = Document.increment();
		this.name = name;
		this.path = path;
		this.project = project;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return this.path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Project getProject() {
		return this.project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
}
