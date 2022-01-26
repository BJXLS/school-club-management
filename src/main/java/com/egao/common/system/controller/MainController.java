package com.egao.common.system.controller;

import com.egao.common.core.annotation.OperLog;
import com.egao.common.core.utils.EmailCaptchaUtil;
import com.egao.common.core.web.BaseController;
import com.egao.common.core.web.JsonResult;
import com.egao.common.system.entity.LoginRecord;
import com.egao.common.system.entity.Menu;
import com.egao.common.system.entity.User;
import com.egao.common.system.service.EmailService;
import com.egao.common.system.service.LoginRecordService;
import com.egao.common.system.service.MenuService;
import com.egao.common.system.service.UserService;
import com.wf.captcha.utils.CaptchaUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页、登录、验证码等
 * Created by wangfan on 2018-12-24 16:10
 */
@Controller
public class MainController extends BaseController implements ErrorController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private LoginRecordService loginRecordService;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    /**
     * 用户找回页面
     */
    @GetMapping("/workplace")
    public String workplace() {
        return "/schoolClub/workplace.html";
    }

    /**
     * 用户找回页面
     */
    @GetMapping("/forget")
    public String forget() {
        return "forget.html";
    }

    /**
     * 忘记密码发送邮件
     */
    @ResponseBody
    @RequestMapping("/sendEmailVer")
    public JsonResult sendEmailVer(String code, String username, HttpServletRequest request) {
        System.out.println("username: " + username);
        System.out.println("code: " + code);
        if (username == null || username.trim().isEmpty()) return JsonResult.error("请输入账号");
        // 验证码 调用插件
        if (!CaptchaUtil.ver(code, request)) {
            return JsonResult.error("验证码不正确");
        }
        // 通过username获取用户email地址
        User user = userService.getByUsername(username);
        String email = user.getEmail();

        // 获取当前邮箱验证码 并 存入request
        String captcha = EmailCaptchaUtil.getCaptcha(5);
        request.getSession().setAttribute("emailCaptha", captcha);
        System.out.println("email: " + email);
        System.out.println("emailCaptha: " + captcha);

        String title="新疆大学社团管理系统————密码找回";
        String html="<p><strong>亲爱的同学，你好：</strong></p>\n" +
                "<p>&nbsp; &nbsp; 您正在进行找回密码操作，您的验证码是<span style=\"color: #e03e2d; font-size:26px; \"><strong>"+ captcha + "</strong></span>。</p>";

        try {
            emailService.sendFullTextEmail(title, html, new String[]{email});
            return JsonResult.ok("发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return JsonResult.error("发送失败");
    }

    /**
     * 用户找回密码
     */
    @ResponseBody
    @PostMapping("/forget")
    public JsonResult forget(User user, HttpServletRequest request) {
        // 验证验证码
        String realCode = (String)request.getSession().getAttribute("emailCaptha");
        if (!realCode.equals(user.getCode())) {
            return JsonResult.error("邮箱验证码不正确");
        }

        // 获取用户 并 重置密码
        User user1 = userService.getByUsername(user.getUsername());
        user1.setPassword(userService.encodePsw(user.getPassword()));

        if (userService.updateById(user1)) {
            return JsonResult.ok("注册成功");
        }
        return JsonResult.error("注册失败");
    }

    /**
     * 用户注册页面
     */
    @GetMapping("/register")
    public String register() {
        return "reg.html";
    }

    /**
     * 用户注册
     */
    @ResponseBody
    @PostMapping("/register")
    public JsonResult save(User user, HttpServletRequest request) {
        // 验证验证码
        if (!CaptchaUtil.ver(user.getCode(), request)) {
            return JsonResult.error("验证码不正确");
        }

        // 默认开启用户
        user.setState(0);
        // 对密码进行加密
        user.setPassword(userService.encodePsw(user.getPassword()));
        user.setNickName(user.getUsername());
        // 添加角色id 默认为普通学生
        List<Integer> roleids = new ArrayList<>();
        roleids.add(4);
        user.setRoleIds(roleids);
        if (userService.saveUser(user)) {
            return JsonResult.ok("注册成功");
        }
        return JsonResult.error("注册失败");
    }

    /**
     * 用户登录
     */
    @ResponseBody
    @PostMapping("/login")
    public JsonResult login(String username, String password, String code, Boolean remember, HttpServletRequest request) {
        if (username == null || username.trim().isEmpty()) return JsonResult.error("请输入账号");
        // 验证码 调用插件
        if (!CaptchaUtil.ver(code, request)) {
            loginRecordService.saveAsync(username, LoginRecord.TYPE_ERROR, "验证码错误", request);
            return JsonResult.error("验证码不正确");
        }

        // 利用shiro进行登录控制
        try {
            if (remember == null) remember = false;
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password, remember));
            loginRecordService.saveAsync(username, request);
            return JsonResult.ok("登录成功");
        } catch (IncorrectCredentialsException ice) {
            loginRecordService.saveAsync(username, LoginRecord.TYPE_ERROR, "密码错误", request);
            return JsonResult.error("密码错误");
        } catch (UnknownAccountException uae) {
            loginRecordService.saveAsync(username, LoginRecord.TYPE_ERROR, "账号不存在", request);
            return JsonResult.error("账号不存在");
        } catch (LockedAccountException e) {
            loginRecordService.saveAsync(username, LoginRecord.TYPE_ERROR, "账号被锁定", request);
            return JsonResult.error("账号被锁定");
        } catch (ExcessiveAttemptsException eae) {
            loginRecordService.saveAsync(username, LoginRecord.TYPE_ERROR, "操作频繁", request);
            return JsonResult.error("操作频繁，请稍后再试");
        }
    }

    /**
     * 登录页
     */
    @GetMapping("/login")
    public String login() {
        if (getLoginUser() != null) return "redirect:index";
        return "login.html";
    }

    /**
     * 主页
     */
    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        // 左侧菜单
        List<Menu> menus = menuService.getUserMenu(getLoginUserId(), Menu.TYPE_MENU);
        model.addAttribute("menus", menuService.toMenuTree(menus, 0));
        return "index.html";
    }

    /**
     * 图形验证码
     */
    @RequestMapping("/assets/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            CaptchaUtil.out(5, request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 主页弹窗页面
     */
    @RequestMapping("/tpl/{name}")
    public String tpl(@PathVariable("name") String name) {
        return "index/" + name + ".html";
    }

    /**
     * 错误页
     */
    @RequestMapping("/error")
    public String error() {
        return "error/404.html";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
