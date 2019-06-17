# MoneyTransfer

How to start the MoneyTransfer application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/money-trans-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

## Sample Calls

1. Get Accounts for a User
```curl -X GET \
  http://localhost:8080/accounts \
  -H 'Accept: */*' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: 4bede4b3-d29d-44ac-a34b-082a299a8bf4,d1a606a9-340c-4820-93cb-6d6cd7296e0e' \
  -H 'User-Agent: PostmanRuntime/7.13.0' \
  -H 'accept-encoding: gzip, deflate' \
  -H 'cache-control: no-cache' \
  -H 'user_id: 1122'
```

2. Get account for an Account Number
```
curl -X GET \
  http://localhost:8080/accounts/1 \
  -H 'Accept: */*' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: 3b331d3d-2b9f-4fb9-8a93-953a5e4d3904,38c42df7-8fe9-4971-ba2e-d3c718ed166b' \
  -H 'User-Agent: PostmanRuntime/7.13.0' \
  -H 'accept-encoding: gzip, deflate' \
  -H 'cache-control: no-cache'
```
  
3. Delete account with an Account Number
```
curl -X DELETE \
  http://localhost:8080/accounts/2 \
  -H 'Accept: */*' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: 93463075-e5f0-4064-b543-8b145a1acee4,efab9443-5d70-45c1-b2cf-6c11255b6cde' \
  -H 'User-Agent: PostmanRuntime/7.13.0' \
  -H 'accept-encoding: gzip, deflate' \
  -H 'cache-control: no-cache' \
  -H 'content-length: '
```

4. Get Transactions with an Account Number
```
curl -X GET \
  'http://localhost:8080/transactions?account_number=1' \
  -H 'Accept: */*' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: 29c36786-26ea-4c12-af5e-71058b755dc5,03770d50-283b-48aa-a3b2-2894990dfa3a' \
  -H 'User-Agent: PostmanRuntime/7.13.0' \
  -H 'accept-encoding: gzip, deflate' \
  -H 'account_number: 2' \
  -H 'cache-control: no-cache' \
  -H 'only_outgoing: true'
 ```

5. Create Account
```
curl -X POST \
  http://localhost:8080/accounts \
  -H 'Accept: */*' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: 808f33c1-1489-4be7-8667-722565b7b8f7,3cbc4154-31cb-4922-a8c5-49de1c6f2271' \
  -H 'User-Agent: PostmanRuntime/7.13.0' \
  -H 'accept-encoding: gzip, deflate' \
  -H 'cache-control: no-cache' \
  -H 'content-length: 102' \
  -d '{
	"is_active":true,
	"account_type":"CURRENT",
	"currency":"EURO",
	"user_id":1122,
	"balance":1200
}'
```

6. Create Transaction
```
curl -X POST \
  http://localhost:8080/transactions \
  -H 'Accept: */*' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: 185aa020-8277-435b-9876-25c450443357,2743f447-29d8-4d02-897f-cd48b002fe15' \
  -H 'User-Agent: PostmanRuntime/7.13.0' \
  -H 'accept-encoding: gzip, deflate' \
  -H 'cache-control: no-cache' \
  -H 'content-length: 63' \
  -d '{
	"source_account":1,
	"destination_account":3,
	"amount":20
}'
```

7. Get Transaction with a Transaction Id
```
curl -X GET \
  http://localhost:8080/transactions/4 \
  -H 'Accept: */*' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: 19ea3b76-8144-4e2c-9cfa-7d6bffea3cba,4313bb46-cda1-4c91-9ebf-f8cf33d20102' \
  -H 'User-Agent: PostmanRuntime/7.13.0' \
  -H 'accept-encoding: gzip, deflate' \
  -H 'cache-control: no-cache'
```

8. Get Transactions with the Account Number(Only Source)
```
curl -X GET \
  'http://localhost:8080/transactions?account_number=4&only_incoming=true' \
  -H 'Accept: */*' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: ab079715-61a1-4300-b8f4-b8707eb430af,638aa648-3ea9-43bc-83a8-f90d3927f202' \
  -H 'User-Agent: PostmanRuntime/7.13.0' \
  -H 'accept-encoding: gzip, deflate' \
  -H 'cache-control: no-cache'
```
