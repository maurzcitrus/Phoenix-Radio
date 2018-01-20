/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ft08.phoenix.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phyo
 */
public class User {
        
    private String userId;
    private String password;
    private String fullName;
    private List<Role> roles;
    private boolean authenticated;
    private int programSlots;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public List<Role> getRoles(){
        if(roles == null) {
            roles = new ArrayList<>();
        }
        return roles;
    }
    
    public void setRoles(List<Role> roles){
        this.roles = roles;
    }
    
    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean isAuthenticated) {
        this.authenticated = isAuthenticated;
    }
    
    public int getprogramSlots(){
        return programSlots;
    }
    
    public void setprogramSlots(int programslots){
        this.programSlots = programslots;
    }
}
