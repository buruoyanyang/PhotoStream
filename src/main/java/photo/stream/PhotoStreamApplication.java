package photo.stream;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import photo.stream.repository.impl.BaseRepositoryImpl;

import javax.annotation.Resource;

/**
 * @author xuhf
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class, basePackages = {
        "photo.stream.repository"
})
@EntityScan({
        "photo.stream.model.entity",
})
public class PhotoStreamApplication implements TransactionManagementConfigurer {

    @Resource
    private PlatformTransactionManager txManager;

    public static void main(String[] args) {
        SpringApplication.run(PhotoStreamApplication.class, args);
    }

    @NotNull
    @Override
    public TransactionManager annotationDrivenTransactionManager() {
        return this.txManager;
    }
}
