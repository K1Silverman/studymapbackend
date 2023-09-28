INSERT INTO public."roles" (name) VALUES ('Admin'), ('User');

INSERT INTO public.users (firstname,lastname,pw,email,role_id,status) VALUES
	 ('Silver','Kurrik','jswbw8Hf.idon7dvd','kurriksilver@gmail.com',4,'Active'),
	 ('Silvia','Adov','/Wylmite1/','adov.silvia@gmail.com',4,'Active');


INSERT INTO public."session" (hash,user_id,status,datetime_created) VALUES
	 ('30c6ff2a61af3472fc9323665b0e348ecdf6f6e1f1be70f129ee4af20c1b4663',3,'Active','2023-09-08 09:59:25.020891');

INSERT INTO public.folders (foldername,"position",status,user_id) VALUES
	 ('Unfoldered subjects',0,'Default',3),
	 ('Unfoldered subjects',0,'Default',4),
	 ('asdf',0,'Active',3);

INSERT INTO public.themes (name, first_color, secondary_color, buttons_color, status) VALUES
    ('Default Theme', '#93c5fd', '#6366f1', 'invert', 'Default')