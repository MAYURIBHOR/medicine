# Pharmacy Management System

A complete and beautiful Spring Boot pharmacy management system with user/admin login, password validation, inventory, sales, prescriptions, customers, suppliers, alerts, and notifications.

## Features

- Inventory Management: Track medicine stock and expiry.
- Expiry Highlighting: Expired/soon-to-expire medicines are visually marked.
- Expiry Alerts: Automatic alerts for medicines expiring in 30 days.
- Sales & Billing: Generate bills, multiple payment modes.
- Prescription Management: Store and verify digital prescriptions.
- Customer Management: Profiles and purchase history.
- Supplier Management: Details and order tracking.
- User Access Control: Secure login, role-based access (admin, staff).
- Responsive UI with Bootstrap, images, color themes.

## Requirements

- Java 17+
- Maven

## Setup

1. **Clone or extract the project.**
2. Place your images (pharmacy-logo.png, medicine.png, sale.png, prescription.png, customer.png, supplier.png, alert.png) in `src/main/resources/static/images/`.
3. In project root, run:
   ```
   mvn spring-boot:run
   ```
4. Access the app at [http://localhost:8080](http://localhost:8080)

## Login & Registration

- Users register with email and password (min 8 chars, 1 special, 1 upper, 1 lower).
- Choose role: STAFF or ADMIN.
- Admins can access `/admin/*` pages, staff can use user features.
- Eye icon to toggle password visibility.

## H2 Database

- Default uses H2 in-memory.
- Access H2 console at `/h2-console` with JDBC URL: `jdbc:h2:mem:pharmacydb`

## Customization

- To use MySQL/PostgreSQL, edit `application.properties`.
- Add more features/pages using the controller/service/template pattern.

## Credits

- Images: Use free icons from [Flaticon](https://flaticon.com) or [Unsplash](https://unsplash.com)
- Designed by MAYURIBHOR.
