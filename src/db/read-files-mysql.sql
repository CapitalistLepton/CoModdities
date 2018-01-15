LOAD DATA INFILE 'src/db/resources.csv' INTO TABLE Resources
  FIELDS TERMINATED BY ','  LINES STARTING BY '\n';

LOAD DATA INFILE 'src/db/recipes.csv' INTO TABLE Recipes
  FIELDS TERMINATED BY ','  LINES STARTING BY '\n';

LOAD DATA INFILE 'src/db/factories.csv' INTO TABLE Factories
  FIELDS TERMINATED BY ','  LINES STARTING BY '\n';

LOAD DATA INFILE 'src/db/requirements.csv' INTO TABLE Requirements
  FIELDS TERMINATED BY ','  LINES STARTING BY '\n';
