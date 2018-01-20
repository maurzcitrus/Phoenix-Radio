/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ft08.phoenix.user;

import java.util.List;
import sg.edu.nus.iss.ft08.phoenix.model.User;

/**
 *  Defines methods to maintain users
 * 
 * @author JAYENDRA
 */
public interface UserDao {

    /**
     * Finds all users along with their roles in the database
     *
     * @return list of all the users
     */
    public List<User> findAll();

    /**
     * Finds a user that matches any of the attribute values
     * in given record.
     * Currently, it supports user ID.
     *
     * @param record should be User object
     * @return user that match ID or null
     */
    public User findOne(User record);    

    /**
     * creates new user in database
     *
     * @param newRecord should be a User object
     * @return newly created user 
     */
    public User createUser(User newRecord);

    /**
     * updates new user in database by referring the user ID
     *
     * @param record should be User Object 
     * @return newly updated user
     */
    public User updateUser(User record);

    /**
     * Deletes user based on ID
     *
     * @param record should be User object and must have ID value in it
     */
    public void deleteUser(User record);

    /**
     * Check whether user is assigned to any program slot or not.
     *
     * @param record should be user object and must have ID value in it
     * @return User Object if user assigned or null if he does not. 
     */
    public User findAssignedUser(User record);
    
    //public User findAssignedProgramSlots(User record);
}
