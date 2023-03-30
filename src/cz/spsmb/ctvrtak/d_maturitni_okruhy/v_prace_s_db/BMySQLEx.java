package cz.spsmb.ctvrtak.d_maturitni_okruhy.v_prace_s_db;

import java.sql.*;

public class BMySQLEx {
    //File->Project Structure->Libraries, Maven,  mysql:mysql-connector-java:5.1.40
    private final String dbms="mysql";
    private final String serverName = "vydb1.spsmb.cz";
    private final int portNumber = 3306;
    private final String dbName = "";
    private final String userName="";
    private final String password="";
    private Connection conn;
    public void getConnectionToDatabase() throws SQLException {
        {
            if (this.dbms.equals("mysql")) {
                this.conn =
                        DriverManager.getConnection("jdbc:" + this.dbms + "://" + this.serverName +
                                ":" + this.portNumber + "/" + this.dbName, this.userName, this.password);
                this.conn.setCatalog(this.dbName);
                //Statement stmt = this.conn.createStatement();
                //stmt.executeQuery("set names utf8;");
            }
            System.out.println("Connected to database");
        }
    }

    public long insertQuestion(int obtiznost_id, String question, long spravna_odpoved) throws SQLException {
        long returnValue;
        try (
                PreparedStatement statement = this.conn.prepareStatement(
                        "INSERT INTO otazky (obtiznost_id, nazev, spravna_odpoved) VALUES (?,?,?);",
                        Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, String.valueOf(obtiznost_id));
            statement.setString(2, question);
            statement.setString(3, String.valueOf(spravna_odpoved));
            // ...

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    returnValue = generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
        return returnValue;
    }
    public long insertAnswer(String answer) throws SQLException {
        long returnValue;
        try (
                PreparedStatement statement = this.conn.prepareStatement(
                        "INSERT INTO odpovedi (nazev) VALUES (?);",
                        Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, answer);
            // ...

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    returnValue = generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
        return returnValue;
    }
    public long insertWrongAnswerMap(long question, long answer) throws SQLException {
        long returnValue;
        try (
                PreparedStatement statement = this.conn.prepareStatement(
                        "INSERT INTO otazky_spatne_odpovedi (otazka_id, odpoved_id) VALUES (?,?);",
                        Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, String.valueOf(question));
            statement.setString(2, String.valueOf(answer));
            // ...

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    returnValue = generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
        return returnValue;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Statement stmt;
        // Connection string pro DB mysql, server vydb1.spsmb.cz, port 3306 a DB mojedb
        //String url = "jdbc:mysql://vydb1.spsmb.cz:3306/mojedb";
        String url = "jdbc:mysql://mysql.hostnow.cz:3306/sql1362_admin";
        Connection conn = DriverManager.getConnection(url, "sql1362_admin", "vitek0025");

        // vytvoření tabulky ((C)RUD - Create)
        stmt = conn.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS mojetabulka " +
                "(id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                " name VARCHAR(255) NOT NULL)" ;
        stmt.executeUpdate(sql);
        stmt.close();

        stmt = conn.createStatement();
        ResultSet rs1 = stmt.executeQuery("SELECT * FROM mojetabulka ORDER BY 1 DESC LIMIT 1;");
        int id = 1;
        while (rs1.next()) {
            id = rs1.getInt("id") + 1;
        }


        //naplnění daty ((C)RUD -Create)
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO mojetabulka (name) VALUES (?)");
        pstmt.setString(1, String.format("%d. záznam do DB", id));
        pstmt.executeUpdate();

        //čtení dat (C(R)UD - Read)
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM mojetabulka;");
        String format = "%3s|%20s\n";
        System.out.format(format, "id", "name");
        while(rs.next()){
            System.out.format(format, rs.getInt("id"), rs.getString("name"));
        }

        //uzavření spojení s DB
        conn.close();
    }
}
