Sovelluksessa on kaksi esiasetettua käyttäjää:

Käyttäjä:
Käyttäjätunnus: user
Salasana: userpass

Ylläpitäjä:
Käyttäjätunnus: admin
Salasana: adminpass

Kirjautumisen jälkeen:

Käyttäjä (USER) pääsee etusivulle ja mahdollisesti muihin user-polkuihin.

Ylläpitäjä (ADMIN) pääsee lisäksi ylläpitosivulle osoitteessa /admin




Etusivu sisältää:

Tervetulotekstin valitulla kielellä

Ohjeet käyttöön (lokalisoidut)

Footerin, jossa yrityksen nimi

Etusivulla voit valita kielen (suomi / englanti) oikeasta yläkulmasta.

Valinta tallennetaan selaimen istuntoon ja säilyy sivun päivityksen ajan.



Roolipohjainen pääsy
/admin: vain ADMIN-roolilla pääsy

/user/: USER ja ADMIN -rooleilla pääsy

Muille poluille kirjautuminen vaaditaan



Henkilöt-sivu
Tällä sivulla voit tarkastella henkilöiden tietoja. Tarjolla on seuraavat toiminnot:

Haku/suodatus: Voit suodattaa henkilöitä nimellä ja osoitteella kirjoittamalla hakukenttiin osan nimestä tai osoitteesta.

Lajittelu: Voit järjestää henkilöt ID:n, nimen tai osoitteen mukaan nousevaan tai laskevaan järjestykseen napsauttamalla sarakeotsikoita.



Mittaukset-sivu
Sivulla näytetään mittausdataa, kuten paino, lämpötila ja pulssi.

Lajittelu: Voit järjestää mittauksia mittaustyypin, arvon, päivämäärän tai henkilön mukaan napsauttamalla sarakeotsikoita.


Käyttäjäsivu
Tämä on yleinen tervetulonäkymä joka näkyy jokaiselle käyttäjälle.

Ylläpitosivu
Tämä on hallintanäkymä joka näkyy vain admineille.

taulujen luonti:
USE harjoitustyo;

-- Taulu: henkilo
CREATE TABLE henkilo (
    id INT PRIMARY KEY,
    nimi VARCHAR(100) NOT NULL,
    osoite VARCHAR(255)
);

-- Taulu: profiili
CREATE TABLE profiili (
    id INT PRIMARY KEY,
    kuvaus VARCHAR(100),
    henkilo_id INT,
    FOREIGN KEY (henkilo_id) REFERENCES henkilo(id)
);

-- Taulu: mittaus
CREATE TABLE mittaus (
    id INT PRIMARY KEY,
    tyyppi VARCHAR(50),
    arvo VARCHAR(50),
    pvm DATE,
    henkilo_id INT,
    FOREIGN KEY (henkilo_id) REFERENCES henkilo(id)
);

-- Taulu: kayttaja
CREATE TABLE kayttaja (
    id INT PRIMARY KEY,
    kayttajatunnus VARCHAR(50) UNIQUE NOT NULL,
    salasana VARCHAR(255) NOT NULL
);

-- Taulu: kayttaja_roolit
CREATE TABLE kayttaja_roolit (
    kayttaja_id INT,
    roolit VARCHAR(50),
    FOREIGN KEY (kayttaja_id) REFERENCES kayttaja(id)
);

-- Spring Securityn käyttäjätaulut
CREATE TABLE users (
    username VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE roles (
    username VARCHAR(255),
    role VARCHAR(255),
    FOREIGN KEY (username) REFERENCES users(username)
);



testidata:
-- Henkilöt
INSERT INTO henkilo (id, nimi, osoite) VALUES
(1, 'Matti Meikäläinen', 'Testikatu 1'),
(2, 'Liisa Testaaja', 'Katu 123'),
(3, 'John Doe', 'Unknown Street');

-- Profiilit
INSERT INTO profiili (id, kuvaus, henkilo_id) VALUES
(1, 'Aktiivinen', 1),
(2, 'Passiivinen', 2),
(3, 'Normaali', 3);

-- Mittaukset
INSERT INTO mittaus (id, tyyppi, arvo, pvm, henkilo_id) VALUES
(1, 'Paino', '70kg', '2024-01-01', 1),
(2, 'Lämpötila', '36.5', '2024-01-02', 1),
(3, 'Paino', '80kg', '2024-02-01', 2),
(4, 'Pulssi', '60', '2024-02-15', 3);

-- Käyttäjät (sovelluksen oma käyttö)
INSERT INTO kayttaja (id, kayttajatunnus, salasana) VALUES
(1, 'admin', 'adminsalasana'),
(2, 'user', 'usersalasana'),
(3, 'testi', 'testisalasana');

INSERT INTO kayttaja_roolit (kayttaja_id, roolit) VALUES
(1, 'ADMIN'),
(2, 'USER'),
(3, 'USER'),
(3, 'ADMIN');

-- Spring Securityin oletuskäyttäjät
INSERT INTO users (username, password, enabled) VALUES
('user', 'password', true),
('admin', 'adminpass', true);

INSERT INTO roles (username, role) VALUES
('user', 'ROLE_USER'),
('admin', 'ROLE_ADMIN');



