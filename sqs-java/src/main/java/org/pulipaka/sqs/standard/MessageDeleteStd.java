package org.pulipaka.sqs.standard;

import java.util.List;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteMessageResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;

/**
 * A Simple SQS Client to delete messages from a STANDARD queue using AWS SDK.
 * The message request receives a message and then deletes it using the receipt handle.
 * @author Adithya
 *
 */
public class MessageDeleteStd {

	public static void main(String[] args) {
		
		final AmazonSQS amazonSQS = AmazonSQSClientBuilder.defaultClient();
		
		final ReceiveMessageRequest request = new ReceiveMessageRequest()
						.withQueueUrl(System.getProperty("SQS_QUEUE_URL"));
		
		ReceiveMessageResult result = amazonSQS.receiveMessage(request);
		List<Message> messages = result.getMessages();
		System.out.println("Message count: " + messages.size());
		
		if(messages.size() > 0){
			final String receiptHandle = messages.get(0).getReceiptHandle();
			
			final DeleteMessageRequest deleteRequest = new DeleteMessageRequest()
					.withQueueUrl(System.getProperty("SQS_QUEUE_URL"))
					.withReceiptHandle(receiptHandle);
			
			System.out.println("Deleting message: " + messages.get(0).getMessageId());
			DeleteMessageResult deleteResult = amazonSQS.deleteMessage(deleteRequest);
			System.out.println("Message Deleted!");
			System.out.println("Http Status code for the response: " + deleteResult.getSdkHttpMetadata().getHttpStatusCode());
			System.out.println("Headers");
			System.out.println("---------------------------");
			deleteResult.getSdkHttpMetadata().getHttpHeaders()
			.forEach((key,value) 
					-> System.out.println("Key: " + key + ", Value: " + value));
			System.out.println("---------------------------");
			System.out.println("Message RequestID: " + deleteResult.getSdkResponseMetadata().getRequestId());
		}else{
			System.out.println("No messages in the Queue!");
		}
		
	}
}
