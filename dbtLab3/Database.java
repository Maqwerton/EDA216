

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


    public List<Movie> getMovies() {
        List<Movie> result = new LinkedList<>();
        try {
            String sql =
                "SELECT movie_name\n" +
                "FROM movies";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                result.add(new Movie(rs));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return result;
        }
    
    public User getUser(String username)    {
        try {
            String sql =
                "SELECT username\n" +
                "FROM users\n" +
                "WHERE username=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            User user = new User(rs);
            return user;
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return null;
        }

    public User userExist(String username)    {
        try {
            String sql =
                        "SELECT username\n" +
                        "FROM users\n" +
                        "WHERE username=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            User user = new User(rs);
            return user;
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }


    public List<Performance> getPerformances(String movie) {
        List<Performance> result = new LinkedList<>();
        try {
            String sql =
                "SELECT id, theater, movie, play_date, availableSeats\n" +
                "FROM  performances\n" +
                "WHERE movie=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, movie);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Performance(rs));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
         }
        return result;
    }

    public Performance getPerformance(String movie, String date) {
        Performance result = null;
        try {
            String sql =
                    "SELECT id, theater, movie, play_date, availableSeats\n" +
                            "FROM  performances\n" +
                            "WHERE movie=? and play_date=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, movie);
            ps.setString(2, date);
            ResultSet rs = ps.executeQuery();
            result = new Performance(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return result;
    }

    public boolean updatePerformance(int id)   {
        try {
            String sql = 
            "update performances\n"+
            "set availableSeats = availableSeats-1\n"+
            "where id=? and availableSeats > 0";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int n = ps.executeUpdate();
            if(n != 0) {
                return true; 
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return false;
    }

    public void makeReservation(String performance, String username)    {
        try {
            String sql = 
            "INSERT INTO reservations(to_see, booker)\n"+
            "VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, performance);
            ps.setString(2, username);
            ps.executeUpdate();
        }   catch(SQLException e)   {
            e.printStackTrace();
        } finally   {
        }
    }
}   

class Movie {
    public final String movieName;

    public Movie(ResultSet rs) throws SQLException {
        this.movieName = rs.getString("movie_name");
    }
}

class Theater {
    public final String theaterName, address;
    public final int seats;

    public Theater(ResultSet rs) throws SQLException {
        this.theaterName = rs.getString("theater_name");
        this.address = rs.getString("address");
        this.seats = rs.getInt("seats");
    }
}

class User {
    public final String username;

    public User(ResultSet rs) throws SQLException {
        this.username = rs.getString("username");
    }
}

class Performance   {
    public final int id;
    public final String movie, theater, play_date, availableSeats;
    
    public Performance(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.movie = rs.getString("movie");
        this.theater = rs.getString("theater");
        this.play_date = rs.getString("play_date");
        this.availableSeats = rs.getString("availableSeats");
        
    }   
}

class Reservation   {
    public final int resNbr;
    public final String toSee, booker;

    public Reservation(ResultSet rs) throws SQLException  {
        this.resNbr = rs.getInt("resNbr");
        this.toSee = rs.getString("to_see");
        this.booker = rs.getString("booker");
    }
}


