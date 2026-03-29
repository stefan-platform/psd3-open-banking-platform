# PSD3-Style Open Banking 

## Overview

PSD3-Style Open Banking este un proiect enterprise-style construit pentru a simula o platformă complexă de integrare multi-bancă, inspirată din open banking.

Platforma oferă un model intern unificat pentru:

- managementul consimțământului
- inițierea plăților
- acces la informații despre conturi
- integrarea cu mai multe bănci prin adaptoare separate
- procesare sincronă și asincronă
- audit și istoric complet al operațiunilor
- analiză AML
- dispute management

Scopul proiectului este strict tehnic

## Main Objectives

Acest proiect urmărește să demonstreze:

- integrare multi-bank prin contracte externe diferite
- model intern comun pentru payment initiation și account information
- procesare asincronă prin Kafka
- persistență și audit în PostgreSQL
- orchestrare și routing inteligent
- motor de reguli pentru AML
- lifecycle pentru dispute
- extensibilitate pentru noi bănci și noi fluxuri

## Functional Scope

### 1. Consent Management

- creare consimțământ
- activare consimțământ
- revocare consimțământ
- verificare consimțământ activ pentru operațiuni
- istoric acces și audit

### 2. Payment Initiation

- inițiere plată prin API unificat
- validare request
- routing către banca potrivită
- transformare request intern -> request bancă
- mapare răspuns bancă -> status intern
- update status plată
- publicare evenimente Kafka

### 3. Account Information

- listare conturi
- sold cont
- istoric tranzacții
- agregare răspunsuri din bănci diferite
- normalizare format răspuns

### 4. AML Detection

- analiză reguli înainte de trimiterea plății
- scoring de risc
- flagging pentru operațiuni suspecte
- blocare / review / allow în funcție de scor și reguli
- audit pentru deciziile AML

### 5. Dispute Management

- deschidere dispută pentru o plată
- clasificare dispută
- assign către status intern
- urmărire investigație
- rezolvare dispută
- jurnal complet de acțiuni și decizii

### 6. Audit & Event History

- istoric pentru payment lifecycle
- istoric pentru consent lifecycle
- istoric pentru AML decisions
- istoric pentru dispute lifecycle
- trasabilitate pentru integrare și debugging

## Arhitectura proiectului
 ### 1.api-gateway
 Reprezintă punctul unic de intrare în platformă. Acesta expune API-urile publice, aplică responsabilități transversale precum autentificare, correlation  ID, request logging, rate limiting și error normalization, apoi rutează cererile către serviciile interne corespunzătoare.
