/**
 * 
 */
package org.hamster.weixinmp.dao.repository.user;

import org.hamster.weixinmp.dao.entity.user.WxUserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 *
 *
 */
@Repository
public interface WxUserDao extends PagingAndSortingRepository<WxUserEntity, Long> {

    /**
     * @param openid
     */
    WxUserEntity findByOpenId(String openid);

    /**
     * 查询所有关注的用户（state=1）
     * @return
     */
    @Query("select w from WxUserEntity w where w.state=1")
    List<WxUserEntity> findAllSubscribeUser();

}
