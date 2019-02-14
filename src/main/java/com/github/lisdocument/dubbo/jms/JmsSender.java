package com.github.lisdocument.dubbo.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author bin
 */
public class JmsSender {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://47.99.210.191:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            //创建队列，有的话就不会创建
            //destination表示目的地
            Destination destination = session.createQueue("first-queue");
            //创建消息发送者
            MessageProducer producer = session.createProducer(destination);

            TextMessage textMessage = session.createTextMessage("1111111");
            producer.send(textMessage);
            //只有在commit之后消息才会发送，类似于事务提交机制
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
