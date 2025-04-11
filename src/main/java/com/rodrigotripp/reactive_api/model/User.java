package com.rodrigotripp.reactive_api.model;

public class User {
    private Long id;
    private String name;
    private String email;
    private String username;
    private String phone;
    private String website;
    
    // Constructores
    public User() {}
    
    public User(Long id, String name, String email, String username, String phone, String website) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.phone = phone;
        this.website = website;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return name; }
    public void setNombre(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPhoneNumber() { return phone; }
    public void setPhoneNumber(String phone) { this.phone = phone; }
    
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
}