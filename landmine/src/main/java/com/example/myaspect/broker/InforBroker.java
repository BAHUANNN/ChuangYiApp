package com.example.myaspect.broker;

import com.example.myaspect.bean.InforBean;
import com.example.myaspect.consumer.InforConsumer;
import com.example.myaspect.producer.InforProducer;
import com.example.myaspect.storage.InforStorage;

public class InforBroker {
    InforStorage storage = new InforStorage();
    InforProducer producer = new InforProducer();
    InforConsumer consumer = new InforConsumer();
}
