package com.sqlite.demo.dialect;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.type.StringType;

import java.sql.Types;

public class SQLiteDialect extends Dialect {
    private static final String CONSTANT_INTEGER = "integer";
    private static final String CONSTANT_SUBSTR = "substr";
    public SQLiteDialect() {
        registerColumnType(Types.BIT, CONSTANT_INTEGER);
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.INTEGER, CONSTANT_INTEGER);
        registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.FLOAT, "float");
        registerColumnType(Types.REAL, "real");
        registerColumnType(Types.DOUBLE, "double");
        registerColumnType(Types.NUMERIC, "numeric");
        registerColumnType(Types.DECIMAL, "decimal");
        registerColumnType(Types.CHAR, "char");
        registerColumnType(Types.VARCHAR, "varchar");
        registerColumnType(Types.LONGVARCHAR, "longvarchar");
        registerColumnType(Types.DATE, "date");
        registerColumnType(Types.TIME, "time");
        registerColumnType(Types.TIMESTAMP, "timestamp");
        registerColumnType(Types.BINARY, "blob");
        registerColumnType(Types.VARBINARY, "blob");
        registerColumnType(Types.LONGVARBINARY, "blob");
        registerColumnType(Types.BLOB, "blob");
        registerColumnType(Types.CLOB, "clob");
        registerColumnType(Types.BOOLEAN, CONSTANT_INTEGER);

        registerFunction("concat", new VarArgsSQLFunction(StringType.INSTANCE, "", "||", ""));
        registerFunction("mod", new SQLFunctionTemplate(StringType.INSTANCE, "?1 % ?2"));
        registerFunction(CONSTANT_SUBSTR, new StandardSQLFunction(CONSTANT_SUBSTR, StringType.INSTANCE));
        registerFunction("substring", new StandardSQLFunction(CONSTANT_SUBSTR, StringType.INSTANCE));
    }

    public boolean supportsIdentityColumns() {
        return true;
    }

    public boolean hasDataTypeInIdentityColumn() {
        return false;
    }

    public String getIdentityColumnString() {
        return CONSTANT_INTEGER;
    }

    public String getIdentitySelectString() {
        return "select last_insert_rowid()";
    }
    @Override
    public boolean supportsLimit() {
        return true;
    }
    @Override
    protected String getLimitString(String query, boolean hasOffset) {
        return query + (hasOffset ? " limit ? offset ?" : " limit ?");
    }

    public boolean supportsTemporaryTables() {
        return true;
    }

    public String getCreateTemporaryTableString() {
        return "create temporary table if not exists";
    }

    public boolean dropTemporaryTableAfterUse() {
        return false;
    }
    @Override
    public boolean supportsCurrentTimestampSelection() {
        return true;
    }
    @Override
    public boolean isCurrentTimestampSelectStringCallable() {
        return false;
    }
    @Override
    public String getCurrentTimestampSelectString() {
        return "select current_timestamp";
    }
    @Override
    public boolean supportsUnionAll() {
        return true;
    }
    @Override
    public boolean hasAlterTable() {
        return false;
    }
    @Override
    public boolean dropConstraints() {
        return false;
    }
    @Override
    public String getAddColumnString() {
        return "add column";
    }
    @Override
    public String getForUpdateString() {
        return "";
    }
    @Override
    public boolean supportsOuterJoinForUpdate() {
        return false;
    }
    @Override
    public String getDropForeignKeyString() {
        throw new UnsupportedOperationException("No drop foreign key syntax supported by SQLiteDialect");
    }
    @Override
    public String getAddForeignKeyConstraintString(String constraintName, String[] foreignKey, String referencedTable,
                                                   String[] primaryKey, boolean referencesPrimaryKey) {
        throw new UnsupportedOperationException("No add foreign key syntax supported by SQLiteDialect");
    }
    @Override
    public String getAddPrimaryKeyConstraintString(String constraintName) {
        throw new UnsupportedOperationException("No add primary key syntax supported by SQLiteDialect");
    }
    @Override
    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }
    @Override
    public boolean supportsCascadeDelete() {
        return false;
    }
}
