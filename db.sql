create table OBSLUGA
(
    ID_FILHARMONII NUMBER not null,
    ID_KLIENTA     NUMBER not null
)
/

create table WYSTEPY
(
    ID_WYDARZENIA NUMBER not null,
    ID_ARTYSTY    NUMBER not null
)
/

create table NAJEM
(
    ID_FILHARMONII NUMBER not null,
    ID_ARTYSTY     NUMBER not null
)
/

create table PRACA_PRZY
(
    ID_PRACOWNIKA    NUMBER not null,
    ID_WYDARZENIA    NUMBER not null,
    DATA_ROZPOCZECIA DATE   not null,
    DATA_ZAKONCZENIA DATE   not null
)
/

create table ADRESY
(
    ID_ADRESU    NUMBER       not null
        constraint PK_ADRESY
            primary key,
    MIASTO       VARCHAR2(20) not null,
    ULICA        VARCHAR2(30) not null,
    NR_BUDYNKU   VARCHAR2(4)  not null,
    NR_LOKALU    VARCHAR2(4),
    KOD_POCZTOWY CHAR(6)      not null
)
/

create table FILHARMONIE
(
    ID_FILHARMONII    NUMBER        not null,
    NAZWA_FILHARMONII VARCHAR2(100) not null
        constraint NAZWA_FILHARMONII
            unique,
    DATA_ZALOZENIA    DATE          not null,
    ID_ADRESU         NUMBER        not null
        constraint FILHARMONIA_MA_ADRES
            references ADRESY
)
/

create index IX_ID_FILHARMONII
    on FILHARMONIE (ID_FILHARMONII)
/

alter table FILHARMONIE
    add constraint FILHARMONIA_PK
        primary key (ID_FILHARMONII)
/

create trigger TS_FILHARMONIE_ID_FILHARMONII_S
    before insert
    on FILHARMONIE
    for each row
BEGIN
  :new.id_filharmonii := id_filharmonii_s.nextval;
END;
/

create trigger TSU_FILHARMONIE_ID_FILHARMONII_S
    after update of ID_FILHARMONII
    on FILHARMONIE
    for each row
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column id_filharmonii in table Filharmonie as it uses sequence.');
END;
/

create table KASY_BILETOWA
(
    ID_KASY            NUMBER      not null,
    GODZINA_OTWARCIA   VARCHAR2(5) not null,
    GODZINA_ZAMKNIECIA VARCHAR2(5) not null,
    NR_TELEFONU        VARCHAR2(12),
    ID_FILHARMONII     NUMBER      not null
        constraint POSIADA
            references FILHARMONIE
)
/

create index IX_ID_KASY
    on KASY_BILETOWA (ID_KASY)
/

alter table KASY_BILETOWA
    add constraint KASA_BILETOWA_PK
        primary key (ID_KASY)
/

create trigger TS_KASY_BILETOWA_ID_KASY_S
    before insert
    on KASY_BILETOWA
    for each row
BEGIN
  :new.id_kasy := id_kasy_s.nextval;
END;
/

create trigger TSU_KASY_BILETOWA_ID_KASY_S
    after update of ID_KASY
    on KASY_BILETOWA
    for each row
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column id_kasy in table Kasy_Biletowa as it uses sequence.');
END;
/

create table KLIENCI
(
    ID_KLIENTA  NUMBER       not null,
    IMIE        VARCHAR2(20) not null,
    NAZWISKO    VARCHAR2(30) not null,
    NR_TELEFONU VARCHAR2(12) not null,
    PLEC        CHAR         not null
        check (plec IN ('K', 'M')),
    ID_ADRESU   NUMBER       not null
        constraint KLIENT_MA_ADRES
            references ADRESY,
    HASLO       VARCHAR2(100)
)
/

create index IX_ID_KLIENTA
    on KLIENCI (ID_KLIENTA)
/

create unique index KLIENCI_NR_TELEFONU_UINDEX
    on KLIENCI (NR_TELEFONU)
/

alter table KLIENCI
    add constraint KLIENT_PK
        primary key (ID_KLIENTA)
/

create trigger TS_KLIENCI_ID_KLIENTA_S
    before insert
    on KLIENCI
    for each row
BEGIN
  :new.id_klienta := id_klienta_s.nextval;
