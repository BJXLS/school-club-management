package com.egao.common.system.controller;

import com.egao.common.core.annotation.OperLog;
import com.egao.common.core.utils.EmailCaptchaUtil;
import com.egao.common.core.web.JsonResult;
import com.egao.common.system.entity.User;
import com.egao.common.system.service.EmailService;
import com.egao.common.system.service.UserService;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * 邮件功能
 * Created by wangfan on 2020-03-21 0:37
 */
@Controller
@RequestMapping("/sys/email")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

    @RequiresPermissions("sys:email:view")
    @RequestMapping()
    public String view() {
        return "system/email.html";
    }

    /**
     * 发送邮件
     */
    @OperLog(value = "邮件功能", desc = "发送邮件", result = true, param = false)
    @RequiresPermissions("sys:email:view")
    @ResponseBody
    @RequestMapping("/send")
    public JsonResult send(String title, String html, String email) {
        try {
            emailService.sendFullTextEmail(title, html, new String[]{email});
            return JsonResult.ok("发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return JsonResult.error("发送失败");
    }

    /**
     * 发送邮件
     */
    @OperLog(value = "发送", desc = "发送邮件", result = true, param = false)
    @RequiresPermissions("sys:email:view")
    @ResponseBody
    @RequestMapping("/sendEmailVer")
    public JsonResult sendEmailVer(String code, String username, HttpServletRequest request) {
//        System.out.println("正常进入email");
//        System.out.println("code: " + code + ",username: " + username);
//        if (username == null || username.trim().isEmpty()) return JsonResult.error("请输入账号");
//        // 验证码 调用插件
//        if (!CaptchaUtil.ver(code, request)) {
//            return JsonResult.error("验证码不正确");
//        }
//        // 通过username获取用户email地址
//        User user = userService.getByUsername(username);
//        String email = user.getEmail();
//
//        // 获取当前邮箱验证码 并 存入request
//        String captcha = EmailCaptchaUtil.getCaptcha(5);
//        request.getSession().setAttribute("emailCaptha", captcha);
//
//        String title="新疆大学社团管理系统————密码找回";
//        String html="<p><strong>亲爱的同学，你好：</strong></p>\n" +
//                "<p>&nbsp; &nbsp; 您正在进行找回密码操作，您的验证码是<span style=\"color: #e03e2d;\"><strong>"+ captcha + "。</strong></span></p>";
//
//        try {
//            emailService.sendFullTextEmail(title, html, new String[]{email});
//            return JsonResult.ok("发送成功");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
        return JsonResult.error("发送失败");
    }

}
