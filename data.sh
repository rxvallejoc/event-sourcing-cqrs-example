#!/bin/bash

set -xeuo pipefail

################ Cusotmer
curl 'http://localhost:8080/customers/commands/sign-up' \
  --data '{"email":"rxvallejoc@gmail.com"}' \
  -H 'Content-Type:application/json'
echo
################ Customer addresses
curl 'http://localhost:8080/customers/{customer_id}/addresses/commands/add' \
  --data '{"nickName":"Home","location":"Charles Darwin y Vicente Solano"}' \
  -H 'Content-Type:application/json'
echo

curl 'http://localhost:8080/customers/{customer_id}/addresses/commands/add' \
  --data '{"nickName":"Work","location":"Republica del Salvador y Suiza"}' \
  -H 'Content-Type:application/json'
echo

################ Cusotmer
curl 'http://localhost:8080/customers/commands/sign-up' \
  --data '{"email":"karina@gmail.com"}' \
  -H 'Content-Type:application/json'
echo

################ Customer addresses
curl 'http://localhost:8080/customers/{customer_id}/addresses/commands/add' \
  --data '{"nickName":"Home","location":"Av. Amazonas y Eloy Alfaro"}' \
  -H 'Content-Type:application/json'
echo

curl 'http://localhost:8080/customers/{customer_id}/addresses/commands/add' \
  --data '{"nickName":"Work","location":"Republica del Salvador y Suiza"}' \
  -H 'Content-Type:application/json'
echo


############## Menu
curl http://localhost:8081/menu/items/commands/add \
  --data '{"description": "Coca Cola", "price": "3.00"}' \
  -H 'Content-Type:application/json'
echo

curl http://localhost:8081/menu/items/commands/add \
  --data '{"description": "Hamburgesa", "price": "10.00"}' \
  -H 'Content-Type:application/json'
echo

curl http://localhost:8081/menu/items/commands/add \
  --data '{"description": "Hotdog", "price": "8.00"}' \
  -H 'Content-Type:application/json'
echo

curl http://localhost:8081/menu/items/commands/add \
  --data '{"description": "Agua Mineral", "price": "2.00"}' \
  -H 'Content-Type:application/json'
echo


############## Orders
############## Open order
curl http://localhost:8082/orders/commands/open \
  --data '{"customer": "http://localhost:8080/customers/{customer_id}"}' \
  -H 'Content-Type:application/json'
echo
############## Order Add items

curl  http://localhost:8082/orders/{orderId}/orderItems/commands/add \
  --data '{"quantity":1, "menuItem":""}' \
  -H 'Content-Type:application/json'
echo
############## Order Add address

curl http://localhost:8082/orders/{orderId}/commands/add-delivery-address \
  --data '{"address": ""}' \
  -H 'Content-Type:application/json'
echo
