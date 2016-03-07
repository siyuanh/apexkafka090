/**
 * Put your copyright and license info here.
 */
package com.example.apexkafka090;


import org.apache.apex.malhar.kafka.KafkaMetrics;
import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.Context;
import com.datatorrent.api.DAG;
import com.datatorrent.api.DAG.Locality;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.lib.io.ConsoleOutputOperator;

@ApplicationAnnotation(name="KafkaInputDemo")
public class Application implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    // Sample DAG with 2 operators
    // Replace this code with the DAG you want to build


    KafkaExampleOperator kafkaInputOperatorExample = dag.addOperator("kafkainput", KafkaExampleOperator.class);
    kafkaInputOperatorExample.setClusters("localhost:9092");
    kafkaInputOperatorExample.setTopics("/user/mapr/mystream:topic0");
    kafkaInputOperatorExample.setStrategy("ONE_TO_MANY");


    ConsoleOutputOperator cons = dag.addOperator("console", new ConsoleOutputOperator());

    dag.addStream("OutputToConsole", kafkaInputOperatorExample.outputPort, cons.input).setLocality(Locality.CONTAINER_LOCAL);
    dag.setAttribute(kafkaInputOperatorExample, Context.OperatorContext.METRICS_AGGREGATOR, new KafkaMetrics.KafkaMetricsAggregator());
  }
}
