# Currency

### Endpoints:

/swagger-ui/ - swagger

/average/update - update databases
/average/result - resul by last update
/average/filter?startTime[yyyy-MM-dd'T'hh-MM-ss]&endTime[yyyy-MM-dd'T'hh-MM-ss] - filter by time


Еhere are some inaccuracies in the implementation: privat api not have time, and time data created after update, so if update it often, it can affect the results in filter by time