END;
/

create trigger TSU_KLIENCI_ID_KLIENTA_S
    after update of ID_KLIENTA
    on KLIENCI
    for each row
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column id_klienta in table Klienci as it uses sequence.');
END;
/

create table SALE
(
    ID_SALI                NUMBER       not null
        constraint SALA_PK
            primary key,
    OZN_SALI               VARCHAR2(12) not null,
    LICZBA_MIEJSC_STREFA_A NUMBER       not null,
    LICZBA_MIEJSC_STREFA_B NUMBER       not null,
    LICZBA_MIEJSC_STREFA_C NUMBER       not null,
    ID_FILHARMONII         NUMBER       not null
        constraint POSIADA_SALE
            references FILHARMONIE
)
/

create index IX_ID_SALI
    on SALE (ID_FILHARMONII)
/

create trigger TS_SALE_ID_SALI_S
    before insert
    on SALE
    for each row
BEGIN
  :new.id_sali := id_sali_s.nextval;
END;
/

create trigger TSU_SALE_ID_SALI_S
    after update of ID_SALI
    on SALE
    for each row
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column id_sali in table Sale as it uses sequence.');
END;
/

create table ARTYSCI
(
    ID_ARTYSTY  NUMBER       not null,
    IMIE        VARCHAR2(20) not null,
    NAZWISKO    VARCHAR2(30) not null,
    NR_TELEFONU VARCHAR2(12) not null,
    PESEL       CHAR(11),
    NR_KONTA    CHAR(26),
    PLEC        CHAR         not null
        check (plec IN ('K', 'M')),
    ID_ADRESU   NUMBER       not null
        constraint ARTYSTA_MA_ADRES
            references ADRESY
)
/

create index IX_ID_ARTYSTY
    on ARTYSCI (ID_ARTYSTY)
/

alter table ARTYSCI
    add constraint ARTYSTA_PK
        primary key (ID_ARTYSTY)
/

create trigger TS_ARTYSCI_ID_ARTYSTY_S
    before insert
    on ARTYSCI
    for each row
BEGIN
  :new.id_artysty := id_artysty_s.nextval;
END;
/

create trigger TSU_ARTYSCI_ID_ARTYSTY_S
    after update of ID_ARTYSTY
    on ARTYSCI
    for each row
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column id_artysty in table Artysci as it uses sequence.');
END;
/

create trigger TS_ADRESY_ID_ADRESU_S
    before insert
    on ADRESY
    for each row
BEGIN
  :new.id_adresu := id_adresu_s.nextval;
END;
/

create trigger TSU_ADRESY_ID_ADRESU_S
    after update of ID_ADRESU
    on ADRESY
    for each row
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column id_adresu in table Adresy as it uses sequence.');
END;
/

create table RODZAJE_WYDARZENIA
(
    ID_RODZAJU    NUMBER        not null
        constraint PK_RODZAJE_WYDARZENIA
            primary key,
    NAZWA_RODZAJU VARCHAR2(50)  not null
        constraint NAZWA_RODZAJU
            unique,
    OPIS          VARCHAR2(300) not null
)
/

create table WYDARZENIA
(
    ID_WYDARZENIA    NUMBER        not null,
    NAZWA_WYDARZENIA VARCHAR2(100) not null
        constraint NAZWA_WYDARZENIA
            unique,
    CZAS_ROZPOCZECIA DATE          not null,
    OPIS             VARCHAR2(500) not null,
    ID_FILHARMONII   NUMBER        not null
        constraint ORGANIZUJE
            references FILHARMONIE,
    ID_SALI          NUMBER        not null
        constraint ODBYWA_SIE_NA
            references SALE,
    ID_RODZAJU       NUMBER        not null
        constraint JEST_RODZAJU
            references RODZAJE_WYDARZENIA
)
/

create index IX_ID_WYDARZENIA
    on WYDARZENIA (ID_WYDARZENIA)
/

alter table WYDARZENIA
    add constraint WYDARZENIE_PK
        primary key (ID_WYDARZENIA)
/

create trigger TS_WYDARZENIA_ID_WYDARZENIA_S
    before insert
    on WYDARZENIA
    for each row
BEGIN
  :new.id_wydarzenia := id_wydarzenia_s.nextval;
