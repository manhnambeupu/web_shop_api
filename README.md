+++

🌐 WebShop Project Overview 🛒
The WebShop project is an e-commerce system built using Spring Boot, providing a platform for online sales and management. This project is designed to assist businesses in managing products, orders, customers, and other related details effectively.

Project Structure 🏗️
📂 Repository Layer
This layer contains interfaces for interacting with the database, including repositories for:

📦 Products
📝 Product details
🛍️ Shopping carts
🧾 Invoices
⚙️ Other objects
These repositories are used to query data from the database.

🛠️ Service Layer
This layer provides business processing services, including services for:

📏 Product size (KichCoServiceIplm)
🏷️ Product type (LoaiSanPhamServiceIplm)
🎨 Color (MauSacServiceIplm)
These services are used to perform complex business operations.

🔄 Data Transfer Objects (DTOs)
The project uses DTOs to transfer data between layers and minimize the number of queries needed when interacting with the database. DTOs include:

🎨 MauSacDTO
🏷️ LoaiSanPhamDTO
📦 SanPhamDTO
📏 KichCoDTO
🗂️ Entity Layer
This layer contains entity objects mapped to the database via JPA. Entities include:

🎨 MauSac
🏷️ LoaiSanPham
📏 KichCo
Each entity has basic attributes such as id and name.

🗄️ Database
The project uses MySQL to create and manage databases, with data tables designed to store information about products, shopping carts, invoices, and other objects.

Project Configuration ⚙️
The project is configured and managed using Maven, which supports automatic build and dependency management. Application configuration is stored in the application.properties file, allowing environment and database settings to be easily changed.
+++
