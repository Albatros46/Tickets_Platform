# 🎫 Event Ticket Platform

Bu proje, **Spring Boot**, **React**, ve **PostgreSQL** kullanılarak geliştirilen bir Etkinlik Bilet Satış Platformudur.

## 🛠️ Kullanılan Teknolojiler

### Backend:
- Java 21
- Spring Boot 3.4.6
- Spring Security & OAuth2 Resource Server
- Spring Data JPA
- PostgreSQL
- MapStruct
- Lombok

### Frontend:
- React 
- RESTful API Entegrasyonu

## 🔐 Güvenlik
- Token bazlı authentication (OAuth2 Resource Server)
- Rol bazlı kullanıcı erişimi (ROLE_ADMIN, ROLE_USER)

## 📦 Özellikler
- Etkinlik oluşturma (Yönetici yetkisiyle)
- Kullanıcıların etkinlikleri görüntülemesi
- Bilet satın alma ve takip
- Backend için Swagger veya Postman collection ile test imkanı
- Devtools ile geliştirme kolaylığı

## 🧪 Test
- JUnit 5
- Spring Security Test
- H2 Test Veritabanı

## 🚀 Kurulum

```bash
# Backend
cd tickets
./mvnw spring-boot:run

# Frontend (varsayım: frontend ayrı dizinde)
cd front
npm install
npm run dev
