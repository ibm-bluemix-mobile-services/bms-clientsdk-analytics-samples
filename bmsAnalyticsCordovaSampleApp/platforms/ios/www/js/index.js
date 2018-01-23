/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
var app = {
    // Application Constructor
initialize: function() {
    document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
},
    
    // deviceready Event Handler
    //
    // Bind any cordova events here. Common events are:
    // 'pause', 'resume', etc.
onDeviceReady: function() {
    BMSClient.initialize(BMSClient.REGION_US_SOUTH);
    var applicationName = "CordovaHelloWorld";
    var apiKey =  "5c1e2450-e595-420e-9821-05ad99576cc1";
    var hasUserContext = true;
    var deviceEvents = [BMSAnalytics.ALL];
    BMSClient.initialize(BMSClient.REGION_US_SOUTH); //Make sure you point to your region
    var collectLocation=true;
    BMSAnalytics.initialize(applicationName, apiKey, hasUserContext, collectLocation, deviceEvents);
    console.log('Bluemix Mobile Analytics initialized');
    
    var logger = BMSLogger.getLogger("logger");
    
    var buttonElement0 = document.getElementById("ping");
    var textElement0 = document.getElementById("userIdentity");
    var buttonElement1 = document.getElementById("setUsrContext");
    
    var buttonElement2 = document.getElementById("disableLogger");
    var buttonElement3 = document.getElementById("enableLogger");
    var buttonElement4 = document.getElementById("isEnabled");
    
    var buttonElement5 = document.getElementById("getLogLevel");
    var textElement1 = document.getElementById("loglevelset");
    var buttonElement6 = document.getElementById("setLogLevel");
    
    var textElement2 = document.getElementById("logText");
    var textElement3 = document.getElementById("logTextLevel");
    var buttonElement7 = document.getElementById("sendLog");
    
    var buttonElement8 = document.getElementById("enableAnalytics");
    var buttonElement9 = document.getElementById("disableAnalytics");
    var buttonElement10 = document.getElementById("sendAnalytics");
    
    var textElement4 = document.getElementById("analyticscustomlog");
    var buttonElement11 = document.getElementById("customLogAnalytics");
    
    var buttonElement12 = document.getElementById("getlogStoreSize");
    var textElement5 = document.getElementById("logStoreSize");
    var buttonElement13 = document.getElementById("setlogStoreSize");
    
    buttonElement0.style.display ="block";
    buttonElement1.style.display="block";
    buttonElement2.style.display ="block";
    buttonElement3.style.display="block";
    buttonElement4.style.display ="block";
    buttonElement5.style.display="block";
    buttonElement6.style.display ="block";
    buttonElement7.style.display="block";
    buttonElement8.style.display ="block";
    buttonElement9.style.display="block";
    buttonElement10.style.display ="block";
    buttonElement11.style.display="block";
    buttonElement12.style.display ="block";
    buttonElement13.style.display="block";
    
    //BMSAnalytics.send();
    BMSAnalytics.enable();
    
    
    //BMSLogger.setMaxLogStoreSize(10000);
    
    
    buttonElement0.addEventListener('click', app.testServerConnection, false);
    
    buttonElement1.addEventListener('click',app.setUserContext,false);
    
    buttonElement2.addEventListener('click',app.disableLogger,false);
    
    buttonElement3.addEventListener('click',app.enableLogger,false);
    
    buttonElement4.addEventListener('click',app.isEnabled,false);
    
    buttonElement5.addEventListener('click',app.getLogLevel,false);
    
    buttonElement6.addEventListener('click',app.setLogLevel,false);
    
    buttonElement7.addEventListener('click',app.sendLog,false);
    
    buttonElement8.addEventListener('click',app.enableAnalytics,false);
    
    buttonElement9.addEventListener('click',app.disableAnalytics,false);
    
    buttonElement10.addEventListener('click',app.sendAnalytics,false);
    
    buttonElement11.addEventListener('click',app.customLogAnalytics,false);
    
    buttonElement12.addEventListener('click',app.getlogStoreSize,false);
    
    buttonElement13.addEventListener('click',app.setlogStoreSize,false);
    
    //this.receivedEvent('deviceready');
},
    
    
    "testServerConnection": function testServerConnection() {
        var titleText = document.getElementById("main_title");
        var statusText = document.getElementById("main_status");
        var infoText = document.getElementById("main_info");
        titleText.innerHTML = "Hello Bluemix Mobile Analytics";
        statusText.innerHTML = "Connecting to Server...";
        infoText.innerHTML = "";
        console.log('testServerConnection');
        BMSAnalytics.log({'customLog':'testServerConnection1'});
        BMSAnalytics.send(
                          function(response) {
                          console.log('success: ' + response);
                          },
                          function (err) {
                          console.log('fail: ' + err);
                          });
        setTimeout(function(){
                   statusText.innerHTML = "";
                   }, 2000);
    },
    
    "setUserContext": function setUserContext() {
        var titleText = document.getElementById("main_title");
        var statusText = document.getElementById("main_status");
        var infoText = document.getElementById("main_info");
        
        titleText.innerHTML = "Hello Bluemix Mobile Analytics";
        infoText.innerHTML = "";
        BMSAnalytics.setUserIdentity(document.getElementById("userIdentity").value);
        BMSAnalytics.send();
        statusText.innerHTML = "User Identity Set..with userId "+document.getElementById("userIdentity").value;
        setTimeout(function(){
                   statusText.innerHTML = "";
                   }, 2000);
    },
    
    "disableLogger": function disableLogger() {
        var titleText = document.getElementById("main_title");
        var statusText = document.getElementById("main_status");
        var infoText = document.getElementById("main_info");
        
        statusText.innerHTML = "Log Capturing is disabled...";
        infoText.innerHTML = "";
        console.log('disableLogger');
        BMSLogger.storeLogs(false);
        setTimeout(function(){
                   statusText.innerHTML = "";
                   }, 2000);
        
    },
    
    "enableLogger": function enableLogger() {
        var titleText = document.getElementById("main_title");
        var statusText = document.getElementById("main_status");
        var infoText = document.getElementById("main_info");
        statusText.innerHTML = "Logs Capturing is enabled...";
        infoText.innerHTML = "";
        console.log('enableLogger');
        BMSLogger.storeLogs(true);
        setTimeout(function(){
                   statusText.innerHTML = "";
                   }, 2000);
        
    },
    
    
    "isEnabled":function isEnabled() {
        var titleText = document.getElementById("main_title");
        var statusText = document.getElementById("main_status");
        var infoText = document.getElementById("main_info");
        statusText.innerHTML = "Check if Log Capturing enabled... ";
        infoText.innerHTML = "";
        BMSLogger.isStoringLogs(  function(success) {
                                if(success) {statusText.innerHTML = "Log Capturing is enabled... ";}
                                else {statusText.innerHTML = "Log Capturing is disabled... ";}
                                
                                },
                                function (failure) {
                                statusText.innerHTML = "Log Capturing unknown ";
                                });
        
        setTimeout(function(){
                   statusText.innerHTML = "";
                   }, 2000);
        
        
    },
    
    "getLogLevel":function()  {
        var titleText = document.getElementById("main_title");
        var statusText = document.getElementById("main_status");
        var infoText = document.getElementById("main_info");
        
        BMSLogger.getLogLevel(function(success) {
                              statusText.innerHTML ="Current Logging Level "+success;
                              },
                              function (failure) {
                              statusText.innerHTML = "Failed to get log level ";
                              });
//        setTimeout(function(){
//                   statusText.innerHTML = "";
//                   }, 2000);
//        
        
        
        BMSAnalytics.logLocation();
        
        setTimeout(function(){
                   statusText.innerHTML = "";
                   }, 2000);
    },
    
    "setLogLevel":function() {
        var titleText = document.getElementById("main_title");
        var statusText = document.getElementById("main_status");
        var infoText = document.getElementById("main_info");
        
        var ll=document.getElementById("loglevelset").value;
        
        if(ll=="DEBUG" || ll=="debug")
        {
            BMSLogger.setLogLevel(BMSLogger.DEBUG);
        }
        else if(ll=="INFO" || ll=="info")
        {
            BMSLogger.setLogLevel(BMSLogger.INFO);
        }
        else if(ll=="WARN" || ll=="warn")
        {
            BMSLogger.setLogLevel(BMSLogger.WARN);
        }
        else if(ll=="ERROR" || ll=="error")
        {
            BMSLogger.setLogLevel(BMSLogger.ERROR);
        }
        else if(ll=="FATAL" || ll=="fatal")
        {
            BMSLogger.setLogLevel(BMSLogger.FATAL);
        }
        else
        {
            statusText.innerHTML = "Log Level not valid";
        }
        
        statusText.innerHTML ="Setting Logging Level "+document.getElementById("loglevelset").value;
        //BMSLogger.setLogLevel(document.getElementById("loglevelset").value);
        
        setTimeout(function(){
                   statusText.innerHTML = "";
                   }, 2000);
        
    },
    
    "sendLog": function sendLog() {
        var titleText = document.getElementById("main_title");
        var statusText = document.getElementById("main_status");
        var infoText = document.getElementById("main_info");
        var logger = BMSLogger.getLogger("logger");
        
        statusText.innerHTML = "Logging with some verbosity level ...";
        infoText.innerHTML = "";
        
        var ll=document.getElementById("logTextLevel").value;
        var lt=document.getElementById("logText").value;
        
        if(ll=="DEBUG" || ll=="debug")
        {
            logger.debug(lt);
        }
        else if(ll=="INFO" || ll=="info")
        {
            logger.info(lt);
        }
        else if(ll=="WARN" || ll=="warn")
        {
            logger.warn(lt);
        }
        else if(ll=="ERROR" || ll=="error")
        {
            logger.error(lt);
        }
        else if(ll=="FATAL" || ll=="fatal")
        {
            logger.fatal(lt);
        }
        else
        {
            statusText.innerHTML = "Logging Verbose Level not valid";
        }
        
        BMSLogger.send();
        setTimeout(function(){
                   statusText.innerHTML = "";
                   }, 2000);
        
    },
    
    "enableAnalytics":function enableAnalytics() {
        var titleText = document.getElementById("main_title");
        var statusText = document.getElementById("main_status");
        var infoText = document.getElementById("main_info");
        
        statusText.innerHTML = "Enabling Analytics Log Storage ...";
        infoText.innerHTML = "";
        
        BMSAnalytics.enable();
        
        setTimeout(function(){
                   statusText.innerHTML = " Analytics Log Storage Enabled...";
                   }, 2000);
        
        
        setTimeout(function(){
                   statusText.innerHTML = "";
                   }, 2000);
        
    },
    
    "disableAnalytics":function disableAnalytics() {
        var titleText = document.getElementById("main_title");
        var statusText = document.getElementById("main_status");
        var infoText = document.getElementById("main_info");
        
        statusText.innerHTML = "Disabling Analytics Log Storage ...";
        infoText.innerHTML = "";
        
        BMSAnalytics.disable();
        
        setTimeout(function(){
                   statusText.innerHTML = " Analytics Log Storage Disabled...";
                   }, 2000);
        
        
        setTimeout(function(){
                   statusText.innerHTML = "";
                   }, 2000);
        
    },
    
    "sendAnalytics":function sendAnalytics() {
        var titleText = document.getElementById("main_title");
        var statusText = document.getElementById("main_status");
        var infoText = document.getElementById("main_info");
        
        statusText.innerHTML = "Sending Analytics Logs  ...";
        infoText.innerHTML = "";
        
        BMSAnalytics.send();
        
        setTimeout(function(){
                   statusText.innerHTML = " Analytics Log Sent. ";
                   }, 2000);
        
        
        setTimeout(function(){
                   statusText.innerHTML = "";
                   }, 2000);
        
    },
    
    "customLogAnalytics":function customLogAnalytics() {
        var titleText = document.getElementById("main_title");
        var statusText = document.getElementById("main_status");
        var infoText = document.getElementById("main_info");
        
        statusText.innerHTML = "Sending Analytics Custom Log  ...";
        infoText.innerHTML = "";
        
        BMSAnalytics.log(document.getElementById("analyticscustomlog").value);
        
        setTimeout(function(){
                   statusText.innerHTML = "";
                   }, 2000);
        
    },
    
    "getlogStoreSize":function getlogStoreSize() {
        var titleText = document.getElementById("main_title");
        var statusText = document.getElementById("main_status");
        var infoText = document.getElementById("main_info");
        
        BMSLogger.getMaxLogStoreSize(function(success) {
                                     statusText.innerHTML ="Current LogStore Max Size "+success;
                                     },
                                     function (failure) {
                                     statusText.innerHTML = "Failed to get LogStore Max Size ";
                                     });
        infoText.innerHTML = "";
        
        setTimeout(function(){
                   statusText.innerHTML = "";
                   }, 2000);
        
    },
    
    "setlogStoreSize":function setlogStoreSize() {
        var titleText = document.getElementById("main_title");
        var statusText = document.getElementById("main_status");
        var infoText = document.getElementById("main_info");
        
        statusText.innerHTML = "Setting Max Size of Log Store ...";
        infoText.innerHTML = "";
        
        var maxlogsize=parseInt(document.getElementById("logStoreSize").value);
        BMSLogger.setMaxLogStoreSize(maxlogsize);
        
        statusText.innerHTML = "Max Size of Log Store set to "+ document.getElementById("logStoreSize").value;
        
        setTimeout(function(){
                   statusText.innerHTML = "";
                   }, 2000);
        
        
    }
    
    
};

app.initialize();




