package com.javacodegeeks.patterns.proxypattern.remoteproxy.database;
import java.sql.ResultSet;

public interface DatabaseActions {
    public ResultSet Read(String query);
    public boolean Write(String query);
    public boolean Delete(String query);
    public boolean Update(String query);
}
