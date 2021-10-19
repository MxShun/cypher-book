CREATE SCHEMA IF NOT EXISTS cypher;

CREATE TABLE IF NOT EXISTS cypher.book(
    isbn varchar(13) NOT NULL,
    title varchar(100) NOT NULL,
    author varchar(100) NOT NULL,
    publisher varchar(100) NOT NULL,
    price integer NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,

    PRIMARY KEY (isbn)
);

INSERT INTO cypher.book(isbn, title, author, publisher, price, created_at, updated_at) VALUES
    ('9780596802295', 'The Art of Readable Code', 'Dustin Boswell, Trevor Foucher', 'O''Reilly Media, Inc.', 4174, '2011-11-29', '2011-11-29 09:09:09.09'),
    ('9784274226298', 'The Pragmatic Programmer', 'David Thomas, Andrew Hunt', 'Addison-Wesley Professional', 3520, '2000-11-01', '2020-11-21 10:10:10.1'),
    ('9780321146533', 'Test-Driven Development By Example', 'Kent Beck', 'Addison-Wesley Professional', 4808, '2002-11-08', '2002-11-08 12:12:12.12'),
    ('9780134686097', 'Effective Java', 'Joshua Bloch', 'Addison-Wesley Professional', 4400, '2001-12-03', '2018-10-30 18:18:18.18'),
    ('9780201616415', 'Extreme Programming Explained', 'Kent Beck', 'Addison-Wesley Professional', 1775, '1999-09-01', '2015-06-26  20:20:20.2'),
    ('9784774190877', '現場で役立つシステム設計の原則 〜変更を楽で安全にするオブジェクト指向の実践技法', '増田亨', '技術評論社', 3234, '2017-07-05', '2017-07-05  22:22:22.22')
;
