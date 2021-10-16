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
    ('9780596802295', 'The Art of Readable Code', 'Dustin Boswell, Trevor Foucher', 'O''Reilly Media, Inc.', 4174, '2021-11-29', '2021-11-29'),
    ('9784274226298', 'The Pragmatic Programmer', 'David Thomas, Andrew Hunt', 'Addison-Wesley Professional', 3520, '2000-11-01', '2020-11-21'),
    ('9780321146533', 'Test-Driven Development By Example', 'Kent Beck', 'Addison-Wesley Professional', 4808, '2002-11-08', '2002-11-08'),
    ('9780134686097', 'Effective Java', 'Joshua Bloch', 'Addison-Wesley Professional', 4400, '2001-12-03', '2018-10-30'),
    ('9780201616415', 'Extreme Programming Explained', 'Kent Beck', 'Addison-Wesley Professional', 1775, '1999-09-01', '2015-06-26')
;

