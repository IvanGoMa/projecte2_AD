package ivha.jpa.project2.DTO;

public class RoleRequestDTO {
    private String name;
    private String description;

    public RoleRequestDTO(){}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
