/**
 * Author:  Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * Created: 16/02/2017
 */

CREATE TABLE Contact(
    id SERIAL NOT NULL,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    number TEXT NULL NULL,
    PRIMARY KEY(id)
);