package com.javacodegeeks.patterns.proxypattern.remoteproxy.database;

public class DatabaseFactory {

    public synchronized static DatabaseActions getInstance(String type) throws ClassNotFoundException {
        switch (type){
            case "postgresql":
                return new PostgreSQL();
            case "mysql":
                return new MySQL();
        }
        return null;
    }

}
