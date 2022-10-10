package photo.stream.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionManager;
import photo.stream.config.base.AbstractTransactionAopConfig;

/**
 * @author xuhf
 * @date 2022/10/10
 */
@Configuration
public class TransactionAopConfig extends AbstractTransactionAopConfig {
    public TransactionAopConfig(TransactionManager transactionManager) {
        super(transactionManager);
    }
}
