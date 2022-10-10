package photo.stream.config.base;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 全局事务切面配置,适用spring boot项目
 * springboot aop
 * @author Xuhuanfeng
 * @date 2021年06月01日14:06:43
 */
public abstract class AbstractTransactionAopConfig {
    private static final int TX_METHOD_TIMEOUT = 120;
    private static final String AOP_POINTCUT_EXPRESSION = "(execution(* photo.stream.service.*.*(..)))";

    private final TransactionManager transactionManager;

    public AbstractTransactionAopConfig(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Bean(name = "txAdvice")
    public TransactionInterceptor txAdvice() {
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        RuleBasedTransactionAttribute readOnlyRule = new RuleBasedTransactionAttribute();
        readOnlyRule.setReadOnly(true);
        readOnlyRule.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);

        RuleBasedTransactionAttribute requireRule = new RuleBasedTransactionAttribute();
        requireRule.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requireRule.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requireRule.setTimeout(TX_METHOD_TIMEOUT);

        RuleBasedTransactionAttribute requireNewRule = new RuleBasedTransactionAttribute();
        requireNewRule.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requireNewRule.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        requireNewRule.setTimeout(TX_METHOD_TIMEOUT);

        Map<String, TransactionAttribute> txMap = new HashMap<>();

        txMap.put("create*", requireRule);
        txMap.put("insert*", requireRule);
        txMap.put("update*", requireRule);
        txMap.put("modify*", requireRule);
        txMap.put("save*", requireRule);
        txMap.put("delete*", requireRule);
        txMap.put("merge*", requireRule);
        txMap.put("effect*", requireRule);
        txMap.put("inBound*", requireRule);
        txMap.put("outBound*", requireRule);
        txMap.put("doInit*", requireRule);
        txMap.put("init*", requireRule);
        txMap.put("assign*", requireRule);
        txMap.put("unAssign*", requireRule);
        txMap.put("move*", requireRule);
        txMap.put("set*", requireRule);
        txMap.put("reset*", requireRule);
        txMap.put("add*", requireRule);
        txMap.put("enable*", requireRule);
        txMap.put("disable*", requireRule);
        txMap.put("copy*", requireRule);
        txMap.put("build*", requireRule);
        txMap.put("import*", requireNewRule);
        txMap.put("execute*", requireNewRule);
        txMap.put("sync*", requireNewRule);

        txMap.put("get*", readOnlyRule);
        txMap.put("find*", readOnlyRule);
        txMap.put("select*", readOnlyRule);
        txMap.put("load*", readOnlyRule);
        txMap.put("query*", readOnlyRule);
        txMap.put("list*", readOnlyRule);
        txMap.put("is*", readOnlyRule);
        txMap.put("do*", readOnlyRule);
        source.setNameMap(txMap);
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor(TransactionInterceptor txAdvice) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice);
    }
}
