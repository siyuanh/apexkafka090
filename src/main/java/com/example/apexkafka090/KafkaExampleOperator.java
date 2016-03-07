package com.example.apexkafka090;

import java.util.Arrays;

import org.apache.apex.malhar.kafka.AbstractKafkaInputOperator;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import com.datatorrent.api.DefaultOutputPort;

/**
 * Created by siyuan on 1/11/16.
 */
public class KafkaExampleOperator extends AbstractKafkaInputOperator
{
  public final transient DefaultOutputPort<String> outputPort = new DefaultOutputPort();

  @Override
  protected void emitTuple(String s, ConsumerRecord<byte[], byte[]> consumerRecord)
  {
    outputPort.emit(new String(consumerRecord.value()));
  }

  @Override
  public void setTopics(String... topics)
  {
    super.setTopics(Lists.transform(Arrays.asList(topics), new Function<String, String>()
    {
      @Override
      public String apply(String o)
      {
        return "/user/mapr/mystream:" + o;
      }
    }).toArray(new String[]{}));
  }
}
