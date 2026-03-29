## API Gateway

API Gateway reprezintă punctul unic de intrare în platformă. 
Acesta expune API-urile publice, aplică responsabilități transversale precum autentificare, 
correlation ID, request logging, rate limiting și error normalization, 
apoi rutează cererile către serviciile interne corespunzătoare.

### Responsibilities

- unified external entry point
- request routing to internal services
- token validation and propagation
- correlation ID propagation
- request/response logging
- rate limiting
- unified error responses
- health and observability endpoints

### Internal Routing

- `/api/consents/**` → `consent-service`
- `/api/payments/**` → `payment-initiation-service`
- `/api/accounts/**` → `account-information-service`
- `/api/fraud/**` → `fraud-detection-service`
- `/api/disputes/**` → `dispute-management-service`