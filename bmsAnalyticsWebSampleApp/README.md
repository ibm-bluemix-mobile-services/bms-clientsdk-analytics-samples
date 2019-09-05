# bms-analytics-clientsdk-sampleapp
Starter web app code  to connect with Bluemix Mobile Analytics  Service.

## IBM Bluemix Mobile Analytics Service Platform Web Sample Application
Use this sample application to get started with development of web applications.
The application uses the IBM Bluemix Mobile Analytics SDK to connect to a local or remote server.
 
**Note** 

**Getting Started**
1. To connect this App to IBM Mobile Analytics you need to have an instance of Mobile Analytics Service in Bluemix. To create your Mobile Analytics instance go [here](https://console.bluemix.net/catalog/services/mobile-analytics?env_id=ibm:yp:us-south&taxonomyNavigation=apps). After creating the instance you need to note down the `apiKey`  and  `instanceId` , which later needs to be provided for initialization of the websdk.
	* To get `apiKey` go under Service Credentials Section and then View Credential.
	* To get `instanceId` look into the url for the alphanumeric id upto /( or ?).
2. In this sample, the web-app is hosted using nodejs server. So you need node and npm preinstalled in your system. 
3. The client sdk is downloaded and kept in `client/bms-clientsdk-web-analytics` . To get the latest code download it from [here](https://github.com/ibm-bluemix-mobile-services/bms-clientsdk-web-analytics). 
4. To Initialize the web-app go to `client/js/index.js` file. Update the parameters 
	* `applicationName` is the unique id of your application that is shown in the dashboard. This applicationName unifies all the data sent from different platforms .
	* `apiKey` and `instanceId` , are the bluemix Mobile Analytics service instance specific keys noted before. 
	* `bmsregion` is the region in which service instance is created.
	* `deviceEvents` and `hasUserContext` are user requirement specific cofigurations . 
	For more information on these cofigurations visit Bluemix service instance `Getting Started` section.

5. After Configuration is done , run the server following the steps below. 
	* `cd node_server`
	* npm start
6. After the server starts , go to `http://localhost:9081/home/` to see the app UI.
