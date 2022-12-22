INSERT INTO public.scheduler(
id, title, start, "end", active)
VALUES (3, 'test', '2022-12-19 11:23:54+03', '2022-12-19 11:23:54+03', true);



UPDATE public.scheduler
SET  active = false
WHERE id=3;



DELETE FROM public.scheduler
WHERE id=3;

