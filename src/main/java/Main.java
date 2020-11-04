import dao.DisciplineDAO;
import dao.RoleDAO;
import dao.TaskDAO;
import dao.UserDAO;
import entities.Discipline;
import entities.Role;
import entities.Task;
import entities.User;
import entities.enums.DisciplineTypes;
import entities.enums.StatusTypes;

import java.sql.Date;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws SQLException {
        RoleDAO roleDAO = new RoleDAO();
        TaskDAO taskDAO = new TaskDAO();
        DisciplineDAO disciplineDAO = new DisciplineDAO();
        UserDAO userDAO = new UserDAO();
        List<Discipline> disciplineList = addDiscipline();
        Set<Role> roleSet = addRole();
        List<Task> taskList = addTask();
        List<User> userListAM = addAMUsers(taskList, roleSet.stream().filter(t -> t.getName().equals("Intern")).collect(Collectors.toSet()), disciplineList.get(1));
        List<User> userListDEV = addDEVUsers(taskList, roleSet.stream().filter(t -> t.getName().equals("Intern")).collect(Collectors.toSet()), disciplineList.get(0));
        List<User> userListTEST = addTESTUsers(taskList, roleSet.stream().filter(t -> t.getName().equals("Intern")).collect(Collectors.toSet()), disciplineList.get(2));

        for (Role role : roleSet) {
            roleDAO.addToDatabase(role);
        }
        for (Task task : taskList) {
            taskDAO.addToDatabase(task);
        }
        for (Discipline discipline : disciplineList) {
            disciplineDAO.addToDatabase(discipline);
        }

        for (User user : userListAM) {
            userDAO.addToDatabase(user);
        }
        for (User user : userListDEV) {
            userDAO.addToDatabase(user);
        }
        for (User user : userListTEST) {
            userDAO.addToDatabase(user);
        }
        disciplineList.get(1).setHeadOfDiscipline(userListAM.get(0));
        disciplineDAO.update(disciplineList.get(1));
        disciplineList.get(0).setHeadOfDiscipline(userListDEV.get(1));
        disciplineDAO.update(disciplineList.get(0));
        disciplineList.get(2).setHeadOfDiscipline(userListTEST.get(2));
        disciplineDAO.update(disciplineList.get(2));
        System.out.println(userDAO.listAllUsersByRole("Intern"));
        userDAO.closeTransactionSession();
        System.out.println(userDAO.listAllUsersByDiscipline(DisciplineTypes.AM));
        userDAO.closeTransactionSession();
        System.out.println(userDAO.listAllUsersByTaskInTODO(StatusTypes.TODO));
        userDAO.closeTransactionSession();
        System.out.println(disciplineDAO.listAllDisciplines(2));
        disciplineDAO.closeTransactionSession();
        userDAO.delete(userListDEV.get(1));


    }

    public static List<User> addAMUsers(List<Task> taskList, Set<Role> roleSet, Discipline discipline) {
        return Arrays.asList(new User("Vadim", "Besliu", "asd@gmail.com",
                        "vbesliu", new Date(2020, 12, 11), true, roleSet, taskList, discipline),
                new User("Sorin", "Gorea", "sgorea@gmail.com", "sgorea", new Date(2020, 12,
                        10), true, roleSet, taskList, discipline));
    }

    public static List<User> addDEVUsers(List<Task> taskList, Set<Role> roleSet, Discipline discipline) {
        return Arrays.asList(new User("Dan", "Velescu", "dvelescu@gmail.com",
                        "dvelescu", new Date(2020, 12, 11), true, roleSet, taskList, discipline),
                new User("Nicolae", "Semitar", "nsemitar@gmail.com", "nsemitar", new Date(2020, 12, 10),
                        true, roleSet, taskList, discipline));
    }

    public static List<User> addTESTUsers(List<Task> taskList, Set<Role> roleSet, Discipline discipline) {
        return Arrays.asList(new User("Dmitrii", "Sprinceac", "dsprinceac@gmail.com", "dsprinceac",
                        new Date(2020, 12, 11), true, roleSet, taskList, discipline),
                new User("Valeria", "Jucov", "vjucov@gmail.com", "vjucov",
                        new Date(2020, 12, 10), true, roleSet, taskList, discipline),
                new User("Teimur", "Delimuhametov", "tdelimuhametov@gmail.com", "tDelimuhametov",
                        new Date(2020, 12, 10),
                        true, roleSet, taskList, discipline));
    }

    public static Set<Role> addRole() {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role("Admin"));
        roleSet.add(new Role("Intern"));
        roleSet.add(new Role("Mentor"));
        return roleSet;
    }

    public static List<Task> addTask() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("Debug", "Debuging", new Date(2020, 12, 11),
                new Date(2020, 12, 11), StatusTypes.TODO));
        taskList.add(new Task("Test", "Testing", new Date(2020, 11, 30),
                new Date(2020, 12, 11), StatusTypes.PROGRESS));
        return taskList;
    }

    public static List<Discipline> addDiscipline() {
        List<Discipline> disciplinesList = new ArrayList<>();
        disciplinesList.add(new Discipline(DisciplineTypes.DEV));
        disciplinesList.add(new Discipline(DisciplineTypes.AM));
        disciplinesList.add(new Discipline(DisciplineTypes.TEST));
        return disciplinesList;
    }


}
