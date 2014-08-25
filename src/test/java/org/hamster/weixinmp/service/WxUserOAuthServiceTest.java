package org.hamster.weixinmp.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.hamster.weixinmp.dao.entity.user.WxUserEntity;
import org.hamster.weixinmp.base.AbstractServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hacker on 2014/8/18.
 */
public class WxUserOAuthServiceTest extends AbstractServiceTest {
    @Autowired
    WxUserOAuthService wxUserOAuthService;
    @Test
    public void testGetCodeUrl() throws Exception {
        String redirect_uri="m.boyacai.com";
        String state="a";
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder url=wxUserOAuthService.getCodeUrl(WxUserOAuthService.Scope.snsapi_base, redirect_uri, state);
        HttpGet request=new HttpGet(url.build());
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        String respBody = EntityUtils.toString(entity);
        System.err.println(respBody);
    }

    @Test
    public void testFetchAccessTokenByCode() throws Exception {
        String code="00f04548196898de9ff396f5cf7c4b81";
        WxUserEntity userEntity= wxUserOAuthService.fetchAccessTokenByCode(code);
        System.err.println(userEntity.toString());
    }

    @Test
    public void testRefreshToken() throws Exception {

    }

    @Test
    public void testGetUserInfo() throws Exception {

    }
}
