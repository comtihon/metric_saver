# Metrics Saver [![Build Status](https://travis-ci.org/comtihon/metric_saver.svg?branch=master)](https://travis-ci.org/comtihon/metric_saver)
Service for saving devices metrics and events from kafka to postgres database.

## Run
Ensure that [PostgreSql](https://www.postgresql.org/) and [Kafka](https://kafka.apache.org/) 
are accessible before running the service.  
Access urls are specified in application.properties `spring.kafka.bootstrap-servers` 
for kafka and `spring.datasource.url` for postgres.

### In docker

    sudo ./gradlew build buildDocker
    sudo docker run -t com.metric.saver

### In OS

    ./gradlew bootRun
    
## Testing
    
    ./gradlew check