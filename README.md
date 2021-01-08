# Currency

### Crontab:
0 */12 * * * http://localhost:8080/average/update

### Endpoints:

/swagger-ui/ - swagger

/average/update - update databases<br>
/average/result - resul by last update<br>
/average/filter?startTime[yyyy-MM-dd'T'hh-MM-ss]&endTime[yyyy-MM-dd'T'hh-MM-ss] - filter by time

Minfin api replaced on mock api in application.properties
Controlers for different proveders can be deleted or used for update app

There are some inaccuracies in the implementation: privat api not have time, and time data created after update, so if update it often, it can affect the results in filter by time

The task is a little unfinished - no tests. I understand that this is important. But I couldn't keep up with the time
