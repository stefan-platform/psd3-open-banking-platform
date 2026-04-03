## Open Banking Consent Service

Open Banking Consent Service este un serviciu dedicat gestionării consimțămintelor în scenarii de open banking multi-bank.

Serviciul modelează un consent ca o relație controlată și auditabilă între:

 client
- aplicație / TPP
- una sau mai multe bănci participante
- unul sau mai multe conturi
- un set clar de permisiuni
- o perioadă de valabilitate

Acest proiect este construit pentru a simula o componentă realistă dintr-o platformă PSD3-style / open banking

### Responsabilitatile proiectului

- creare consimțământ
- activare consimțământ
- revocare consimțământ
- expirare consimțământ
- interogare consimțământ
- listare conturi acoperite de consimțământ
- validare acces pentru o bancă, un cont și o permisiune
- audit pentru schimbările de stare și acțiunile relevante
- publicare de evenimente de domeniu pentru restul platformei



### Routele interne

POST /api/consents
POST /api/consents/{consentId}/activate
POST /api/consents/{consentId}/revoke
POST /api/consents/{consentId}/expire
GET  /api/consents/{consentId}
GET  /api/consents/{consentId}/accounts
GET  /api/consents/{consentId}/history
POST /api/consents/{consentId}/validate-access

### Structura proiectului: 

```text
open-banking-consent-service/
├── src/main/java/com/stefan/consent/
│   ├── api/
│   │   ├── ConsentController.java
│   │   ├── ConsentQueryController.java
│   │   └── ConsentValidationController.java
│   │
│   ├── application/
│   │   ├── command/
│   │   ├── dto/
│   │   ├── facade/
│   │   ├── mapper/
│   │   ├── service/
│   │   └── validator/
│   │
│   ├── domain/
│   │   ├── event/
│   │   ├── exception/
│   │   ├── model/
│   │   ├── repository/
│   │   └── state/
│   │
│   ├── infrastructure/
│   │   ├── config/
│   │   ├── kafka/
│   │   └── persistence/
│   │
│   ├── exception/
│   │   └── GlobalExceptionHandler.java
│   │
│   └── ConsentServiceApplication.java
│
├── src/main/resources/
│   ├── application.yml
│   └── db/migration/
│
├── src/test/java/com/stefan/consent/
│   ├── unit/
│   └── integration/
│
├── pom.xml
└── README.md