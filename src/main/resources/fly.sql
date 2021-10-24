-- 1.Գտնել Paris-London չվերթ իրականացնող ավիաընկերությունների
-- համարները:   Կրկնություններից ազատվել:
select distinct trip_no
from trip
where town_from = 'Paris'
  and town_to = 'London';

-- 2.Գտնել յուրաքանչյուր չվերթի թռիչքի օրերը: Կարգավորել տողերը
-- ըստ չվերթի համարի և ամսաթվի
select trip_no, date
from pass_in_trip
order by trip_no, date;

-- 3.Գտնել այն ուղևորներին, որոնց ազգանունը (2-րդ անունը )
-- սկսվում է “B” տառով
select name
from passenger
where name like '% B%';

-- 4.Գտնել յուրաքանչյուր չվերթի, յուրաքանչյուր օրվա ուղղևորների քանակը
select count(name) as name, date, pit.trip_no
from trip
         cross join pass_in_trip pit on trip.trip_no = pit.trip_no
         cross join passenger p on p.ID_psg = pit.ID_psg
group by date, trip.trip_no;

-- 5.Գտնել ուղևորների ID-ները, որոնք 2 և ավելի անգամ թռիչք են կատարել
select distinct ID_psg, count(ID_psg)
from pass_in_trip
group by ID_psg
having count(ID_psg) > 1;

-- 6.Գտնել չվերթների ուղղությունները և այդ չվերթները իրականացնող
-- ավիաընկերությունների անունները
select town_from, town_to, c.name
from trip
         left join company c on trip.ID_comp = c.ID_comp;

-- 7.Գտնել այն ուղևորների համարներն ու անունները, որոնք օգտվել
-- են 2 և ավելի ավիաընկերությունների ծառայությունից
select p.id_psg, p.name
from trip
         inner join pass_in_trip pit on trip.trip_no = pit.trip_no
         inner join passenger p on p.id_psg = pit.id_psg
group by p.id_psg, p.name
having (count(distinct id_comp) >= 2);


-- 8 --Գտնել յուրաքանչյուր ավիաընկերության, յուրաքանչյուր օրվա տեղափոխած ուղևորների քանակը
select date, count(id_psg), t.id_comp
from pass_in_trip
         inner join trip t on t.trip_no = pass_in_trip.trip_no
group by date, id_comp;

-- 9. Գտնել չվերթների հնարավոր բոլոր ուղղույթունը
SELECT distinct a.town_from, b.town_to
FROM trip a
         cross JOIN trip b
where a.town_from != b.town_to;

-- 10․ Գտնել քաղաքները , որտեղ կարելի է հասնել Paris-ից մեկ տրանզիտի միջոցով:
select distinct trip.town_to
from trip
where town_from in (select town_from from trip where town_to = 'Paris')
  and trip.town_to != 'Paris';

-- 11․ Գտնել․ ուղևորների համարները, անունները, թռիչքի օրերը և ուղղությունները:
-- Կարգավորել տողելը ըստ ուղևորի համարի և ամսաթվի աճման կարգով:
select passenger.ID_psg, passenger.name, pit.date, t.town_from, t.town_to
from passenger
         join pass_in_trip pit on passenger.ID_psg = pit.ID_psg
         join trip t on t.trip_no = pit.trip_no
order by passenger.ID_psg, date;
-- 12․ Գտնել այն ուղևորների համարները, անունները, որոնք նույն ուղղությամբ թռել են
 -- առնվազն 2 անգամ:
select distinct passenger.ID_psg, passenger.name
from passenger
         join pass_in_trip pit on passenger.ID_psg = pit.ID_psg
         join trip t on t.trip_no = pit.trip_no
group by name, town_to
having count(name) > 1
   and count(town_to) > 1;

-- 13.Գտնել որ չվերթով քանի  թռիչք է կարաևվել 05/03/2003 ից մինչև 30/03/2003 ընկած ժամանակա-հատվածում:
-- 0-ները  նույնպես ընգրկել պատասխանում
select trip_no,
                ifnull(count(trip_no), 0)
                    as quantity
from pass_in_trip
where date >= '2003-04-05'
  and date <= '2003-04-30'
group by trip_no
having count(trip_no)=0;



-- 14.Գտնել ավիաընկերությունները (նաև անուները), որոնք մինիմալ թվով
-- չվերթներ ունեն:

select company.ID_comp, company.name
from company
         join trip t on company.ID_comp = t.ID_comp
group by t.ID_comp
having count(t.ID_comp) = 2;