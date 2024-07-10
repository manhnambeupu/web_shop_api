The WebShop project is an e-commerce system built using Spring Boot, providing a platform for online sales and management. This project is designed to assist businesses in managing products, orders, customers, and other related details effectively.

The project structure includes the following main components:

Repository Layer: This layer contains interfaces for interacting with the database, including repositories for products, product details, shopping carts, invoices, and other objects. These repositories are used to query data from the database.

Service Layer: This layer provides business processing services, including services for product size (KichCoServiceIplm), product type (LoaiSanPhamServiceIplm), and color (MauSacServiceIplm). These services are used to perform complex business operations.

Data Transfer Objects (DTOs): The project uses DTOs to transfer data between layers and minimize the number of queries needed when interacting with the database. DTOs include MauSacDTO, LoaiSanPhamDTO, SanPhamDTO, and KichCoDTO.

Entity Layer: This layer contains entity objects mapped to the database via JPA. Entities include MauSac, LoaiSanPham, and KichCo, each of which has basic attributes such as id and name.

Database: The project uses MySQL to create and manage databases, with data tables designed to store information about products, shopping carts, invoices, and other objects.

The project is configured and managed using Maven, which supports automatic build and dependency management. Application configuration is stored in the application.properties file, allowing environment and database settings to be easily changed.
