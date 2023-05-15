# MapleStory Event Item Finder
This project is a program that shows which character owns the chairs, mount, and damage skins given as events in MapleStory. It uses MariaDB, and the client is written in Java.
## Introduction
There are various event items in MapleStory, such as chairs, mounts, and damage skins. These items can be obtained through summer and winter events, anniversary events, and other events such as the Fairy Bros' Golden Carriage.

There are hundreds of different chairs, mounts, and damage skins, and when you receive an item that you already have, it goes into your inventory in an untradeable form. Therefore, many users either try to remember all the items they have or record them in programs like Excel.

However, since there are too many items to remember, it is impossible to remember them all, and recording them in Excel or similar programs becomes difficult as the number of characters increases.
>The picture is a collection of damage skin icons up until
                            2020.
![뎀스](https://github.com/yeganghwang/maplestory_Event_Item/assets/101787202/5d220d68-3108-4e5f-9326-e8b544fa6ce4)

Let's say that collections of dozens of characters with three types of items each, including damage skins, mounts, and chairs, are stored in multiple worlds. This is a really large amount of data. To handle this, I decided to store it in a database.


This project aims to create a database to record event items and develop a client that makes it easy to manage them.



## Database Structure

First, I need to create a database to store the characters who have chairs, ridings, and damage skins, each of which has dozens of different variations and belongs to multiple worlds. I will need to store their names, the worlds they belong to, their jobs, and levels.

#### Table : character_table
|Field| Type | Null | Key |
|--|--|--|--|
| name | char[100] | NO | PRI
| world | char[100] | NO |
| job | char[100] | NO |
| level | int | YES |

Next, I will create tables for chairs, ridings, and damage skins. Each table will have the character's name as a foreign key, and the combination of the item name and character name will create the primary key. (Note: a character cannot have duplicate ridings or damage skins, but they can have multiple chairs. For simplicity, I will use "name-item" as the primary key for all tables. Whether a character has multiple instances of the same chair is not important for our purposes.) I have not included tables for acquisition methods for each item, but they could be added later if necessary.

#### Table : chair_table
|Field| Type | Null | Key |
|--|--|--|--|
| name | char[100] | NO | Part of PRI, FRN(REF character_table)
| chair | char[100] | NO | Part of PRI

#### Table : riding_table
|Field| Type | Null | Key |
|--|--|--|--|
| name | char[100] | NO | Part of PRI, FRN(REF character_table)
| riding | char[100] | NO | Part of PRI

Note: In my country's version of MapleStory, "Mount" is expressed as "Riding", so I have used "Riding" as the name of the database and source codes. Please keep this in mind.

#### Table : damageskin_table
|Field| Type | Null | Key |
|--|--|--|--|
| name | char[100] | NO | Part of PRI, FRN(REF character_table)
| skin | char[100] | NO | Part of PRI

Now the SQL queries in the code are working well.
- - -
# Usage
<img width="562" alt="mainui" src="https://github.com/yeganghwang/maplestory_Event_Item/assets/101787202/9def6b5a-7358-43c7-9e81-0c8b80127db3">

<img width="626" alt="searchui" src="https://github.com/yeganghwang/maplestory_Event_Item/assets/101787202/5a6c85ea-8dc9-430e-9c36-0e865bd3870e">

<img width="562" alt="putdataui" src="https://github.com/yeganghwang/maplestory_Event_Item/assets/101787202/cf57fb25-7258-4010-b864-5747cfbc4770">

### Connect DB
First, please check the dbConnecter.java file in the control directory. This java file is a connector that connects the program and the database server. The server information is stored in (example)DBInfo.txt, which is an example. Please modify it to your server content and rename it to DBInfo.txt.

    127.0.0.1
    maplestory_db
    root
    P@ssw0rd
# Reference
- https://github.com/Neonlab-kr/Software-Engineering-A4-POS
	I referred to SWING UI and DB Connection and Queries for this project.


    
