import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author muhammad
 */
public class database {
    PreparedStatement insertStatement;
    Connection connection;
     public database() throws SQLException
    {
         
         String dbURL = "jdbc:derby://localhost:1527/Game";
         connection = DriverManager.getConnection(dbURL);
         String insertQuery = "INSERT INTO Labyrinth (NAME,score) VALUES (?,?)";
         insertStatement = connection.prepareStatement(insertQuery);
        
    }
     /**
     * Read the data in the database and sort all the data in the database in descending order.
     * @return ArrayList
     * @throws java.sql.SQLException   
     */
      public ArrayList<Data> getHighScores() throws SQLException {
        String query = "SELECT * FROM Game";
        ArrayList<Data> Points = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery(query);
        while (results.next()) {
            String name = results.getString("NAME");
            int score = results.getInt("POINT");
            
            Points.add(new Data(name, score));
        }
        sortPoints(Points);
        return Points;
    }
      /**
     * Sort array.
     * 
     */
      private void sortPoints(ArrayList<Data> Scores) {
        Collections.sort(Scores, new Comparator<Data>() {
             /**
     * Sort descending.
     * @param t
     * @param t1
     */
            @Override
            
            public int compare(Data t, Data t1) {
                return t1.getScores() - t.getScores();
            }
        });
    }
      /**
     * Insert data into the database.
     * @param name
     * @param score
     * @throws java.sql.SQLException   
     */
       public void insertScore(String name ,int score) throws SQLException {
        //Timestamp ts = new Timestamp(System.currentTimeMillis());
        insertStatement.setString(1, name);
        insertStatement.setInt(2, score);
        insertStatement.executeUpdate();
    }
}