END;
/

create trigger TSU_WYDARZENIA_ID_WYDARZENIA_S
    after update of ID_WYDARZENIA
    on WYDARZENIA
    for each row
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column id_wydarzenia in table Wydarzenia as it uses sequence.');
END;
/

create table BILETY
(
    ID_BILETU     NUMBER not null,
    CENA          NUMBER(8, 2),
    RODZAJ        CHAR   not null
        constraint CHECKCONSTRAINTA1
            check (rodzaj IN ('U', 'N'))
        check (rodzaj IN ('U', 'N')),
    STREFA        CHAR   not null,
    OZN_MIEJSCA   VARCHAR2(5),
    ID_KLIENTA    NUMBER
        constraint KUPUJE
            references KLIENCI,
    ID_WYDARZENIA NUMBER not null
        constraint UDOSTEPNIA
            references WYDARZENIA,
    ID_KASY       NUMBER
        constraint SPRZEDAJE_BILET
            references KASY_BILETOWA
)
/

create index IX_ID_BILETU
    on BILETY (ID_BILETU)
/

alter table BILETY
    add constraint BILET_PK
        primary key (ID_BILETU)
/

create trigger TS_BILETY_ID_BILETU_S
    before insert
    on BILETY
    for each row
BEGIN
  :new.id_biletu := id_biletu_s.nextval;
END;
/

create trigger TSU_BILETY_ID_BILETU_S
    after update of ID_BILETU
    on BILETY
    for each row
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column id_biletu in table Bilety as it uses sequence.');
END;
/

create table WYNAGRODZENIA_ZA_WYDARZENIE
(
    ID_WYNAGRODZENIA NUMBER       not null,
    DATA             DATE         not null,
    KWOTA            NUMBER(8, 2) not null,
    ID_WYDARZENIA    NUMBER       not null
        constraint JEST_WYPLACANE
            references WYDARZENIA,
    ID_ARTYSTY       NUMBER       not null
        constraint OTRZYMUJE
            references ARTYSCI
)
/

create index IX_ID_WYNAGRODZENIA
    on WYNAGRODZENIA_ZA_WYDARZENIE (ID_WYNAGRODZENIA)
/

alter table WYNAGRODZENIA_ZA_WYDARZENIE
    add constraint UNIQUE_IDENTIFIER1
        primary key (ID_WYNAGRODZENIA)
/

create trigger TS_WYNAGRODZENIA_ZA_WYDARZENIE_ID_WYNAGRODZENIA_ZA_WYDARZENIE_S
    before insert
    on WYNAGRODZENIA_ZA_WYDARZENIE
    for each row
BEGIN
  :new.id_wynagrodzenia := id_wynagrodzenia_za_wydarzenie_s.nextval;
END;
/

create trigger TSU_WYNAGRODZENIA_ZA_WYDARZENIE_ID_WYNAGRODZENIA_ZA_WYDARZENIE_S
    after update of ID_WYNAGRODZENIA
    on WYNAGRODZENIA_ZA_WYDARZENIE
    for each row
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column id_wynagrodzenia in table Wynagrodzenia_Za_Wydarzenie as it uses sequence.');
END;
/

create trigger TS_RODZAJE_WYDARZENIA_ID_RODZAJE_WYDARZENIA_S
    before insert
    on RODZAJE_WYDARZENIA
    for each row
BEGIN
  :new.id_rodzaju := id_rodzaje_wydarzenia_s.nextval;
END;
/

create trigger TSU_RODZAJE_WYDARZENIA_ID_RODZAJE_WYDARZENIA_S
    after update of ID_RODZAJU
    on RODZAJE_WYDARZENIA
    for each row
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column id_rodzaju in table Rodzaje_Wydarzenia as it uses sequence.');
END;
/

create table STANOWISKA
(
    ID_STANOWISKA    NUMBER        not null
        constraint PK_STANOWISKA
            primary key,
    NAZWA_STANOWISKA VARCHAR2(30)  not null
        constraint NAZWA
            unique,
    OPIS             VARCHAR2(300) not null
)
/

create trigger TS_STANOWISKA_ID_STANOWISKA_S
    before insert
    on STANOWISKA
    for each row
