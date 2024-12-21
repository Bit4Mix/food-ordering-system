SELECT c.Name AS CountryName
FROM Country c
         LEFT JOIN City ci ON c.CountryID = ci.CountryID
         LEFT JOIN Building b ON ci.CityID = b.CityID
WHERE b.BuildingID IS NULL;