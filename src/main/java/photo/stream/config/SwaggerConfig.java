package photo.stream.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuhf
 * @date 2022/9/21
 */
@Configuration
public class SwaggerConfig {


    @Bean
    public GroupedOpenApi userApi(){
        String[] paths = { "/**" };
        String[] packagedToMatch = { "photo.stream.controller" };
        return GroupedOpenApi.builder()
                .group("照片墙")
                .pathsToMatch(paths)
                .addOperationCustomizer((operation, handlerMethod) -> operation.addParametersItem(new HeaderParameter()
                        .name("groupCode")
                        .example("测试")
                        .description("集团code")
                        .schema(new StringSchema()
                                ._default("BR")
                                .name("groupCode").description("集团code"))))
                .packagesToScan(packagedToMatch).build();
    }
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("照片墙API")
                        .version("1.0")
                        .description( "照片墙API")
                        .license(new License().name("Apache 2.0")));
    }


}