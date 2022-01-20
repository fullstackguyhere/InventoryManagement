# Shopify-Backend-Intern-Challenge-2022

## Instruction to run the code

### Prequisites
- Get Maven (Refer download and installtion docs given [here](https://maven.apache.org/))
- Get Java 17, instruction can be found [here](https://adoptopenjdk.net/)
- Get MySQL database, instruction can be found [here](https://dev.mysql.com/downloads/) based on your operating system

### Using IDE (Intellij)
- Clone the project and open it's root directory in Jet Brains Intellij
- Update the MySQL url, username, password as per your system in the application.properties file (`spring.datasource.url, spring.datasource.username, spring.datasource.password=root`)
- Update the maven dependencies (Right click on project name -> Maven -> Update Project/Reload Project)
- Run the app (Click on InventoryManagementApplication from Add Configuration dropdown -> Click On Run button on the right side)
- Done, Now application is running on port 8080, You can make HTTP request using Postman or Using CURL

### Using Command line
### Steps
- Clone the project and navigate to it's classpath directory (/InverntoryManagement)
- Update the MySQL url, username, password as per your system in the application.properties file (`spring.datasource.url, spring.datasource.username, spring.datasource.password=root`)
- go to `/src/main/resources/`
- open mysql in terminal and run this file `inventorydb-bootup.sql`
- Open cmd in the given directory and run the following command
```sh
 mvn clean install
 ```
- Once build is successfully run the following command
```sh
 mvn spring-boot:run
 ```
- Done, Now application is running on port 8080, now you can make Http request using POSTMAN or CURL

POSTMAN Collection is saved as Inventory Management.postman_collection.json in the /InverntoryManagement. 
You can use it to hit endpoints once service is up and running.

### Basic CRUD Functionality. Urls are as follows:

#### Base URL:- `http://localhost:8080/api/v1`


- Create inventory items `/item`
- Edit Them `/item/{id}`
- Delete Them `/item/{id}`
- View a list of them `/item?pageSize={pageSize}&pageNo={pageNo}`

### Additional Feature implemented:-

I have chosen the CSV export feature. I have assumed we can do multiple type of exports, for export inventory data to csv as mentioned below:-
- Export all data `/csv/all`
- Export based on pickupdate range `/csv/pick_up_date/{fromDate}/{toDate}`
- Export based on estimated delivery range `/csv/estimated_delivery_date/{fromDate}/{toDate}`
- Export based on inventory item picked up from a source `/csv/source/{sourceName}`
- -Export based on inventory item being delivered to a particular destination `/csv/destination/{destinationName}`
- Export based on inventory item being picked up from a source and delivered to a particular destination `/csv/source_destination/{sourceName}/{Destination}`
- Export based on delivery status `/csv/delivery_status/{deliveryStatus}`

Requests for all this can be found in the postman collection
