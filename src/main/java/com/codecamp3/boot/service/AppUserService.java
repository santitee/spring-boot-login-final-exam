package com.codecamp3.boot.service;

import com.codecamp3.boot.util.PasswordUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codecamp3.boot.entity.AppRole;
import com.codecamp3.boot.entity.AppUser;
import com.codecamp3.boot.entity.UserRole;
import com.codecamp3.boot.repository.AppRoleRepository;
import com.codecamp3.boot.repository.AppUserRepository;
import com.codecamp3.boot.repository.UserRoleRepository;


@Service
public class AppUserService {
   // TODO: ประกาศตัวแปร AppUserRepository
   @Autowired
   private AppUserRepository appUserRepository;
   @Autowired
   private UserRoleRepository userRoleRepository;
   @Autowired
   private AppRoleRepository appRoleRepository;
   public String registerAndValidation(String username, String password, String confirmPassword, String firstname, String lastname, String email, String confirmEmail, String birthDay, String birthMonth, String birthYear, String gender) {
       String errorMessage = "";
       if ( !password.equals(confirmPassword) )
           errorMessage += "Password mismatched!<br>";
      
       if ( !email.equals(confirmEmail) )
           errorMessage += "Email mismatched!<br>";

       String birthDayDatabaseFormat = birthYear + "-" + birthMonth + "-" + birthDay;
       if (errorMessage.equals("")) { // no error
           try {
               String encryptedPassword = PasswordUtil.encryptPassword(password);
               createUser(username, encryptedPassword, firstname, lastname, email, birthDayDatabaseFormat, gender);
           } catch (Exception ex) {
               errorMessage += ex.getMessage();
           }
       }
       return errorMessage;
   }
   public void createUser(String username, String encryptedPassword, String firstname, String lastname, String email, String birthDate, String gender) {
       AppUser user = new AppUser(username, encryptedPassword, firstname, lastname, email, birthDate, gender);
       AppRole role = appRoleRepository.findByRoleName("ROLE_USER");
       System.out.println(user.getUserId()); // Null
	  // TODO: uncomment เมื่อประกาศ constructor ใน UserRole เพิ่มแล้ว
      UserRole userRole = new UserRole(user, role);

	  // TODO: uncomment เมื่อ appUserRepository และ userRoleRepository พร้อมใช้งาน
      appUserRepository.save(user);
      userRoleRepository.save(userRole);
   }
   // TODO: เพิ่ม method findByUserName ทั้งในไฟล์นี้ตรงนี้และไฟล์ Interface AppUserRepository เพื่อใช้งาน
   public AppUser findByUserName(String userName) {
       //AppUser appUser = appUserRepository.findByUserName(userName);
       return appUserRepository.findByUserName(userName);
   }
}
