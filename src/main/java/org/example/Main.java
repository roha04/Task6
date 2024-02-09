package org.example;

import db.Database;
import db.DatabaseQueryService;
import db.dao.*;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        //Connection conn = Database.getInstance().getConnection();
        //conn.close();
        //To check work of the following methods you need to run one of it,others must be commented.
        //If you dont, it will occur exception: The object is already closed
        //List<MaxProjectCountClient> maxProjectCountClients = new DatabaseQueryService().findMaxProjectsClient();
        //List<LongestProject> longestProject = new DatabaseQueryService().findLongestProject();
        //List<MaxSalaryWorker> maxSalaryWorker = new DatabaseQueryService().findMaxSalaryWorker();
        //List<YoungestEldestWorker> youngestEldestWorker = new DatabaseQueryService().findYoungestEldestWorker();
        List<ProjectPrice> projectPrices = new DatabaseQueryService().calculateProjectPrice();

    }
}


