# wm-http-interceptor

Simple Eclipse java project defining sample HTTP inbound and outbound interceptors for the webMethods Integration Server.

## Importing project into Eclipse

1. Open Eclipse or Software AG Designer
1. Switch to the Java perspective
1. Go to File > Import...
1. Choose Projects from Git... and click Next
1. Choose Clone URI and click Next
1. Paste https://github.com/nibltech/wm-http-interceptor into the URI field and click Next

## Create the WEBMETHODS_HOME build path variable

The project requires the wm-isclient.jar from the Software AG installation in order to compile. This JAR is defined in the project's build path via a classpath variable, WEBMETHODS_HOME, which points to the home directory of the Software AG installation. These are the steps to define this variable.

1. Open Eclipse or Software AG Designer
1. Go to Window > Preferences
1. Navigate to Java > Build Path > Classpath Variables
1. Create a new variable called WEBMETHODS_HOME and set it to the folder where the Software AG software is installed (e.g. C:\SoftwareAG)

## Editing the source code

1. Open Eclipse or Software AG Designer and go to the wm-http-interceptor project in the Java perspective
1. Navigate to the src folder
1. Expand the Java package com.nibble.samples.is.interceptor
1. Open the HttpInboundInterceptor.java and HttpOutboundInterceptor.java files
1. Make the necessary changes
1. Save to compile

## Exporting the JAR

1. Open Eclipse or Software AG Designer and go to the wm-http-interceptor project in the Java perspective
1. Right-click on the top-level project folder
1. Click on Export...
1. Choose JAR file and click Next...
1. Select Export generated class files and resources
1. Specify your JAR file name and click Finish

## Installing the JAR and configuring the Integration Server

1. Shutdown the webMethods Integration Server
1. Copy the JAR file to the the Integration Server's lib/jars/custom directory (e.g. C:\SoftwareAG\IntegrationServer\instances\default\lib\jars\custom)
1. Open the Integration Server's server.cnf configuration file in a text editor
1. Locate the properties starting with watt.server.http.interceptor
1. If intercepting inbound calls:
    1. Set watt.server.http.interceptor.enabled=true
    1. Set watt.server.http.interceptor.impl=com.nibble.samples.is.interceptor.HttpInboundInterceptor
1. If intercepting outbound calls:
    1. Set watt.server.http.interceptor.outbound.enabled=true
    1. Set watt.server.http.interceptor.outbound.impl=com.nibble.samples.is.interceptor.HttpOutboundInterceptor
1. Save the file
1. Restart the Integration Server

## Verifying that the interceptor is working

1. Tail the Integration Server's wrapper log where all standard output is re-directed (e.g. C:\SoftwareAG\profiles\IS_default\logs\wrapper.log)
1. Perform HTTP inbound and outbound operations to see if the interceptor writes information to the wrapper log
1. A simple test is to use the pub.client:http service to invoke the wm.server:ping on the server itself. For example, if you have a local Integration Server running on port 5555, you can:
    1. Open a browser
    1. Paste the URL: http://localhost:5555/invoke/pub.client:http?url=http://localhost:5555/invoke/wm.server:ping
    1. Hit return
1. Sample output:
```
INFO   | jvm 1    | 2023/12/06 15:44:42 | HTTP Inbound Pre-Process >>>
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Method: GET
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Path: /invoke/pub.client:http?url=http://localhost:5555/invoke/wm.server:ping&method=get
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Protocol: HTTP/1.1
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Headers:
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Cookie: ssnid=c33f65feda2a4807bce0eb8bf8a558db
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Connection: keep-alive
INFO   | jvm 1    | 2023/12/06 15:44:42 |     User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Sec-Fetch-Site: none
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Sec-Fetch-Dest: document
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Host: localhost:5555
INFO   | jvm 1    | 2023/12/06 15:44:42 |     DNT: 1
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Accept-Encoding: gzip, deflate, br
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Sec-Fetch-Mode: navigate
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Authorization: Basic *************************
INFO   | jvm 1    | 2023/12/06 15:44:42 |     sec-ch-ua: "Google Chrome";v="119", "Chromium";v="119", "Not?A_Brand";v="24"
INFO   | jvm 1    | 2023/12/06 15:44:42 |     sec-ch-ua-mobile: ?0
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Upgrade-Insecure-Requests: 1
INFO   | jvm 1    | 2023/12/06 15:44:42 |     sec-ch-ua-platform: "Windows"
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Sec-Fetch-User: ?1
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Accept-Language: en-US,en;q=0.9
INFO   | jvm 1    | 2023/12/06 15:44:42 |   UUID: 0506bd09ed964640b1d128180f9c230a
INFO   | jvm 1    | 2023/12/06 15:44:42 | <<< HTTP Inbound Pre-Process
INFO   | jvm 1    | 2023/12/06 15:44:42 | HTTP Outbound Pre-Process >>>
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Method: GET
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Resource: http://localhost:5555/invoke/wm.server:ping
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Protocol: HTTP/1.1
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Headers:
INFO   | jvm 1    | 2023/12/06 15:44:42 |     User-Agent: Mozilla/4.0 [en] (WinNT; I)
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Host: localhost:5555
INFO   | jvm 1    | 2023/12/06 15:44:42 |   UUID: 5012e0f837274eed8d40b2571f26e900
INFO   | jvm 1    | 2023/12/06 15:44:42 | <<< HTTP Outbound Pre-Process
INFO   | jvm 1    | 2023/12/06 15:44:42 | HTTP Inbound Pre-Process >>>
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Method: GET
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Path: /invoke/wm.server:ping
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Protocol: HTTP/1.1
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Headers:
INFO   | jvm 1    | 2023/12/06 15:44:42 |     User-Agent: Mozilla/4.0 [en] (WinNT; I)
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Host: localhost:5555
INFO   | jvm 1    | 2023/12/06 15:44:42 |   UUID: a08ee05f0fd4427786b3eca38dfaa143
INFO   | jvm 1    | 2023/12/06 15:44:42 | <<< HTTP Inbound Pre-Process
INFO   | jvm 1    | 2023/12/06 15:44:42 | HTTP Inbound Post-Process >>>
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Response code: 200
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Response text: OK
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Headers:
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Content-Length: 164
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Content-Type: text/html; charset=UTF-8
INFO   | jvm 1    | 2023/12/06 15:44:42 |   UUID: a08ee05f0fd4427786b3eca38dfaa143
INFO   | jvm 1    | 2023/12/06 15:44:42 | <<< HTTP Inbound Post-Process
INFO   | jvm 1    | 2023/12/06 15:44:42 | HTTP Outbound Post-Process >>>
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Response code: 200
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Response text: OK
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Headers:
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Content-Length: 164
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Content-Type: text/html; charset=UTF-8
INFO   | jvm 1    | 2023/12/06 15:44:42 |   UUID: 5012e0f837274eed8d40b2571f26e900
INFO   | jvm 1    | 2023/12/06 15:44:42 | <<< HTTP Outbound Post-Process
INFO   | jvm 1    | 2023/12/06 15:44:42 | HTTP Inbound Post-Process >>>
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Response code: 200
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Response text: OK
INFO   | jvm 1    | 2023/12/06 15:44:42 |   Headers:
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Content-Length: 1261
INFO   | jvm 1    | 2023/12/06 15:44:42 |     Content-Type: text/html; charset=UTF-8
INFO   | jvm 1    | 2023/12/06 15:44:42 |   UUID: 0506bd09ed964640b1d128180f9c230a
INFO   | jvm 1    | 2023/12/06 15:44:42 | <<< HTTP Inbound Post-Process
```
