SELECT c.Name AS CountryName
FROM Country c
         JOIN City ci ON c.CountryID = ci.CountryID
GROUP BY c.CountryID, c.Name
HAVING SUM(ci.Population) > 400;