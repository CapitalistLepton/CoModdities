DROP TABLE IF EXISTS Companies;
CREATE TABLE Companies (
  cid INT PRIMARY KEY,
  name VARCHAR(255),
  balance FLOAT
);

DROP TABLE IF EXISTS Factories;
CREATE TABLE Factories (
  fid INT PRIMARY KEY,
  name VARCHAR(255),
  rid INT REFERENCES Recipes(rid)
);

DROP TABLE IF EXISTS FactoryList;
CREATE TABLE FactoryList (
  cid INT REFERENCES Companies(cid),
  fid INT REFERENCES Factories(fid)
);

DROP TABLE IF EXISTS Recipes;
CREATE TABLE Recipes (
  rid INT PRIMARY KEY,
  output INT REFERENCES Resources(eid),
  output_amount INT
);

DROP TABLE IF EXISTS Requirements;
CREATE TABLE Requirements (
  rid INT REFERENCES Recipes(rid),
  eid INT REFERENCES Resources(eid),
  amount INT
);

DROP TABLE IF EXISTS Resources;
CREATE TABLE Resources (
  eid INT PRIMARY KEY,
  name VARCHAR(255),
  symbol VARCHAR(5),
  price FLOAT
);
