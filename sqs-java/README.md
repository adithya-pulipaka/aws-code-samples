# sqs-java
This project include code samples for sending,receiving and manipulating messages with [Amazon SQS](https://aws.amazon.com/sqs/) using **AWS SDK for Java**.

## Setting up

###Credentials

- The credentials can be setup using one of the following ways.
    1. `AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY`  Environment variables
    2. `aws.accessKeyId` and `aws.secretKey` Java System Properties
    3. Credentials file at `~/.aws/credentials`
    
### Queue Setup

- Use AWS console to create a Queue and pass in the URL of the queue through system property `SQS_QUEUE_URL`     