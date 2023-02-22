CREATE TABLE admin (
    id               INTEGER NOT NULL,
    imie             VARCHAR2(32 BYTE) NOT NULL,
    nazwisko         VARCHAR2(64 BYTE) NOT NULL,
    data_urodzenia   DATE,
    telefon          VARCHAR2(15 BYTE) NOT NULL,
    email            VARCHAR2(32 BYTE),
    login            VARCHAR2(32 BYTE) NOT NULL,
    haslo            VARCHAR2(32 BYTE) NOT NULL
);

ALTER TABLE admin ADD CONSTRAINT admin_pk PRIMARY KEY ( id );

ALTER TABLE admin ADD CONSTRAINT admin_login_un UNIQUE ( login );

CREATE TABLE choroba (
    id                  INTEGER NOT NULL,
    karta_pacjenta_id   INTEGER NOT NULL,
    data                DATE NOT NULL,
    rozpoznanie         VARCHAR2(256 BYTE) NOT NULL,
    leki                VARCHAR2(1024 BYTE),
    historia_choroby    VARCHAR2(2048 BYTE)
);

ALTER TABLE choroba ADD CONSTRAINT choroba_pk PRIMARY KEY ( id );

CREATE TABLE harmonogram (
    id             INTEGER NOT NULL,
    lekarz_id      INTEGER NOT NULL,
    poniedzialek   VARCHAR2(16 BYTE),
    wtorek         VARCHAR2(16 BYTE),
    sroda          VARCHAR2(16 BYTE),
    czwartek       VARCHAR2(16 BYTE),
    piatek         VARCHAR2(16 BYTE),
    typ            CHAR(1 BYTE) NOT NULL
);

CREATE UNIQUE INDEX harmonogram__idx ON
    harmonogram (
        lekarz_id
    ASC );

ALTER TABLE harmonogram ADD CONSTRAINT harmonogram_pk PRIMARY KEY ( id );

CREATE TABLE karta_pacjenta (
    id           INTEGER NOT NULL,
    pacjent_id   INTEGER NOT NULL,
    wzrost       INTEGER NOT NULL,
    waga         INTEGER NOT NULL
);

CREATE UNIQUE INDEX karta_pacjenta__idx ON
    karta_pacjenta (
        pacjent_id
    ASC );

ALTER TABLE karta_pacjenta ADD CONSTRAINT karta_pacjenta_pk PRIMARY KEY ( id );

CREATE TABLE lekarz (
    id               INTEGER NOT NULL,
    imie             VARCHAR2(32 BYTE) NOT NULL,
    nazwisko         VARCHAR2(64 BYTE) NOT NULL,
    data_urodzenia   DATE,
    telefon          VARCHAR2(15 BYTE) NOT NULL,
    email            VARCHAR2(32 BYTE),
    specjalizacja    VARCHAR2(64 BYTE) NOT NULL,
    nr_pokoju        INTEGER,
    login            VARCHAR2(32 BYTE) NOT NULL,
    haslo            VARCHAR2(32 BYTE) NOT NULL
);

ALTER TABLE lekarz ADD CONSTRAINT lekarz_pk PRIMARY KEY ( id );

ALTER TABLE lekarz ADD CONSTRAINT lekarz_login_un UNIQUE ( login );

CREATE TABLE pacjent (
    id                  INTEGER NOT NULL,
    imie                VARCHAR2(32 BYTE) NOT NULL,
    nazwisko            VARCHAR2(64 BYTE) NOT NULL,
    nazwisko_rodowe     VARCHAR2(64 BYTE),
    data_urodzenia      DATE NOT NULL,
    pesel               VARCHAR2(11 BYTE) NOT NULL,
    telefon             VARCHAR2(15 BYTE) NOT NULL,
    email               VARCHAR2(32 BYTE),
    miejsce_urodzenia   VARCHAR2(64 BYTE) NOT NULL,
    adres               VARCHAR2(256 BYTE) NOT NULL,
    plec                CHAR(1 BYTE),
    login               VARCHAR2(32 BYTE) NOT NULL,
    haslo               VARCHAR2(32 BYTE) NOT NULL
);

ALTER TABLE pacjent ADD CONSTRAINT pacjent_pk PRIMARY KEY ( id );

ALTER TABLE pacjent ADD CONSTRAINT pacjent_login_un UNIQUE ( login );

CREATE TABLE pielegniarka (
    id               INTEGER NOT NULL,
    imie             VARCHAR2(32 BYTE) NOT NULL,
    nazwisko         VARCHAR2(64 BYTE) NOT NULL,
    data_urodzenia   DATE,
    telefon          VARCHAR2(15 BYTE) NOT NULL,
    email            VARCHAR2(32 BYTE),
    login            VARCHAR2(32 BYTE) NOT NULL,
    haslo            VARCHAR2(32 BYTE) NOT NULL
);

ALTER TABLE pielegniarka ADD CONSTRAINT pielegniarka_pk PRIMARY KEY ( id );

ALTER TABLE pielegniarka ADD CONSTRAINT pielegniarka_login_un UNIQUE ( login );

