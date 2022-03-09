package com.javatpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
public class Test {
    public static void main(String[] args) {
        StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta=new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory=meta.getSessionFactoryBuilder().build();
        Session session=factory.openSession();
        //saveData(session);
        getData(session);
    }
    private static void getData(Session session) {
        final List<Question> list = session.createQuery("FROM com.javatpoint.Question q", Question.class).list();
        Transaction t= session.beginTransaction();
         if(list != null && !list.isEmpty()) {
             final Question question = list.get(0);
             final List<Answer> answers = question.getAnswers();
             final List<Answer> changed = new ArrayList<>();
             for (Answer a : answers) {
                 a.setPostedBy("VINOD");
                 changed.add(a);
             }
             question.setAnswers(changed);
             session.save(question);
         }
        t.commit();
        session.close();
    }

    private static void saveData1(Session session) {
        Transaction t= session.beginTransaction();

        Answer ans1=new Answer();
        ans1.setAnswername("VVVV");
        ans1.setPostedBy("Ravi Malik");

        Answer ans2=new Answer();
        ans2.setAnswername("RRRR");
        ans2.setPostedBy("Sudhir Kumar");


        ArrayList<Answer> list1=new ArrayList<Answer>();
        list1.add(ans1);
        list1.add(ans2);


        Question question1=new Question();
        question1.setQname("Who am I ?");
        question1.setAnswers(list1);


        session.persist(question1);


        t.commit();
        session.close();
        System.out.println("success");
    }
    private static void saveData(Session session) {
        Transaction t= session.beginTransaction();

        Answer ans1=new Answer();
        ans1.setAnswername("Java is a programming language");
        ans1.setPostedBy("Ravi Malik");

        Answer ans2=new Answer();
        ans2.setAnswername("Java is a platform");
        ans2.setPostedBy("Sudhir Kumar");

        Answer ans3=new Answer();
        ans3.setAnswername("Servlet is an Interface");
        ans3.setPostedBy("Jai Kumar");

        Answer ans4=new Answer();
        ans4.setAnswername("Servlet is an API");
        ans4.setPostedBy("Arun");

        ArrayList<Answer> list1=new ArrayList<Answer>();
        list1.add(ans1);
        list1.add(ans2);

        ArrayList<Answer> list2=new ArrayList<Answer>();
        list2.add(ans3);
        list2.add(ans4);

        Question question1=new Question();
        question1.setQname("What is Java?");
        question1.setAnswers(list1);

        Question question2=new Question();
        question2.setQname("What is Servlet?");
        question2.setAnswers(list2);

        session.persist(question1);
        session.persist(question2);

        t.commit();
        session.close();
        System.out.println("success");
    }
}
