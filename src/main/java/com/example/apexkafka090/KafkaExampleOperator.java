package com.example.apexkafka090;

import org.apache.apex.malhar.kafka.AbstractKafkaInputOperator;
import org.apache.kafka.clients.consumer.ConsumerRecord;

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
}