CREATE TABLE recepcjonistka (
    id               INTEGER NOT NULL,
    imie             VARCHAR2(32 BYTE) NOT NULL,
    nazwisko         VARCHAR2(64 BYTE) NOT NULL,
    data_urodzenia   DATE,
    telefon          VARCHAR2(15 BYTE) NOT NULL,
    email            VARCHAR2(32 BYTE),
    login            VARCHAR2(32 BYTE) NOT NULL,
    haslo            VARCHAR2(32 BYTE) NOT NULL
);

ALTER TABLE recepcjonistka ADD CONSTRAINT recepcjonistka_pk PRIMARY KEY ( id );

ALTER TABLE recepcjonistka ADD CONSTRAINT recepcjonistka_login_un UNIQUE ( login );

CREATE TABLE recepta (
    id                 INTEGER NOT NULL,
    pacjent_id         INTEGER NOT NULL,
    lekarz_id          INTEGER NOT NULL,
    data_wystawienia   DATE NOT NULL,
    nr_recepty         VARCHAR2(16 BYTE) NOT NULL,
    leki               VARCHAR2(1024 BYTE) NOT NULL,
    czy_zrealizowana   CHAR(1) NOT NULL
);

ALTER TABLE recepta ADD CONSTRAINT recepta_pk PRIMARY KEY ( id );

CREATE TABLE skierowanie (
    id                 INTEGER NOT NULL,
    pacjent_id         INTEGER NOT NULL,
    lekarz_id          INTEGER NOT NULL,
    data_skierowania   DATE NOT NULL,
    rodzaj             VARCHAR2(64 BYTE) NOT NULL,
    specjalizacja      VARCHAR2(64 BYTE),
    rozpoznanie        VARCHAR2(256 BYTE),
    lista_badan        VARCHAR2(1024 BYTE),
    czy_wewnetrzne     CHAR(1) NOT NULL
);

ALTER TABLE skierowanie ADD CONSTRAINT skierowanie_pk PRIMARY KEY ( id );

CREATE TABLE wizyta (
    id                 INTEGER NOT NULL,
    pacjent_id         INTEGER NOT NULL,
    lekarz_id          INTEGER NOT NULL,
    typ_wizyty         VARCHAR2(32 BYTE) NOT NULL,
    nr_pokoju          INTEGER,
    data               DATE NOT NULL,
    godzina            DATE NOT NULL,
    numerek            INTEGER,
    czy_potwierdzona   CHAR(1) NOT NULL,
    czy_odwolana       CHAR(1) NOT NULL,
    czy_odbyta         CHAR(1) NOT NULL,
    opis               VARCHAR2(512 BYTE)
);

ALTER TABLE wizyta ADD CONSTRAINT wizyta_pk PRIMARY KEY ( id );

CREATE TABLE zabieg (
    id                INTEGER NOT NULL,
    pacjent_id        INTEGER NOT NULL,
    pielegniarka_id   INTEGER NOT NULL,
    skierowanie_id    INTEGER NOT NULL,
    data              DATE NOT NULL,
    rodzaj_zabiegu    VARCHAR2(256 BYTE) NOT NULL,
    opis_zabiegu      VARCHAR2(2048 BYTE)
);

ALTER TABLE zabieg ADD CONSTRAINT zabieg_pk PRIMARY KEY ( id );

ALTER TABLE choroba
    ADD CONSTRAINT choroba_karta_pacjenta_fk FOREIGN KEY ( karta_pacjenta_id )
        REFERENCES karta_pacjenta ( id );

ALTER TABLE harmonogram
    ADD CONSTRAINT harmonogram_lekarz_fk FOREIGN KEY ( lekarz_id )
        REFERENCES lekarz ( id );

ALTER TABLE karta_pacjenta
    ADD CONSTRAINT karta_pacjenta_pacjent_fk FOREIGN KEY ( pacjent_id )
        REFERENCES pacjent ( id );

ALTER TABLE recepta
    ADD CONSTRAINT recepta_lekarz_fk FOREIGN KEY ( lekarz_id )
        REFERENCES lekarz ( id );

ALTER TABLE recepta
    ADD CONSTRAINT recepta_pacjent_fk FOREIGN KEY ( pacjent_id )
        REFERENCES pacjent ( id );

ALTER TABLE skierowanie
    ADD CONSTRAINT skierowanie_lekarz_fk FOREIGN KEY ( lekarz_id )
        REFERENCES lekarz ( id );

ALTER TABLE skierowanie
    ADD CONSTRAINT skierowanie_pacjent_fk FOREIGN KEY ( pacjent_id )
        REFERENCES pacjent ( id );

ALTER TABLE wizyta
    ADD CONSTRAINT wizyta_lekarz_fk FOREIGN KEY ( lekarz_id )
        REFERENCES lekarz ( id );

ALTER TABLE wizyta
    ADD CONSTRAINT wizyta_pacjent_fk FOREIGN KEY ( pacjent_id )
        REFERENCES pacjent ( id );

ALTER TABLE zabieg
    ADD CONSTRAINT zabieg_pacjent_fk FOREIGN KEY ( pacjent_id )
        REFERENCES pacjent ( id );

ALTER TABLE zabieg
    ADD CONSTRAINT zabieg_pielegniarka_fk FOREIGN KEY ( pielegniarka_id )
        REFERENCES pielegniarka ( id );

ALTER TABLE zabieg
    ADD CONSTRAINT zabieg_skierowanie_fk FOREIGN KEY ( skierowanie_id )
        REFERENCES skierowanie ( id );