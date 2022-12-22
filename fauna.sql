SELECT id, name, avg_age, discovery_date
	FROM public.fauna
	WHERE name LIKE '%fish%';

SELECT id, name, avg_age, discovery_date
	FROM public.fauna
	WHERE avg_age BETWEEN 10000 and 21000;


SELECT id, name, avg_age, discovery_date
	FROM public.fauna
	WHERE discovery_date IS NULL;


SELECT id, name, avg_age, discovery_date
	FROM public.fauna
	WHERE discovery_date < '1950-01-01';
