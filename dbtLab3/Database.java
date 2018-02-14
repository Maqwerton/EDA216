

import java.sql.*;
import java.util.*;

/**
 * Database is a class that specifies the interface to the movie
 * database. Uses JDBC.
 */
public class Database {

    /**
     * The database connection.
     */
    private Connection conn;

    /**
     * Create the database interface object. Connection to the
     * database is performed later.
     */
    public Database() {
        conn = null;
    }

    /**
     * Open a connection to the database, using the specified user
     * name and password.
     */
    public boolean openConnection(String filename) {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + filename);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Close the connection to the database.
     */
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if the connection to the database has been established
     *
     * @return true if the connection has been established
     */
    public boolean isConnected() {

        return conn != null;
    }

    public boolean userExist(String userId) {

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT userId\n" +
                    "FROM users \n" +
                    "where username = userId";
            if (stmt.executeQuery(sql) == null) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<Movie> getMovies() {
        List<Movie> found = new LinkedList<>();
        try {
            String sql =
                "SELECT title\n" +
                "FROM   Movie";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                found.add(new Movie(rs));
            }
            return found;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return found;
        }
    
    public List<User> getUsers()    {
        List<Users> found = new LinkedList<>();
        try {
            String sql =
                "SELECT username\n" +
                "FROM users"
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next())   {
                found.add(new User(rs));
            }
            return found;
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return found;
        }
    }

    public List<MoviePerformance> getPeformances(String movie) {
        List<MoviePreformance> found = new LinkedList<>();
        try {
            String sql =
                "SELECT id, theater, movie\n" +
                "FROM   MoviePreformance \n" +
                "WHERE title = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, movie);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                found.add(new MoviePerformance(rs));
            }
            return found;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // code to clone our Statement...
         }
        return found;
    }

class Movie {
    public final String movieName;

    public Movie(ResultSet rs) throws SQLException {
        this.title = rs.getString("movie_name");
    }

class Theater {
    public final String theaterName, address;
    public final int seats;

    Public Theater(ResultSet rs) throws SQLException {
        this.theaterName = rs.getString("theater_name");
        this.address = rs.getString("address");
        this.seats = rs.getString("seats");
    }
}

class User {
    public final String username, address, phone;

    Public User(ResultSet rs) throws SQLException {
        this.username = username;
        this.address = address;
        this.phone = phone;
    }
}

class Performance   {
    public final int id;
    public final String location, movie, theater;
    
    Public Performance(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.location = rs.getString("location");
        this.movie = rs.getString("movie");
        this.theater = rs.getString("theater");

        
    }   
}

class Reservation   {
    public final int resNbr;
    public final String toSee, booker;

    Public Reservation(ResultSet rs) throws SQLException  {
        this.resNbr = rs.getInt("resNbr");
        this.toSee = rs.getString("to_see");
        this.booker = rs.getString("booker");
    }
}

}


