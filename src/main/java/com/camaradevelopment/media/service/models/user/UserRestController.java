package com.camaradevelopment.media.service.models.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public User getUserById(@PathVariable int userId){
        return userRepository.findUserById(userId);
    }

    @RequestMapping(path = "/find_by_fist_name/{fisrtName}", method = RequestMethod.GET)
    public List<User> getUsersByFistNameContains(@PathVariable String firstName){
        return userRepository.findUsersByFirstNameContainingIgnoreCase(firstName);
    }
    @RequestMapping(path = "/find_by_last_name/{lastName}", method = RequestMethod.GET)
    public List<User> getUsersByLastNameContains(@PathVariable String lastName){
        return userRepository.findUsersByLastNameContainingIgnoreCase(lastName);
    }
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PATCH)
    public void updateUser(@RequestBody User user){
        User tempUser = userRepository.findUserById(user.getId());
        tempUser.setFirstName(user.getFirstName());
        tempUser.setLastName(user.getLastName());
        tempUser.setEmail(user.getEmail());
        userRepository.save(tempUser);
    }

}
