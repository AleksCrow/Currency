# Currency

### Schedule: 
Information update was set at 6 and 18 hours ev'ry day: 0 0 6,18 * * *

### Endpoints:

/swagger-ui/ - swagger

/average/update - update databases<br>
/average/result - result by last update<br>
/average/filter?startTime=[yyyy-MM-dd'T'hh-MM-ss]&endTime=[yyyy-MM-dd'T'hh-MM-ss] - filter by time. Can be used one of parameters

Minfin api replaced on mock api in application.properties.

There are some inaccuracies in the implementation: privat api not have time, and time data created after update, so if update it often, it can affect the results in filter by time.
