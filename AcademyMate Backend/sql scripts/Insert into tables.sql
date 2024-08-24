use academymate;


insert into instructor_detail values(1, 'Onyx_Gaming', 'Watching Sci-Fi movies');
insert into instructor_detail values(2, 'Gameron', 'Playing Video Games');
insert into instructor_detail values(3, 'Physco_Path', 'Watching K-Drama');
insert into instructor_detail values(4, 'Sadhika_Teaches', 'Nothing');
SELECT * FROM instructor_detail;


insert into instructor values (1, 'Vikas', 'Seervi', 'vikas@seervi', 1);
insert into instructor values (2, 'Yash', 'Prakash', 'yash@prakash', 2);
insert into instructor values (3, 'Abdul', 'Ahad', 'abdul@ahad', 3);
insert into instructor values (4, 'Sadhika', 'Readdy', 'sadhika@reddy', 4);
SELECT * FROM instructor;


insert into course values(1, 'Java', 1);
insert into course values(2, 'Python', 4);
insert into course values(3, 'C', 3);
insert into course values(4, 'C++', 3);
insert into course values(5, 'Cyber Security', 2);
insert into course values(6, 'React JS', 1);
insert into course values(7, 'JavaScript', 4);
insert into course values(8, 'SpringBoot', 1);

SELECT * FROM course;


desc student;
insert into student values (1, 'vikas', 'da_bachha', 'vikku@mail.com');
insert into student values (2, 'yash', 'da_bachha', 'yashu@mail.com');
insert into student values (3, 'abdul', 'da_bachha', 'abdu@mail.com');
insert into student values (4, 'pratibha', 'da_bachha', 'prathu@mail.com');
insert into student values (5, 'anoop', 'da_bachha', 'anuup@mail.com');
insert into student values (6, 'kalyaan', 'da_bachha', 'kallu@mail.com');
insert into student values (7, 'vishnu', 'da_bachha', 'chisnu@mail.com');
insert into student values (8, 'tushar', 'da_bachha', 'tushu@mail.com');
insert into student values (9, 'himanshu', 'da_bachha', 'himu@mail.com');
insert into student values (10, 'kriti', 'da_bachha', 'kritu@mail.com');
insert into student values (11, 'nikitha', 'da_bachha', 'nikku@mail.com');

SELECT * FROM academymate.student;



insert into course_student values(1, 1);
insert into course_student values(2, 1);
insert into course_student values(6, 1);
insert into course_student values(5, 2);
insert into course_student values(2, 3);
insert into course_student values(3, 3);
insert into course_student values(4, 3);
insert into course_student values(3, 4);
insert into course_student values(1, 5);
insert into course_student values(3, 5);
insert into course_student values(1, 6);
insert into course_student values(3, 7);
insert into course_student values(4, 8);
insert into course_student values(1, 8);
insert into course_student values(4, 9);
insert into course_student values(2, 10);
insert into course_student values(4, 10);
insert into course_student values(1, 11);
insert into course_student values(2, 11);
insert into course_student values(4, 11);


SELECT * FROM course_student;


insert into review values(1, 'Crazy!!!', 1);
insert into review values(2, 'Mast tha course', 1);
insert into review values(3, 'Now i understood y react is prefered', 6);
insert into review values(4, 'Damn man!!!', 5);
insert into review values(5, 'Its my first step in programming World', 3);

select * from review;