--------------------------*APPOINTMENT*----------------------------------------

1.POST http://localhost:8080/appointment/
body:
{
"doctorUsername":"Toyota",
"pacientUsername":"Vasile",
"date":"1998-05-21",
"begin":"14:18",
"end":"23:19"
}

2.GET http://localhost:8080/appointment/filter?doctor=Ana&startDate=2023-06-16&endDate=2024-06-16
Parametru doctor : OBLIGATORIU, valoarea NU poate sa lipseasca
Parametru startDate : OBLIGATORIU, valoarea poate sa lipseasca
Parametru endDate : OBLIGATORIU, valoarea poate sa lipseasca

3.GET http://localhost:8080/appointment/all

--------------------------*PACIENT*----------------------------------------

1.POST http://localhost:8080/pacient/
{   
"userName":"Tony",
"password":"passw"
}

2.GET http://localhost:8080/pacient/all

---------------------------*DOCTOR*----------------------------------------

1.POST http://localhost:8080/doctor/
{   
"userName":"Tony",
"password":"passw"
}

2.GET http://localhost:8080/doctor/all

3.POST http://localhost:8080/doctor/hospital/add
{
"doctorUsername":"Ana",
"hospitalName":"Spitalul Clinic de Psihiatrie"
}

4.POST http://localhost:8080/doctor/hospital/remove
{
"doctorUsername":"Ana",
"hospitalName":"Spitalul Clinic de Psihiatrie"
}

---------------------------------*LOGIN*---------------------------------

POST http://localhost:8080/user/login?role=DOCTOR
{
"userName":"Cristi",
"password":"aaa"
}

POST http://localhost:8080/user/login?role=PACIENT
{
"userName":"Cristi",
"password":"aaa"
}


---------------------------*HOSPITAL*----------------------------------------

1. GET http://localhost:8080/hospitals/all


1. POST http://localhost:8080/hospitals/
{
   "name": "Spitalul Clinic Județean de Urgență Cluj-Napoca",
   "adress": "Strada Clinicilor, nr. 3-5",
   "latitude": 46.7659412,
   "longitude": 23.5831731,
   "reviews": [
   {
   "rating": 4.5,
   "review_text": "asddjh"
   }
   ]
}
2. DELETE http://localhost:8080/hospitals/delete/Spitalul-Clinic-Județea-de-Urgență-Cluj-Napoca

3. GET http://localhost:8080/hospitals/Spitalul-Clinic-Județean-de-Urgență-Cluj-Napoca

4. PATCH http://localhost:8080/hospitals/update/Spitalul-Clinic-Județean-de-Urgență-Cluj-Napoca

{
   "adress": "adresa noua din update"
}

---------------------------*REVIEW*----------------------------------------
1. GET http://localhost:8080/review/all
2. POST http://localhost:8080/review/
   {
   "review_text": "The hospital provides excellent care.",
   "rating": 4.8
   }
3. DELETE http://localhost:8080/review/delete/{id}
4. PATCH http://localhost:8080/review/update/{id}
   {
   "review_text": "Updated review text.",
   "rating": 4.9
   }