BEGIN
  :new.id_stanowiska := id_stanowiska_s.nextval;
END;
/

create trigger TSU_STANOWISKA_ID_STANOWISKA_S
    after update of ID_STANOWISKA
    on STANOWISKA
    for each row
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column id_stanowiska in table Stanowiska as it uses sequence.');
END;
/

create table INSTRUMENTY
(
    ID_INSTRUMENTU    NUMBER       not null
        constraint PK_INSTRUMENTY
            primary key,
    NAZWA_INSTRUMENTU VARCHAR2(50) not null
        constraint NAZWA_INSTRUMENTU
            unique
)
/

create trigger TS_INSTRUMENTY_ID_INSTRUMENTU_S
    before insert
    on INSTRUMENTY
    for each row
BEGIN
  :new.id_instrumentu := id_instrumentu_s.nextval;
END;
/

create trigger TSU_INSTRUMENTY_ID_INSTRUMENTU_S
    after update of ID_INSTRUMENTU
    on INSTRUMENTY
    for each row
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column id_instrumentu in table Instrumenty as it uses sequence.');
END;
/

create table WYNAGRODZENIA
(
    ID_WYNAGRODZENIA NUMBER       not null
        constraint PK_WYNAGRODZENIA
            primary key,
    DATA             DATE         not null,
    KWOTA            NUMBER(8, 2) not null
)
/

create table PRACOWNICY
(
    ID_PRACOWNIKA    NUMBER       not null,
    IMIE             VARCHAR2(20) not null,
    NAZWISKO         VARCHAR2(30) not null,
    PLEC             CHAR         not null
        check (plec IN ('K', 'M')),
    PESEL            CHAR(11),
    NR_KONTA         CHAR(26),
    NR_TELEFONU      VARCHAR2(12) not null,
    ID_FILHARMONII   NUMBER       not null
        constraint ZATRUDNIA
            references FILHARMONIE,
    ID_KASY          NUMBER
        constraint PRACUJE_W
            references KASY_BILETOWA,
    ID_ADRESU        NUMBER       not null
        constraint PRACOWNIK_MA_ADRES
            references ADRESY,
    ID_WYNAGRODZENIA NUMBER       not null
        constraint OTRZYMUJE_WYNAGRODZENIE
            references WYNAGRODZENIA,
    ID_STANOWISKA    NUMBER       not null
        constraint RELATIONSHIP2
            references STANOWISKA
)
/

create index IX_ID_PRACOWNIKA
    on PRACOWNICY (ID_PRACOWNIKA)
/

create index IX_RELATIONSHIP2
    on PRACOWNICY (ID_STANOWISKA)
/

alter table PRACOWNICY
    add constraint PRACOWNIK_PK
        primary key (ID_PRACOWNIKA)
/

create trigger TS_PRACOWNICY_ID_PRACOWNIKA_S
    before insert
    on PRACOWNICY
    for each row
BEGIN
  :new.id_pracownika := id_pracownika_s.nextval;
END;
/

create trigger TSU_PRACOWNICY_ID_PRACOWNIKA_S
    after update of ID_PRACOWNIKA
    on PRACOWNICY
    for each row
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column id_pracownika in table Pracownicy as it uses sequence.');
END;
/

create trigger TS_WYNAGRODZENIA_ID_WYNAGRODZENIA_S
    before insert
    on WYNAGRODZENIA
    for each row
BEGIN
  :new.id_wynagrodzenia := id_wynagrodzenia_s.nextval;
END;
/

create trigger TSU_WYNAGRODZENIA_ID_WYNAGRODZENIA_S
    after update of ID_WYNAGRODZENIA
    on WYNAGRODZENIA
    for each row
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column id_wynagrodzenia in table Wynagrodzenia as it uses sequence.');
END;
/

create table OBSLUGA_INSTRUMENTOW
(
    ID_INSTRUMENTU NUMBER not null
        constraint INSTRUMENT_JEST_OBSLUGIWANY
            references INSTRUMENTY,
    ID_ARTYSTY     NUMBER not null
        constraint ARTYSTA_OBSLUGUJE
            references ARTYSCI,
    constraint PK_OBSLUGA_INSTRUMENTOW
        primary key (ID_INSTRUMENTU, ID_ARTYSTY)
)
/

