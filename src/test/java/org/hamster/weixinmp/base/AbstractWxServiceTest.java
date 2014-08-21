/**
 * 
 */
package org.hamster.weixinmp.base;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.hamster.weixinmp.dao.entity.auth.WxAuth;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.service.WxAuthService;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 5, 2014
 * 
 */
public abstract class AbstractWxServiceTest extends AbstractServiceTest {

	protected String accessToken;

	@Autowired
	WxAuthService authService;
	
	@Before
	public void setUp() throws WxException, IOException {
		if (StringUtils.isNotBlank(accessToken)) {
		    return;
        }
        WxAuth auth = authService.getAccessToken();
        this.accessToken = auth.getAccessToken();
	}
}
