DROP TABLE if exists products;
DROP TABLE if exists linkMinorMain;
DROP TABLE if exists minorCategories;
DROP TABLE if exists mainCategories;


CREATE TABLE mainCategories (
	categoryid INTEGER not null AUTO_INCREMENT unique,
    mainCategoryName VARCHAR(45),
    primary key (categoryid)
);

CREATE TABLE minorCategories (
	categoryid INTEGER not null AUTO_INCREMENT unique,
    minorCategoryName VARCHAR(45),
    primary key (categoryid)
);

CREATE TABLE linkMinorMain (
	mainid INT NOT NULL,
    minorid INT NOT NULL,
    FOREIGN KEY (mainid) REFERENCES mainCategories(categoryid),
    FOREIGN KEY (minorid) REFERENCES minorCategories(categoryid)
);


CREATE TABLE products (
    productid INTEGER not null unique,
    name VARCHAR(45) not null,
    nameDescription VARCHAR(80),
    description VARCHAR(2000),
    companyName VARCHAR(45),
    price DOUBLE,
    quantity INTEGER,
    pictureName VARCHAR(45),
    publishedStatus tinyint,
    minorCategory INTEGER,
    mainCategory INTEGER,
    FOREIGN KEY (mainCategory) REFERENCES mainCategories(categoryid),
    FOREIGN KEY (minorCategory) REFERENCES minorCategories(categoryid)
);
