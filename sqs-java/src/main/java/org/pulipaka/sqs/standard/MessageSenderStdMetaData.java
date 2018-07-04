package org.pulipaka.sqs.standard;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

/**
 * A Simple SQS Client to send messages to a STANDARD queue using AWS SDK.
 * The response contains more information in addition to the message details including 
 * the Headers and Status code and Message Request ID that AWS assigns to every request.
 * @author Adithya
 *
 */
public class MessageSenderStdMetaData {
	
	public static void main(String[] args) {
		
		final AmazonSQS amazonSQS = AmazonSQSClientBuilder.defaultClient();
		
		final SendMessageRequest request = new SendMessageRequest()
					.withQueueUrl(System.getProperty("SQS_QUEUE_URL"))
					.withMessageBody("Hello Amazon SQS from MessageSenderStdMetaData!");
		
		SendMessageResult result = amazonSQS.sendMessage(request);
		System.out.println("Http Status code for the response: " + result.getSdkHttpMetadata().getHttpStatusCode());
		System.out.println("Headers");
		System.out.println("---------------------------");
		result.getSdkHttpMetadata().getHttpHeaders()
				.forEach((key,value) 
						-> System.out.println("Key: " + key + ", Value: " + value));
		System.out.println("---------------------------");
		System.out.println("Message RequestID: " + result.getSdkResponseMetadata().getRequestId());
	}

}
