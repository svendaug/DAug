# **Traffic Sign Recognition** 

## Writeup

### You can use this file as a template for your writeup if you want to submit it as a markdown file, but feel free to use some other method and submit a pdf if you prefer.

---

**Build a Traffic Sign Recognition Project**

The goals / steps of this project are the following:
* Load the data set (see below for links to the project data set)
* Explore, summarize and visualize the data set
* Design, train and test a model architecture
* Use the model to make predictions on new images
* Analyze the softmax probabilities of the new images
* Summarize the results with a written report


[//]: # (Image References)

[image1]: ./histogram.png "Histogram"
[image2]: ./Visualisation.jpg "Visualisation"
[image3]: ./YUV_example.png "YUV example"


## Rubric Points
### Here I will consider the [rubric points](https://review.udacity.com/#!/rubrics/481/view) individually and describe how I addressed each point in my implementation.  

---
### Writeup / README

#### 1. Provide a Writeup / README that includes all the rubric points and how you addressed each one. You can submit your writeup as markdown or pdf. You can use this template as a guide for writing the report. The submission includes the project code.

You're reading it! 

### Data Set Summary & Exploration

#### 1. Provide a basic summary of the data set. In the code, the analysis should be done using python, numpy and/or pandas methods rather than hardcoding results manually.

I used the pandas library to calculate summary statistics of the traffic
signs data set:

* The size of training set is 34,799
* The size of the validation set is 4,410
* The size of test set is 12,630
* The shape of a traffic sign image is (32, 32, 3)
* The number of unique classes/labels in the data set is 43

#### 2. Include an exploratory visualization of the dataset.

Here is an exploratory visualization of the data set. It is a bar chart showing how the data ...

![Histogram](histogram.png)


### Design and Test a Model Architecture

#### 1. Describe how you preprocessed the image data. What techniques were chosen and why did you choose these techniques? Consider including images showing the output of each preprocessing technique. Pre-processing refers to techniques such as converting to grayscale, normalization, etc. (OPTIONAL: As described in the "Stand Out Suggestions" part of the rubric, if you generated additional data for training, describe why you decided to generate additional data, how you generated the data, and provide example images of the additional data. Then describe the characteristics of the augmented training set like number of images in the set, number of images for each class, etc.)

I tried the following pre-processing techniques
1. no pre-processing
2. normalised using (pixel-128)/128 technique
3. Greyscale only
4. Greyscale + normalisation using (2)
5. Greyscale + global and local normalisation (pixel-mean)/stddev
6. Using YUV colour space + normalisation. I used the YUV colour space as this was proposed in LeCun's paper. THe paper also mentioned that the Y channel was most important. IN the paper they used less weights for the U and V channel. I couldn't quite figure out how to do that, so I only used the Y channel.

Here are the validation accuracies for each of the different techniques.

|Pre-processing Technique| Validation Accuracy|
-------------------------------------------------
| 1 -> 86%
2 -> <60%
3 -> 87%
4-> 80%
5-> 93%
6 -> 94%

I really wanted to augment my training data set to add more pictures to 
1. have equal number of pictures and
2. introduce perturbations like LeCun's paper described i.e rotations and scaling of pictures
Unfortunately, I couldn't find the time to implement this. I had a baby on Saturday (9th Dec) and my entire weekend has been wiped out!

#### 2. Describe what your final model architecture looks like including model type, layers, layer sizes, connectivity, etc.) Consider including a diagram and/or table describing the final model.

My final model was the same LeNet model from the lab earlier. The only addition was adding dropouts (50% probability) consisted of the following layers:

| Layer         		|     Description	        					| 
|:---------------------:|:---------------------------------------------:| 
| Input         		| 32x32x1 Greyscale image						| 
| Convolution 5x5     	| 1x1 stride, same padding, outputs 28x28x6 	|
| RELU					|												|
| Max pooling	      	| 2x2 stride,  outputs 14x14x6 				|
| Dropout				| 50%											|
| Convolution 5x5     	| 1x1 stride, same padding, outputs 10x10x16 	|
| RELU					|												|
| Max pooling	      	| 2x2 stride,  outputs 5x5x16 				|
| Dropout				| 50%											|
| Fully connected		| Outputs 120 									|
| RELU					|												|
| Fully connected		| Outputs 84 									|
| RELU					|												|
| Fully connected		| Outputs 43 									|
| RELU					|												|
| Softmax				|         										|
|						|												|
|						|												|
 


#### 3. Describe how you trained your model. The discussion can include the type of optimizer, the batch size, number of epochs and any hyperparameters such as learning rate.

To train the model, I used the hyperparameters set up in the LeNet Lab i.e Adam optimiser, batch size of 128, epochs 10, learning rate - 0.001)
The pre-processing of images and minor modification to the architecture bumped the validation accuracy to above 0.93. So I didn't play around with the hyperparameters. In the 10 epochs, you could see the validation accuracy converging, so I don't think increasing epochs is necessary.

#### 4. Describe the approach taken for finding a solution and getting the validation set accuracy to be at least 0.93. Include in the discussion the results on the training, validation and test sets and where in the code these were calculated. Your approach may have been an iterative process, in which case, outline the steps you took to get to the final solution and why you chose those steps. Perhaps your solution involved an already well known implementation or architecture. In this case, discuss why you think the architecture is suitable for the current problem.

My final model results were:
* training set accuracy of 98.8%
* validation set accuracy of 94.7%
* test set accuracy of 93.1%


