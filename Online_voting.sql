CREATE DATABASE IF NOT EXISTS online_voting;

USE online_voting;

CREATE TABLE IF NOT EXISTS voters (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    age INT,
    voter_id INT UNIQUE
);

CREATE TABLE IF NOT EXISTS candidates (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    party VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS votes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    voter_id INT,
    candidate_id INT,
    FOREIGN KEY (voter_id) REFERENCES voters(id),
    FOREIGN KEY (candidate_id) REFERENCES candidates(id)
);
