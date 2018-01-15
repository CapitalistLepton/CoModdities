INSERT INTO Resources VALUES (0, 'Lumber', 'LUM', 1.0),
  (1, 'Iron', 'IRO', 10.0),
  (2, 'Stone', 'STO', 1.0),
  (3, 'Steel', 'STL', 30.0),
  (4, 'Coal', 'COA', 15.0),
  (5, 'Charcoal', 'CHA', 8.0),
  (6, 'Pig Iron', 'PIG', 25.0);

INSERT INTO Recipes VALUES (0, 3, 2),
  (1, 6, 1),
  (2, 5, 1);

INSERT INTO Factories VALUES (0, 'Steel Factory', 0),
  (1, 'Pig Iron Refinery', 1),
  (2, 'Charcoal Manufactory', 2);

INSERT INTO Requirements VALUES (0, 4, 1),
  (0, 1, 3),
  (1, 5, 2),
  (1, 1, 3),
  (2, 0, 2);
