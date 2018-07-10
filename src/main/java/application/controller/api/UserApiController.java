package application.controller.api;

import application.data.model.User;
import application.data.model.UserRole;
import application.data.service.UserService;
import application.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/all-users") //lay ra danh sach user role
    public BaseApiResult getUserRole() {
        DataApiResult result = new DataApiResult();
        try {
            ArrayList<UserRole> userRoles = new ArrayList<>();
            ArrayList<UserRoleDataModel> userRoleDataModels = new ArrayList<>();
            userRoles = userService.getUserRole(); //lay ra role cua user
            for(UserRole u : userRoles) {
                userRoleDataModels.add(new UserRoleDataModel(u.getId(),
                        userService.findUserById(u.getUserId()),userService.findRoleById(u.getRoleId())));

            }
            result.setData(userRoleDataModels);
            result.setMessage("Success!!!");
            result.setSuccess(true);
        }catch (Exception e) {
            result.setData(null);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/update-role/")
    public BaseApiResult updateRole(@RequestBody UserDataModel userDataModel){
        DataApiResult result = new DataApiResult();
        try {
            if(userService.updateRole(userDataModel.getUserId())){
                result.setMessage("success");
                result.setSuccess(true);
                result.setData(null);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            result.setData(null);
        }

        return result;
    }

    @GetMapping("/image/{username}")
    public BaseApiResult getImage(@PathVariable String username) {
        DataApiResult result = new DataApiResult();
        try {
            result.setSuccess(true);
            result.setData(userService.getImage(username));
            result.setMessage("success");
        }catch (Exception e ) {
            result.setData(null);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @GetMapping("/{userId}")
    public BaseApiResult getProfile(@PathVariable int userId){
        DataApiResult result = new DataApiResult();
        ModelMapper modelMapper = new ModelMapper();
        try {
            UserDetailModel u = new UserDetailModel();
            u = modelMapper.map(userService.findUserById(userId),UserDetailModel.class);

//            result.setMessage(userService.findRoleName(u.getId()));

            result.setData(u);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setData(null);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/update-user/{id}")
    public BaseApiResult updateUser(@PathVariable int id, @RequestBody UserDetailModel user) {
        DataApiResult result = new DataApiResult();
        try {
            if (!"".equals(user.getUsername()) && !"".equals(user.getEmail()) && !"".equals(user.getRealname())) {
                User existedUser = userService.findOne(id);
                if(existedUser == null) {
                    result.setSuccess(false);
                    result.setMessage("This user is not existed");
//                }else if(passwordEncoder.matches(user.getOldPassword(),existedUser.getPassword())) {
//                    existedUser.setUsername(user.getUsername());
//                    existedUser.setEmail(user.getEmail());
//                    existedUser.setRealname(user.getRealname());
//                    existedUser.setAddress(user.getAddress());
//                    existedUser.setGender(user.getGender());
//                    existedUser.setImage(user.getImage());
//                    existedUser.setUpdatedDate(user.getUpdatedDate());
//                    existedUser.setPassword(passwordEncoder.encode(user.getPassword()));
//                    userService.updateUser(existedUser);
//                    result.setSuccess(true);
//                    result.setMessage("Success!!!!!");
//                    result.setData(existedUser);
                }else {
                    existedUser.setUsername(user.getUsername());
                    existedUser.setEmail(user.getEmail());
                    existedUser.setRealname(user.getRealname());
                    existedUser.setAddress(user.getAddress());
                    existedUser.setGender(user.getGender());
                    existedUser.setImage(user.getImage());
                    existedUser.setUpdatedDate(user.getUpdatedDate());
                    existedUser.setPhone(user.getPhone());
                    userService.updateUser(existedUser);
                    result.setSuccess(true);
                    result.setMessage("Success!!!!!");
                    result.setData(existedUser);
                }
            }else {
                result.setSuccess(false);
                result.setMessage("Password is wrong.Please check again");
            }
        }catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
