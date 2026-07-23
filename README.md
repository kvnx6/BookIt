# Bookit

A booking marketplace for service businesses hairdressers, physiotherapists, coaches, beauty salons and similar.

Unlike a simple one-provider scheduling tool, Bookit is built as a multi-tenant marketplace: any user can be a customer, a business owner, or both at the same time. A business can have multiple staff members, each with their own services and availability. Customers browse businesses, pick a service, choose a specific staff member or "any available", and book a free slot no login required to browse, login required to book.

## Why

Most small service businesses still run their bookings through phone calls or a shared Excel sheet. Bookit gives them a simple, self-service booking page they can share with customers, without locking them into an expensive SaaS subscription.

## Features

- Public business profiles with a shareable booking link
- Staff-level availability (recurring weekly rules + one-off exceptions for holidays or extra slots)
- Service catalog per business (duration, price, buffer time between appointments)
- "Any available staff" booking, or pick a specific person
- Automatic slot calculation that respects availability, buffers, and existing bookings
- Email confirmation on booking, with cancel/reschedule links
- Owner dashboard with a calendar view of upcoming appointments

## Tech stack

**Backend**
- Java 21, Spring Boot 3, Gradle
- Spring Data JPA, MySQL
- Spring Security with JWT authentication
- Spring Mail for booking notifications

**Frontend**
- Angular (standalone components)
- Angular Material
- RxJS

## Project structure

```
bookit/
├── backend/
│   ├── build.gradle
│   ├── src/main/java/com/bookit/
│   │   ├── auth/                    # registration, login, JWT
│   │   ├── security/                # security config, filters
│   │   ├── users/
│   │   ├── businesses/
│   │   ├── staffmembers/
│   │   ├── services/
│   │   ├── categories/            
│   │   ├── booking/                
│   └── .env
│       
└── frontend/
    └── src/app/
        ├── public/          # marketplace browsing, booking page
        ├── dashboard/       # business owner / staff dashboard
        └── auth/
```

## Getting started

### Prerequisites

- Java 21+
- Node.js 20+
- MySQL 8+

### Backend

```bash
cd backend
cp src/main/resources/application-example.yml src/main/resources/application.yml
# fill in your MySQL credentials and JWT secret
./gradlew bootRun
```

The API runs on `http://localhost:8080`.

### Frontend

```bash
cd frontend
npm install
npm run dev
```

The app runs on `http://localhost:4200`.

## Data model (short version)

| Entity | Description |
|---|---|
| `Users` | Any registered person — can own businesses, work as staff, and book as a customer |
| `Businesses` | A company profile, owned by a `User`, has a unique public slug |
| `StaffMembers` | A person working at a business, optionally linked to a `User` account |
| `Services` | A bookable service (duration, price, buffer), offered by one or more staff |
| `Categories` | Recurring weekly working hours per staff member |
| `Bookings` | A confirmed appointment between a customer, a staff member, and a service |

