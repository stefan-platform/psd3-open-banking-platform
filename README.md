# PSD3-Style Open Banking and Payment Orchestration Platform

## Overview

PSD3-Style Open Banking and Payment Orchestration Platform este un proiect enterprise-style construit pentru a simula o platformă complexă de integrare multi-bancă, inspirată din open banking, payment initiation și cerințe moderne de procesare, monitorizare și control al plăților.

Platforma oferă un model intern unificat pentru:

- managementul consimțământului
- inițierea plăților
- acces la informații despre conturi
- integrarea cu mai multe bănci prin adaptoare separate
- procesare sincronă și asincronă
- audit și istoric complet al operațiunilor
- analiză anti-fraudă
- dispute management

Scopul proiectului este strict tehnic

## Main Objectives

Acest proiect urmărește să demonstreze:

- integrare multi-bank prin contracte externe diferite
- model intern comun pentru payment initiation și account information
- procesare asincronă cu Kafka
- persistență și audit în PostgreSQL
- orchestrare și routing inteligent
- motor de reguli pentru anti-fraudă
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

### 4. Fraud Detection

- analiză reguli înainte de trimiterea plății
- scoring de risc
- flagging pentru operațiuni suspecte
- blocare / review / allow în funcție de scor și reguli
- audit pentru deciziile antifraudă

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
- istoric pentru fraud decisions
- istoric pentru dispute lifecycle
- trasabilitate pentru integrare și debugging
