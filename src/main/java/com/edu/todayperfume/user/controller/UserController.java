package com.edu.todayperfume.user.controller;

import com.edu.todayperfume.user.dto.UserDto;
import com.edu.todayperfume.user.dto.UserRequestDto;
import com.edu.todayperfume.user.dto.LoginRequestDto;
import com.edu.todayperfume.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    /** 로그인 뷰 */
    @GetMapping("/login")
    public String loginView() {
        return "user/login";
    }

    /** 로그인 기능 */
    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequestDto req, Model model) {
        boolean isLogin = userService.login(req);
        if(!isLogin){
            model.addAttribute("error", "존재하지 않는 사용자 정보입니다.");
            model.addAttribute("id", req.id());
            return "user/login";
        }
        return "redirect:/";
    }

    /** 회원가입 뷰 */
    @GetMapping("/signup")
    public String signupView(){
        return "user/signup";
    }

    /** 회원가입 기능 */
    @PostMapping("/signup")
    public String signup(@ModelAttribute UserRequestDto req, Model model) {
        UserDto result = userService.createUser(req);
        // 중복된 정보
        if(result == null){
            model.addAttribute("error", "중복된 사용자 정보입니다.");
            model.addAttribute("info", req);
            return "user/signup";
        }
        return "redirect:/user/login";
    }

    /** 로그아웃 기능 */
    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";
    }

    /** 회원탈퇴 기능 */
    @DeleteMapping()
    public String deleteUser() {
        userService.deleteUser();
        return "redirect:/";
    }

    /** 비밀번호 변경 기능 */
    @PostMapping("/password")
    public String password(@RequestParam("password") String password) {
        userService.updatePassword(password);
        return "redirect:/";
    }
}
