package org.hamster.weixinmp.dao.repository.msg;

import junit.framework.Assert;
import org.hamster.weixinmp.base.AbstractWxServiceTest;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hacker on 2014/8/22.
 */
public class WxBaseMsgDaoTest extends AbstractWxServiceTest {
    @Autowired
    private WxBaseMsgDao baseMsgDao;
    @Test
    public void testFindByMsgId() throws Exception {
        WxBaseMsgEntity entity=baseMsgDao.findByMsgId(Long.parseLong("6050331446666910670")).get(0);
        System.out.println(entity);
        Assert.assertNotNull(entity);
    }
}
