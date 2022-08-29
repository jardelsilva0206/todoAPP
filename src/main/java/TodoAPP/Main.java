/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TodoAPP;

import controller.ProjectController;
import java.sql.Date;
import java.util.List;
import model.Project;

/**
 *
 * @author Jardel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ProjectController projectController = new ProjectController();
       Project project = new Project();
       Date data = new Date(1992/11/31);
       project.setName("Projeto-teste2");
       project.setDescription("description");
       project.setCreatedAt(data);
       project.setUpdatedAt(data);
       project.setId(2);
       //projectController.save(project);
       //projectController.removeById(5);
       //projectController.update(project);
       //List projetos = projectController.getAll();
       //System.out.println(projetos.size());
    }
    
}
