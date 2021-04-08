Spring Boot 2+ and Spring MVC.


General overview

Create a simple API (using Java EE and Spring MVC) and database (using mysql 5.7) to allow storage and management of customers consent preferences.


Your solution should store the following information:


Customer (minimum details)


CustomerId

FirstName

LastName

….

….


Consent


CustomerId

ConsentType enum values: [essentials, marketing, others]

ChannelName enum values: [email, phone, sms, social]

ChannelConsent: true, false (default false)


The API must provide the following functionality:


1. Add consent preference for a customer
2. Fetch customer consent (please include all ConsentType values) for a certain customerId.
If a certain ConsentType or ChannelName is not stored explicitly into the database, the
default value should be considered `false` as result of output
3. Fetch all customers consent values for a certain ConsentType (paginated, default page
size = 10 records)
4. Swagger for API
5. Docker containers for the hosted environment, including seed data script


Example:


Customer:

CustomerID=5, FirstName = Test, LastName=Test

CustomerID=6, FirstName = SecondTest, LastName= SecondTest



Consent:

CustomerID=5, ConsentType=’essentials’, ChannelName=’email’, ChannelConsent=true,

CustomerID=5, ConsentType=’marketing’, ChannelName=phone, ChannelConsent=false,

CustomerID=5, ConsentType= others, ChannelName=’phone’, ChannelConsent=true,

CustomerID=6, ConsentType=’essentials’, ChannelName=’email’, ChannelConsent=true,

CustomerID=6, ConsentType= ‘others’, ChannelName= ‘social’, ChannelConsent=true


How to build:

From the root of the project:
- docker-compose build
- docker-compose up

Init database:
- docker exec -i customerconsent_db_1 mysql -uroot -proot test_db < seed.sql
