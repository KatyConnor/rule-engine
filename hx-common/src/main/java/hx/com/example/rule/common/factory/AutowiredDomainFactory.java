package hx.com.example.rule.common.factory;

import hx.com.example.rule.common.domain.Domain;
import hx.com.example.rule.common.utils.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @Author mingliang
 * @Date 2017-08-30 14:17
 *
 *  extends AbstractDomainFactory
 */
@Component
public class AutowiredDomainFactory{

    private final static Logger LOGGER = LoggerFactory.getLogger(AutowiredDomainFactory.class);

    public <T> T create (Class<T> classzz){
        if (classzz == null){
           throw new RuntimeException("domain class must not null");
        }
        try {
            Object obj = classzz.newInstance();
            if ((Object) obj instanceof Domain){
                Object object = obj.getClass().newInstance();
                Domain baseDomain = (Domain) object;
                baseDomain.setGid(UUIDGenerator.getUUIDNumber());
                baseDomain.setId(UUIDGenerator.getUUID());
                return (T) baseDomain;
            }
        } catch (Exception e) {
            LOGGER.error("Failed to initialize class object！",e);
        }
        throw new RuntimeException("domain class not instanceof Domain");
    }

}
