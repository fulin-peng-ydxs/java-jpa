package jpa.main;


import jpa.entity.User;
import jpa.relationship.many.Project;
import jpa.relationship.one.many.Department;
import jpa.relationship.one.one.Employee;
import jpa.relationship.one.one.ParkingSpace;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class JpaTest {

    public static void main(String[] args) {
        EntityManager entityManager = getEntityManager();
        //开启事务：在JPA中，所有数据操作都应该放在事务中，这是为了确保数据的完整性和一致性。如果不在事务中进行数据操作，
        //则可能会导致数据不一致或丢失，因此建议始终将数据操作放在事务中。在JPA中，可以使用@Transactional注解来管理事务。
        entityManager.getTransaction().begin();
        User user = new User();
        user.setUserAge(90);
        user.setUserName("彭福临");
        user.setId(1);
        entityManager.persist(user);
        entityManager.getTransaction().commit();  //提交事务
        entityManager.close(); //关闭持久化管理器
    }

    private static EntityManager getEntityManager(){
        //创建持久化单元
        EntityManagerFactory myPersistenceUnit = Persistence.createEntityManagerFactory("myPersistenceUnit");
        //创建持久化管理器
        return myPersistenceUnit.createEntityManager();
    }


    @Test
    public void oneToOne(){
        EntityManager entityManager = getEntityManager();
        ParkingSpace parkingSpace = new ParkingSpace();
        parkingSpace.setLocation("0000000");
        Employee employee = new Employee();
        employee.setName("代号1");
        employee.setSalary(20L);
        employee.setParkingSpace(parkingSpace);
        entityManager.getTransaction().begin();
        //持久化实体
        entityManager.persist(parkingSpace);
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        //查询
        Employee employee1 = entityManager.find(Employee.class, 2);
        System.out.println(employee1);
        ParkingSpace parkingSpace1 = entityManager.find(ParkingSpace.class, 1);
        System.out.println(parkingSpace1);
    }


    @Test
    public void oneToMany(){
        EntityManager entityManager = getEntityManager();
        Employee employee = new Employee();
        employee.setName("e1");
        employee.setSalary(20L);
        Employee employee1 = new Employee();
        employee1.setName("e1");
        employee1.setSalary(20L);
        ParkingSpace parkingSpace = new ParkingSpace();
        parkingSpace.setLocation("0000000");
        employee.setParkingSpace(parkingSpace);
        entityManager.getTransaction().begin();
        Department department = new Department();
        department.setName("d1");
        //持久化实体
        entityManager.persist(parkingSpace);
        entityManager.persist(employee);
        employee.setDepartment(department);
        entityManager.persist(employee1);
        employee1.setDepartment(department);
        entityManager.persist(department);
        entityManager.getTransaction().commit();
        //查询
        Department findDepartment = entityManager.find(Department.class, 4);
        List<Employee> employees = findDepartment.getEmployees();
        System.out.println(employees);
    }


    @Test
    public void manyToMany(){
        EntityManager entityManager = getEntityManager();
        Employee employee = new Employee();
        employee.setName("e1");
        employee.setSalary(20L);
        Employee employee1 = new Employee();
        employee1.setName("e2");
        employee1.setSalary(20L);
        Project project = new Project();
        project.setName("p1");
        Project project1 = new Project();
        project1.setName("p2");
        employee.setProjects(Arrays.asList(project1,project));
        employee1.setProjects(employee.getProjects());
        project.setEmployees(Arrays.asList(employee,employee1));
        project1.setEmployees(project.getEmployees());
        entityManager.getTransaction().begin();
        //持久化
        entityManager.persist(employee);
        entityManager.persist(employee1);
        entityManager.persist(project);
        entityManager.persist(project1);
        entityManager.getTransaction().commit();
    }
}
