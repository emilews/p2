package com.javacodegeeks.patterns.proxypattern.remoteproxy.database;

import com.mysql.jdbc.Connection;

public interface DatabaseActions {
    public String[] Read(String query);
    public boolean Write(String query);
    public boolean Delete(String query);
    public boolean Update(String query);
}
