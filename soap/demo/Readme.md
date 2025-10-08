# Basic CRUD SOAP web service
### This web service supports basic crud operations
### Operations supported
>1. UpdateCustomerRequest returns 
>2. CreateCustomerRequest
>3. DeleteCustomerRequest
>4. GetCustomerRequest
### Inputs and response
 >Add Customer
 >>1. Input: CreateCustomerRequest(id, name, age, residence, balance)
   >>2. Output: CreateCustomerResponse(status: "CREATED")

>Get Customer
>>1. Input: GetCustomerRequest(id)
>>2. Output: GetCustomerResponse(id, name, age, residence, balance)

>Update Customer
>>1. Input: UpdateCustomerRequest(id, name, age, residence, balance)
>>2. Output: UpdateCustomerResponse(status: "SUCCESS")

>Delete Customer
>>1. Input: DeleteCustomerRequest(id)
>>2. Output: DeleteCustomerResponse(status: "DELETED" | "Id not found or already deleted")

