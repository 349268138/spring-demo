package com.meituan.pay.finsecurity.listener;

import com.sankuai.it.sso.sdk.enums.AuthFailedCodeEnum;
import com.sankuai.it.sso.sdk.listener.SSOListener;
import com.sankuai.meituan.auth.vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2021/1/7.
 */
public class SecuritySSOListener implements SSOListener {
    @Override
    public String onRedirectingToSSOLogin(HttpServletRequest req, HttpServletResponse res, String callbackUri) {
        return "/api" + callbackUri;
    }

    @Override
    public String onRedirectingToOriginalUrl(HttpServletRequest req, HttpServletResponse res, String ssoid, String originalUrl) {
        return null;
    }

    @Override
    public boolean onSSOAuthed(HttpServletRequest req, HttpServletResponse res, User user) {
        return true;
    }

    @Override
    public boolean onSSOAuthFailed(HttpServletRequest req, HttpServletResponse res, AuthFailedCodeEnum failedCode) {
        return false;
    }

    @Override
    public void onSSOLogouted(HttpServletRequest req, HttpServletResponse res, User user) {

    }
}
