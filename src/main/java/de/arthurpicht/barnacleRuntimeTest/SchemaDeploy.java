package de.arthurpicht.barnacleRuntimeTest;

import org.h2.tools.RunScript;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SchemaDeploy {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String USER = "sa";
    static final String PASS = "sa";


    public static void deploy(String testCase) {

        String dbUrl = "jdbc:h2:./db/" + testCase;
        Path sqlFile = RuntimeTestPaths.getSql(testCase);

        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(dbUrl,USER,PASS);
            RunScript.execute(conn, new FileReader(sqlFile.toAbsolutePath().toString()));
            conn.close();
        } catch(Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

}
