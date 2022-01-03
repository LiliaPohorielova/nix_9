# Для создания базы данных - запусите приложение
# Для заполнения базы данных, используйте затем этот скрипт
USE hw_10_hospital;
insert into doctors values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Mykyta', 'Kuznetsov', 'Victorovich' , 'DENTIST');
insert into doctors values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Ivan', 'Sidorov', 'Aleksandrovich' , 'DENTIST');
insert into doctors values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Sofia', 'Petrova', 'Vyacheslavovna' , 'PEDIATRICIAN');
insert into doctors values (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Petr', 'Ivanov', 'Vasilyevich' , 'SURGEON');
insert into doctors values (5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Dmitrii', 'Petrenko', 'Victorovich' , 'SURGEON');
insert into doctors values (6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Eugeni', 'Kunaev', 'Vasilyevich' , 'PEDIATRICIAN');
insert into doctors values (7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Elina', 'Shatska', 'Mikhaylovna' , 'PHYSICIAN');
insert into doctors values (8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Stanicslav', 'Bovchaluk', 'Yaroslavovich' , 'PHYSICIAN');
insert into doctors values (9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Mykyta', 'Dinovich', 'Vladimirovich' , 'PEDIATRICIAN');
insert into doctors values (10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Arsen', 'Makashvilli', 'Timurovich' , 'CARDIOLOGIST');
insert into doctors values (11, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Dmitrii', 'Prakiv', 'Stepanovich' , 'SURGEON');
insert into doctors values (12, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Ivan', 'Divakov', 'Petrovich' , 'SURGEON');

insert into patients values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 15, 'Mykyta', 'Antonenko');
insert into patients values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 25, 'Vadym', 'Bondarenko');
insert into patients values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 17, 'Kateryna', 'Burtseva');
insert into patients values (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 20, 'Artem', 'Hunchenko');
insert into patients values (5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 21, 'Oleksandr', 'Davydov');
insert into patients values (6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 19, 'Eduard', 'Dasiv');
insert into patients values (7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 22, 'Denys', 'Dovhalin');
insert into patients values (8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 21, 'Iryna', 'Klen');
insert into patients values (9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 24, 'Nataliia', 'Kulyk');
insert into patients values (10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 17, 'Kyrylov', 'Lobano');
insert into patients values (11, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 23, 'Andrii', 'Makhotka');
insert into patients values (12, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 18, 'Anastasiia', 'Mohylevska');
insert into patients values (13, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 29, 'Yaroslav', 'Mulika');
insert into patients values (14, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 20, 'Yelisei', 'Panchenko');
insert into patients values (15, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 18, 'Liliia', 'Pohorielova');
insert into patients values (16, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 21, 'Vladyslav', 'Poliakov');
insert into patients values (17, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 23, 'Daniil', 'Svistelnyk');
insert into patients values (18, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 24, 'Anastasiia', 'Suvorova');
insert into patients values (19, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 22, 'Daniil', 'Tverdokhlib');
insert into patients values (20, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 18, 'Ihor', 'Cherednychenko');
insert into patients values (21, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 20, 'Yehor', 'Shakirov');


insert into declaration values (1, 1);
insert into declaration values (1, 5);
insert into declaration values (1, 10);
insert into declaration values (1, 15);
insert into declaration values (2, 6);
insert into declaration values (2, 11);
insert into declaration values (2, 16);
insert into declaration values (3, 7);
insert into declaration values (3, 12);
insert into declaration values (3, 17);
insert into declaration values (4, 14);
insert into declaration values (5, 5);
insert into declaration values (5, 15);
insert into declaration values (5, 18);
insert into declaration values (5, 16);
insert into declaration values (6, 7);
insert into declaration values (6, 17);
insert into declaration values (6, 20);
insert into declaration values (7, 6);
insert into declaration values (7, 12);
insert into declaration values (7, 17);
insert into declaration values (8, 2);
insert into declaration values (8, 10);
insert into declaration values (8, 20);
insert into declaration values (9, 8);
insert into declaration values (9, 14);
insert into declaration values (9, 18);
insert into declaration values (10, 8);
insert into declaration values (10, 16);
insert into declaration values (10, 19);
insert into declaration values (11, 12);
insert into declaration values (11, 21);
insert into declaration values (11, 8);
insert into declaration values (12, 20);
insert into declaration values (12, 21);