The first architecture I tried was the basic LeNet architecture. With the pre-processing of images, the validation accuracy reached close to 93%. I added dropouts as that was a very simple addition to the architecture and that increased the accuracy to above 93%.
Ideally, I would have also liked to implement some of the more advanced concepts like 1x1 convolution and inception. I looked at the Googlenet architecture but that seemed quite complicated to implement and I ran out of time. I will try and do that in the future.

The weights and biases were initialised at random with zero mean and small standard deviation.
 

### Test a Model on New Images

#### 1. Choose five German traffic signs found on the web and provide them in the report. For each image, discuss what quality or qualities might be difficult to classify.

Here are five German traffic signs that I found on the web:

![Priority Road](priorityroad-class12.jpg) ![Speed Limit 30](speedlimit30-class2.jpg) ![Speed Limit 80](speedlimit80-class5.jpg) 
![Stop Sign](Stopsign-class14.jpg) ![alt text][image8]

I chose these images as they were a fair few images of these classes in the training set. So I thought the model should be good at predicting these images. But some of these images have copyright labels on the image, at angles and not the only element in the image. For example the priority road sign had clouds and 80km/hr speed limit had roads and other features.

#### 2. Discuss the model's predictions on these new traffic signs and compare the results to predicting on the test set. At a minimum, discuss what the predictions were, the accuracy on these new predictions, and compare the accuracy to the accuracy on the test set (OPTIONAL: Discuss the results in more detail as described in the "Stand Out Suggestions" part of the rubric).

Here are the results of the prediction:

| Image			        |     Prediction	        					| 
|:---------------------:|:---------------------------------------------:| 
| Stop Sign      		| Stop sign   									| 
| Priority     			| Priority 										|
| Yield					| End of all speed and passing limits			|
| 30 km/h	      		| 30 km/h						 				|
| 80 km/h				| Right-of-way at the next intersection			|


The model was able to correctly guess 3 of the 5 traffic signs, which gives an accuracy of 60%. This was almost expected. Both the 80km/h speed limit sign and the yield sign had other features (clouds, roads) and they were only a part of the whole image. I think cropping the images would have improved the accuracy. 

#### 3. Describe how certain the model is when predicting on each of the five new images by looking at the softmax probabilities for each prediction. Provide the top 5 softmax probabilities for each image along with the sign type of each probability. (OPTIONAL: as described in the "Stand Out Suggestions" part of the rubric, visualizations can also be provided such as bar charts)

The code for making predictions on my final model is located in the 77th cell of the Ipython notebook.

For the first image, the model is relatively sure that this is a priority road sign (probability of 0.99999), and the model is correct. The top five soft max probabilities were

| Probability         	|     Prediction	        					| 
|:---------------------:|:---------------------------------------------:| 
| 0.99999      			| Priority Road									| 
| 1.69e-6  				| Roundabout mandatory							|
| 4.04e-8				| End of all speed and passing limits			|
| 3.52e-10     			| Yield							 				|
| 1.65e-10			    | No Vehicles	      							|


For the second image, the model is relatively sure that this is a Speed Limit (30km/h) sign (probability of 0.9647), and the model is correct. It is also good to see The top five soft max probabilities were

| Probability         	|     Prediction	        					| 
|:---------------------:|:---------------------------------------------:| 
| 0.9647      			| Speed Limit 30km/h							| 
| 0.0241  				| Speed Limit 70km/h							|
| 0.0079				| Speed Limit 20km/h							|
| 3.08e-3     			| Speed Limit 50km/h			 				|
| 4.17e-5			    | Stop											|

For the third image, the model is fairly sure that this is a right-of-way at the next intersection sign (probability of 0.9291), and the model is wrong. This is expected - the 80 sign is a small part of the overall image and it is also at an angle which the model hasn't been trained for. The top five soft max probabilities were

| Probability         	|     Prediction	        					| 
|:---------------------:|:---------------------------------------------:| 
| 0.9291      			| Right-of-way at the next intersection			| 
| 2.77e-2  				| Beware of ice/snow							|
| 2.55e-2				| Roundabout mandatory							|
| 1.41e-2     			| Double curve					 				|
| 1.83e-3			    | Speed Limit 30km/h  							|

For the fourth image, the model is relatively sure that this is a Stop sign (probability of 0.98), and the model is correct. The top five soft max probabilities were

| Probability         	|     Prediction	        					| 
|:---------------------:|:---------------------------------------------:| 
| 0.9790      			| Stop											| 
| 0.0094  				| Turn left ahead								|
| 0.0065				| Turn right ahead 								|
| 0.0012     			| Keep right					 				|
| 0.0009			    | Speed Limit (70km/h) 							|

For the fifth image, the model is not at all sure what the sign is. It predicts a End of all speed and passing limits sign (probability of 0.4696), and the model is wrong. This was expected as the image has clouds. Again cropping the image might have helped. The top five soft max probabilities were

| Probability         	|     Prediction	        					| 
|:---------------------:|:---------------------------------------------:| 
| 0.4696      			| End of all speed and passing limits			| 
| 0.1219  				| End of speed limit (80km/h)					|
| 0.1024				| Go straight or right							|
| 0.0889     			| End of no passing				 				|
| 0.0465			    | Yield											|


### (Optional) Visualizing the Neural Network (See Step 4 of the Ipython notebook for more details)
#### 1. Discuss the visual output of your trained network's feature maps. What characteristics did the neural network use to make classifications?


