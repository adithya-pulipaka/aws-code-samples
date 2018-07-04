package org.pulipaka.sqs.standard;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

/**
 * A Simple SQS Client to send messages to a STANDARD queue using AWS SDK.
 * Refer {@link README.me} for details on setting up and accessing the credentials and Queue details.
 * @author Adithya
 *
 */
public class MessageSenderStd {
	
	public static void main(String[] args) {
		
		final AmazonSQS amazonSQS = AmazonSQSClientBuilder.defaultClient();
		
		final SendMessageRequest request = new SendMessageRequest()
					.withQueueUrl(System.getProperty("SQS_QUEUE_URL"))
					.withMessageBody("Hello Amazon SQS from MessageSenderStd!");
		
		SendMessageResult result = amazonSQS.sendMessage(request);
		System.out.println("Message ID: " + result.getMessageId());
		System.out.println("MD5 of Message Body: " + result.getMD5OfMessageBody());
	}

}
