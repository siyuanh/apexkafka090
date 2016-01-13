# New Kafka Input Operator Example

### Build 
```
$ git clone git@github.com:siyuanh/apexkafka090.git
$ mvn clean install -DskipTests
```
### Create new topic
```sh
./kafka-topics --zookeeper zookeepernode:2181/confluent2 --create --topic testtopic --replication-factor 2 --partition 3
```

### Produce some data
```sh
./kafka-producer-perf-test --topic localtest3 --throughput 10 --num-records 100 --record-size 100 --producer-props bootstrap.servers=node0:9092,node1:9092,node2:9092
```
You can set num-records to big number to keep it running


### Run Application

Some properties you might set before run
```sh
dt.application.KafkaInputDemo.operator.kafkainput.clusters = node0:9092,node1:9092,node2:9092
dt.application.KafkaInputDemo.operator.kafkainput.topics = testtopic1,testtopic2
dt.application.KafkaInputDemo.operator.kafkainput.strategy = one_to_one|one_to_many
dt.application.KafkaInputDemo.operator.kafkainput.initialPartitionCount = 2
dt.application.KafkaInputDemo.operator.kafkainput.initialOffset = EARLIEST|LATEST|APPLICATION_OR_EARLIEST|APPLICATION_OR_LATEST

```
