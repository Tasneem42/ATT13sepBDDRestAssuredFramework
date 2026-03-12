Feature:Validate palylist api's
Scenario:verify create if palylist is working
Given Create palylist api payload
When user calls with POST http request for create playlist
Then API call executed with status code 201

Scenario:verify if fetch playlist functionality is working 
Given Get playlist api payload
When user calls with GET http request
Then API call executes with status code 200


Scenario:verify if update playlist functionality is working
Given update playlist api payload
When user calls with PUT http request
Then API call should execute with status code 200