package ivha.jpa.project2.DTO;

public class AddressRequestDTO {
    private String address;
    private String city; 
    private String postalCode; 
    private String country;
    private boolean isDefault;
    
    public AddressRequestDTO(String address, String city, String postalCode, String country, boolean isDefault) {
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.isDefault = isDefault;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    
    

}
