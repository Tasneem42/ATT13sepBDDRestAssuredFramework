Feature:Search song
Scenario:Search a song
Given Get a search song payload
| songname | type | artist |
|Lag Ja Gale Se Phir | track | Lata Mangeshkar |
When user calls with GET request
Then API executes with status code 200