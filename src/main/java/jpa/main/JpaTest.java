package jpa.main;


import jpa.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaTest {

    public static void main(String[] args) {
        //创建持久化单元
        EntityManagerFactory myPersistenceUnit =
                Persistence.createEntityManagerFactory("myPersistenceUnit");
        //创建持久化管理器
        EntityManager entityManager = myPersistenceUnit.createEntityManager();
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
}
