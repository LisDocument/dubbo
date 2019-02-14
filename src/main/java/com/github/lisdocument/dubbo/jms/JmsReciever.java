package com.github.lisdocument.dubbo.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsReciever {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://47.99.210.191:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            //第一个参数表示是否事务性会话
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //创建队列，有的话就不会创建
            //destination表示目的地
            Destination destination = session.createQueue("first-queue");
            //创建消息接受者
            MessageConsumer consumer = session.createConsumer(destination);

            TextMessage textMessage = (TextMessage) consumer.receive();
            System.out.println(textMessage.getText());
            //表示消息是够签收，如果没有commit 默认不签收消息
            session.commit();
            session.close();

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if(null != connection){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
