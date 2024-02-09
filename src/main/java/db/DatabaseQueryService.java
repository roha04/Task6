package db;
import db.dao.*;
import reader.SqlFileReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    private static final String MAX_PROJECTS_CLIENT_QUERY = "sql/find_max_projects_client.sql";
    private static final String LONGEST_PROJECT_QUERY = "sql/find_longest_project.sql";
    private static final String MAX_SALARY_WORKER_QUERY = "sql/find_max_salary_worker.sql";
    private static final String YOUNGEST_ELDEST_WORKER_QUERY = "sql/find_youngest_eldest_workers.sql";
    private static final String PROJECT_PRICE_QUERY="sql/print_project_prices.sql";

    private static String sqlQuery="";

    public List<MaxProjectCountClient> findMaxProjectsClient() throws IOException, SQLException {
        ArrayList<MaxProjectCountClient> result = new ArrayList<>();
        
        try{
             sqlQuery = SqlFileReader.readSqlFile(MAX_PROJECTS_CLIENT_QUERY);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        
        try (Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            
            while (resultSet.next()) {
                MaxProjectCountClient client = new MaxProjectCountClient();
                client.setName(resultSet.getString("name"));
                client.setProjectCount(resultSet.getInt("project_count"));
                result.add(client);
            }
        }

        return result;

    }
    public List<LongestProject> findLongestProject() throws IOException,SQLException{
        ArrayList<LongestProject> result = new ArrayList<>();

        try{
            sqlQuery = SqlFileReader.readSqlFile(LONGEST_PROJECT_QUERY);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        try (Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {


            while (resultSet.next()) {
                LongestProject longestProject = new LongestProject();
                longestProject.setId(resultSet.getInt("id"));
                longestProject.setCountDay(resultSet.getInt("count_day"));
                result.add(longestProject);
            }
        }

        return result;

    }
    public List<MaxSalaryWorker> findMaxSalaryWorker() throws IOException,SQLException {
        ArrayList<MaxSalaryWorker> result= new ArrayList<>();

        try{
            sqlQuery = SqlFileReader.readSqlFile(MAX_SALARY_WORKER_QUERY);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        try (Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {


            while (resultSet.next()) {
                MaxSalaryWorker maxSalaryWorker = new MaxSalaryWorker();
                maxSalaryWorker.setName(resultSet.getString("name"));
                maxSalaryWorker.setSalary(resultSet.getInt("salary"));
                result.add(maxSalaryWorker);
            }
        }

        return result;
    }
    public List<YoungestEldestWorker> findYoungestEldestWorker() throws IOException,SQLException{
        ArrayList<YoungestEldestWorker> result= new ArrayList<>();
        try{
            sqlQuery = SqlFileReader.readSqlFile(YOUNGEST_ELDEST_WORKER_QUERY);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        try (Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                YoungestEldestWorker youngestEldestWorker = new YoungestEldestWorker();
                youngestEldestWorker.setType(Type.valueOf(resultSet.getString("type")));
                youngestEldestWorker.setName(resultSet.getString("name"));
                youngestEldestWorker.setBirthday(resultSet.getDate("birthday"));
                result.add(youngestEldestWorker);
            }
        }

        return result;
    }
    public List<ProjectPrice> calculateProjectPrice() throws IOException, SQLException {
        List<ProjectPrice> result = new ArrayList<>();

        String sqlQuery = "";
        try {
            sqlQuery = SqlFileReader.readSqlFile(PROJECT_PRICE_QUERY);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return result; 
        }

        try (Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                ProjectPrice projectPrice = new ProjectPrice();
                projectPrice.setClientName(resultSet.getString("client_name"));
                projectPrice.setProjectPrice(resultSet.getInt("project_price"));
                result.add(projectPrice);
            }
        }

        return result;
    }

}

