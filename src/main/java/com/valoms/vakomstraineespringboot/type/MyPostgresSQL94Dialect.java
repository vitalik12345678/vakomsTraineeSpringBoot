package com.valoms.vakomstraineespringboot.type;

import org.hibernate.dialect.PostgreSQL94Dialect;

import java.sql.Types;

public class MyPostgresSQL94Dialect extends PostgreSQL94Dialect {
    public MyPostgresSQL94Dialect() {
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
    }
}
