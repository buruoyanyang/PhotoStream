package photo.stream.config.base;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * @author xuhf
 * 表名、列名映射策略,适用sringboot项目:
 */
public class ColumnPhysicalNameStrategy extends CamelCaseToUnderscoresNamingStrategy {
    public static Set<String> tableNames = new HashSet<>();

    @Override
    public Identifier toPhysicalColumnName(Identifier name,
                                           JdbcEnvironment jdbcEnvironment) {
        return apply(name, jdbcEnvironment,false);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name,
                                          JdbcEnvironment jdbcEnvironment) {
        tableNames.add(name.getText());
        return apply(name, jdbcEnvironment,false);
    }

    public static String getColumnName(String propertyName) {
        StringBuilder builder = new StringBuilder(propertyName.replace('.', '_'));
        for (int i = 1; i < builder.length() - 1; i++) {
            if (isUnderscoreNeed(builder.charAt(i - 1), builder.charAt(i),
                    builder.charAt(i + 1))) {
                builder.insert(i++, '_');
            }
        }
        return builder.toString();
    }

    private Identifier apply(Identifier name, JdbcEnvironment jdbcEnvironment, boolean uppercase) {
        if (name == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder(name.getText().replace('.', '_'));
        for (int i = 1; i < builder.length() - 1; i++) {
            if (isUnderscoreNeed(builder.charAt(i - 1), builder.charAt(i),
                    builder.charAt(i + 1))) {
                builder.insert(i++, '_');
            }
        }
        String identifierName = builder.toString();
        if(uppercase) {
            identifierName = identifierName.toUpperCase(Locale.ROOT);
        } else {
            identifierName = identifierName.toLowerCase(Locale.ROOT);
        }
        return new Identifier(identifierName, name.isQuoted());
    }


    private static boolean isUnderscoreNeed(char before, char current, char after) {
        return Character.isLowerCase(before) && Character.isUpperCase(current)
                && Character.isLowerCase(after);
    }
}
