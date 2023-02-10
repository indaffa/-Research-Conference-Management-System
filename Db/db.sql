drop database if exists csit314;

create database csit314;

use csit314;

DROP TABLE IF EXISTS `userprofile`;
CREATE TABLE `userprofile` (
	`profileID` int(11) primary key auto_increment, 
	`profilename` varchar(50) not null,
	`description` varchar(50) not null
);


INSERT INTO `userprofile` (`profileID`,`profilename`,`description`) VALUES
(1,"SystemAdmin", "Manage User Accounts and User Profile"),
(2,"Reviewer", "Review Book"),
(3,"Conference", "Allocate papers to reviewer"),
(4,"Author", "Publish his/her paper to be reviewed");

DROP TABLE IF EXISTS `systemadmin`;
CREATE TABLE `systemadmin` (
	`id` int(11) primary key auto_increment, 
	`username` varchar(50) not null,
	`fullname` varchar(50) not null,
	`password` varchar(50) not null,
	`email` varchar(70) not null,
	`profileID` int(11) not null,
	 FOREIGN KEY fk_systemadmin(`profileID`) REFERENCES userprofile(profileID)
	
);

DROP TABLE IF EXISTS `reviewer`;
CREATE TABLE `reviewer` (
	`id` int(11) primary key auto_increment, 
	`username` varchar(50)  not null,
	`fullname` varchar(50) not null,
	`password` varchar(50) not null,
	`email` varchar(70) not null,
	`profileID` int(11),
	`max_no_paper` int(11) default 3, 
	 FOREIGN KEY fk_reviewer(`profileID`) REFERENCES userprofile(profileID)
);

DROP TABLE IF EXISTS `conference`;
CREATE TABLE `conference` (
	`id` int(11) primary key auto_increment, 
	`username` varchar(50) not null,
	`fullname` varchar(50) not null,
	`password` varchar(50) not null,
	`email` varchar(70) not null,
	`profileID` int(11),
	 FOREIGN KEY fk_conference(`profileID`) REFERENCES userprofile(profileID)
);

DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
	`id` int(11) primary key auto_increment, 
	`username` varchar(50)  not null,
	`fullname` varchar(50) not null,
	`password` varchar(50) not null,
	`email` varchar(70) not null,
	`profileID` int(11),
	 FOREIGN KEY fk_author(`profileID`) REFERENCES userprofile(profileID)
);


INSERT INTO `systemadmin` (`username`,`fullname`, `password`, `email` , `profileID`) VALUES
('admin', 'Admin1', 'admin', 'admin1@asda.com', 1),
('reviewer', 'Reviewer1', 'reviewer', 'reviewer1@asda.com', 1),
('cc', 'CC', 'cc', 'cc@asda.com', 1),
('author', 'Author', 'author', 'author1@asda.com', 1);

INSERT INTO `reviewer` (`username`,`fullname`, `password`, `email` , `profileID`, `max_no_paper`) VALUES
('admin', 'Admin1', 'admin', 'admin1@asda.com', 2, 2),
('reviewer', 'Reviewer1', 'reviewer', 'reviewer1@asda.com', 2, 5),
('cc', 'CC', 'cc', 'cc@asda.com', 2, 4),
('author', 'Author', 'author', 'author1@asda.com', 2, 3);

INSERT INTO `conference` (`username`,`fullname`, `password`, `email` , `profileID`) VALUES
('admin', 'Admin1', 'admin', 'admin1@asda.com', 3),
('reviewer', 'Reviewer1', 'reviewer', 'reviewer1@asda.com', 3),
('cc', 'CC', 'cc', 'cc@asda.com', 3),
('author', 'Author', 'author', 'author1@asda.com', 3);

INSERT INTO `author` (`username`,`fullname`, `password`, `email` , `profileID`) VALUES
('admin', 'Admin1', 'admin', 'admin1@asda.com', 4),
('reviewer', 'Reviewer1', 'reviewer', 'reviewer1@asda.com', 4),
('cc', 'CC', 'cc', 'cc@asda.com', 4),
('author', 'Author', 'author', 'author1@asda.com', 4);

DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
	`paper_id` int not null auto_increment primary key,
	 paperName varchar(50) not null,
	`fileContent` longblob not null,
	`Status` varchar(50)not null
);

DROP TABLE IF EXISTS `paperinfo`;
CREATE TABLE `paperinfo` (
	 `paper_id` int not null auto_increment primary key,
	`paperidfk` int(11) not null,
	`Author` int(11) not null,
	`Coauthor` int(11) not null,
	 FOREIGN KEY fk_authorname(`Author`) REFERENCES author(id),
	 FOREIGN KEY fk_coauthorname(`Coauthor`) REFERENCES author(id),
	 FOREIGN KEY fk_paper(`paperidfk`) REFERENCES paper(paper_id)
);


DROP TABLE IF EXISTS `bid`;
CREATE TABLE `bid` (
	 bid_id int not null auto_increment primary key,
	`reviewName` int(11) not null,
	`paperidfk` int(11) not null,
	`allocateStatus` varchar(50) default 'unallocated',
	 FOREIGN KEY fk_reviewbidname(`reviewName`) REFERENCES reviewer(id),
	 FOREIGN KEY fk_paperbidname(`paperidfk`) REFERENCES paper(paper_id)
);

DROP TABLE IF EXISTS `reviews`;
CREATE TABLE `reviews` (
	 review_id int not null auto_increment primary key,
	`bid_id` int (11) not null,
	`reviewContent` varchar(50) ,
	`paperidfk` int(11) not null,
	`reviewer` int(11) not null,
    `rating` int null,
	 FOREIGN KEY fk_bidid(`bid_id`) REFERENCES bid(bid_id),
	 FOREIGN KEY fk_reviewername(`reviewer`) REFERENCES reviewer(id),
	 FOREIGN KEY fk_papername(`paperidfk`) REFERENCES paper(paper_id)
	 
);









