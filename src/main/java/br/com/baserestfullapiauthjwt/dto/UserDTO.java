package br.com.baserestfullapiauthjwt.dto;

import java.util.List;
import java.util.Set;

public class UserDTO {

    private Integer id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String website;
    private List<AddressDTO> address;
    private List<CompanyDTO> company;
    private Set<UserRoleDTO> userRole;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<AddressDTO> getAddress() {
        return address;
    }

    public void setAddress(List<AddressDTO> address) {
        this.address = address;
    }

    public List<CompanyDTO> getCompany() {
        return company;
    }

    public void setCompany(List<CompanyDTO> company) {
        this.company = company;
    }

    public Set<UserRoleDTO> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRoleDTO> userRole) {
        this.userRole = userRole;
    }
}
