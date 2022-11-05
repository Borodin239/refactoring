package ru.akirakozov.sd.refactoring.data;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
interface ResultParser<T> {
    T apply(ResultSet rs) throws SQLException;
}
