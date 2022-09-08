/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;
/**
 *
 * @author Jardel
 */
public class ProjectController {
    public void save(Project project){
        String sql = "INSERT INTO projects(name,"
                + "description,createdat,updatedat) VALUES (?,?,?,?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis())); //project.getCreatedAt().getTime()
            statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            statement.execute();
        }catch(Exception e){
            throw new RuntimeException("Erro ao salvar o projeto"+e.getMessage(),e);
        }finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    public void update(Project project){
        String sql = "UPDATE projects SET name= ?, description= ?,"
                + "updatedat= ? WHERE id= ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            statement.setInt(4, project.getId());
            statement.execute();
    }catch(Exception e){
            throw new RuntimeException("Erro ao atualizar o projeto"+e.getMessage(),e);
        }finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    public List<Project> getAll(){
        String sql = "SELECT * FROM projects";
        List<Project> projects = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
          connection= ConnectionFactory.getConnection();
          statement = connection.prepareStatement(sql);
          resultSet = statement.executeQuery();
          while(resultSet.next()){
              Project project = new Project();
              project.setId(resultSet.getInt("id"));
              project.setName(resultSet.getString("name"));
              project.setDescription(resultSet.getString("description"));
              project.setCreatedAt(resultSet.getDate("createdAt"));
              project.setUpdatedAt(resultSet.getDate("updatedAt"));
              projects.add(project);
          }
        }catch(Exception e){
           throw new RuntimeException("Erro ao buscar projetos"+e.getMessage(),e); 
        }finally{
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        return projects;
    }
    
    public void removeById (int idProject){
        String sql = "DELETE FROM projects WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProject);
            statement.execute();
        }catch(Exception e){
            throw new RuntimeException("Erro ao deletar o projeto"+e.getMessage(),e); 
        }finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
}
