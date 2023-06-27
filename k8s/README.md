Attach to postgres container
```dockerfile
kubectl exec -it postgres-0 -- psql -d spring-microservices -U root -W
```
Create databases
```sql
CREATE DATABASE customer;
CREATE DATABASE fraud;
CREATE DATABASE notification;
```