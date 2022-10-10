package photo.stream.config.base;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitBasicColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;

import java.io.Serial;

/**
 * @author xuhf
 * 缺省列名映射策略,适用sringboot项目:
 */
public class ColumnImplicitNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {
    @Serial
    private static final long serialVersionUID = 1264187465403186656L;

    @Override
    public Identifier determineBasicColumnName(ImplicitBasicColumnNameSource source) {
        String logicColName = transformAttributePath( source.getAttributePath() );
        return toIdentifier(logicColName , source.getBuildingContext() );
    }
}
